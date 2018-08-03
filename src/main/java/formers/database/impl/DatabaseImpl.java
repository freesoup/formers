package formers.database.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.datastax.driver.core.exceptions.CodecNotFoundException;
import com.google.gson.Gson;
import com.worksap.company.access.KeyValueAccess;
import com.worksap.company.access.cassandra.CassandraAccessDatastax;
import com.worksap.company.access.cassandra.exception.CassandraNoHostAvailableException;
import com.worksap.company.access.cassandra.setting.CassandraSetting;
import com.worksap.company.dto.key.SearchCondition;
import com.worksap.company.dto.key.SearchConditions;
import com.worksap.company.dto.operation.Delete;
import com.worksap.company.dto.operation.Delete.Builder;

import formers.core.authentication.Authorization;
import formers.core.database.Database;
import formers.core.exception.DatabaseException;
import formers.core.form.utils.FormFormat;
import formers.core.form.utils.FormResponse;
import formers.database.dto.AccountDto;
import formers.database.dto.FormFormat2Dto;
import formers.database.dto.FormFormatDto;
import formers.database.dto.FormResponseDto;

public class DatabaseImpl implements Database {

    public FormFormat getForm(String iD) throws DatabaseException {
        Gson gson = new Gson();

        CassandraSetting setting = new CassandraSetting();
        setting.setHost("127.0.0.1");
        setting.setNativeTransportPort(9042);
        setting.setTenantID("admin");
        setting.setSchema("formers");

        KeyValueAccess kva = new CassandraAccessDatastax(setting);
        if (iD == null) {
            throw new DatabaseException(
                    "Error retrieving form of form ID " + iD + ". Check to ensure that form ID is correct.");
        }
        FormFormatDto ffDto = kva.getSingle(iD, FormFormatDto.class);

        if (ffDto == null) {
            throw new DatabaseException(
                    "Error retrieving form of form ID " + iD + ". Check to ensure that form ID is correct.");
        }

        String formJSON = ffDto.getFormFormat();

        FormFormat form = gson.fromJson(formJSON, FormFormat.class);

        return form;
    }

    public void submitResponse(FormResponse responses) {
        Gson gson = new Gson();

        String jsonInString = gson.toJson(responses);

        CassandraSetting setting = new CassandraSetting();
        setting.setHost("127.0.0.1");
        setting.setNativeTransportPort(9042);
        setting.setTenantID("admin");
        setting.setSchema("formers");
        KeyValueAccess kva = new CassandraAccessDatastax(setting);

        FormResponseDto formResponse = new FormResponseDto(responses.getFormID(), responses.getRecipient(),
                jsonInString);
        kva.createTable(FormResponseDto.class);
        kva.insert(formResponse);
    }

    public void submitNewForm(FormFormat form) {
        Gson gson = new Gson();

        String jsonInString = gson.toJson(form);

        CassandraSetting setting = new CassandraSetting();
        setting.setHost("127.0.0.1");
        setting.setNativeTransportPort(9042);
        setting.setTenantID("admin");
        setting.setSchema("formers");
        KeyValueAccess kva = new CassandraAccessDatastax(setting);

        FormFormatDto formFormat = new FormFormatDto(form.getID(), form.getOwner(), jsonInString);
        FormFormat2Dto formFormat2 = new FormFormat2Dto(form.getID(), form.getOwner(), jsonInString);

        kva.createTable(FormFormatDto.class);
        kva.createTable(FormFormat2Dto.class);
        kva.insert(formFormat);
        kva.insert(formFormat2);
    }

    public FormResponse getFormResult(String user, String formID) {
        Gson gson = new Gson();

        CassandraSetting setting = new CassandraSetting();
        setting.setHost("127.0.0.1");
        setting.setNativeTransportPort(9042);
        setting.setTenantID("admin");
        setting.setSchema("formers");
        KeyValueAccess kva = new CassandraAccessDatastax(setting);

        FormResponseDto frDto = kva.getSingle(Arrays.asList(formID, user), FormResponseDto.class);

        String formJSON = frDto.getFormResponse();

        FormResponse responses = gson.fromJson(formJSON, FormResponse.class);

        return responses;
    }

    @Override
    public List<FormResponse> getAllFormResponse(String formID) {
        List<FormResponse> formResponses = new ArrayList<FormResponse>();
        CassandraSetting setting = new CassandraSetting();
        setting.setHost("127.0.0.1");
        setting.setNativeTransportPort(9042);
        setting.setTenantID("admin");
        setting.setSchema("formers");
        KeyValueAccess kva = new CassandraAccessDatastax(setting);

        SearchCondition searchId = SearchCondition.eq("formId", formID);
        List<FormResponseDto> frdtoList = kva.search(new SearchConditions(searchId), FormResponseDto.class);

        Gson gson = new Gson();

        for (int i = 0; i < frdtoList.size(); i++) {
            FormResponse response = gson.fromJson(frdtoList.get(i).getFormResponse(), FormResponse.class);
            formResponses.add(response);
        }

        return formResponses;
    }

    @Override
    public List<FormFormat> getAllFormFormat(String userName) {
        List<FormFormat> formFormats = new ArrayList<FormFormat>();
        CassandraSetting setting = new CassandraSetting();
        setting.setHost("127.0.0.1");
        setting.setNativeTransportPort(9042);
        setting.setTenantID("admin");
        setting.setSchema("formers");
        KeyValueAccess kva = new CassandraAccessDatastax(setting);

        SearchCondition searchId = SearchCondition.eq("formOwner", userName);
        List<FormFormat2Dto> ffdtoList = kva.search(new SearchConditions(searchId), FormFormat2Dto.class);

        Gson gson = new Gson();

        for (int i = 0; i < ffdtoList.size(); i++) {
            FormFormat format = gson.fromJson(ffdtoList.get(i).getFormFormat(), FormFormat.class);
            formFormats.add(format);
        }

        return formFormats;
    }

    @Override
    public String getPass(String user) throws DatabaseException {
        CassandraSetting setting = new CassandraSetting();
        setting.setHost("127.0.0.1");
        setting.setNativeTransportPort(9042);
        setting.setTenantID("admin");
        setting.setSchema("formers");
        KeyValueAccess kva = new CassandraAccessDatastax(setting);

        try {
            AccountDto acdto = kva.getSingle(user, AccountDto.class);

            if (acdto == null) {
                return null;
            }

            return acdto.getHashedPass();
        } catch (CassandraNoHostAvailableException cnhae) {
            throw new DatabaseException("Out database is offline.");
        }
    }

    @Override
    public boolean addNewAccount(String user, String hashedPass) {
        CassandraSetting setting = new CassandraSetting();
        setting.setHost("127.0.0.1");
        setting.setNativeTransportPort(9042);
        setting.setTenantID("admin");
        setting.setSchema("formers");
        KeyValueAccess kva = new CassandraAccessDatastax(setting);

        kva.createTable(AccountDto.class);
        AccountDto acdtoCheck = kva.getSingle(user, AccountDto.class);

        if (acdtoCheck != null) {
            return false;
        }

        AccountDto acdto = new AccountDto(user, hashedPass, Authorization.ADMIN);
        kva.insert(acdto);

        return true;
    }

    @Override
    public Authorization getAuthority(String user) {
        CassandraSetting setting = new CassandraSetting();
        setting.setHost("127.0.0.1");
        setting.setNativeTransportPort(9042);
        setting.setTenantID("admin");
        setting.setSchema("formers");
        KeyValueAccess kva = new CassandraAccessDatastax(setting);

        kva.createTable(AccountDto.class);
        AccountDto acdtoCheck = kva.getSingle(user, AccountDto.class);

        if (acdtoCheck == null) {
            return Authorization.STRANGER;
        } else {
            return acdtoCheck.getAuthority();
        }
    }

    @Override
    public void deleteAllTracesOf(String formIdToBeDeleted, String user) throws DatabaseException {
        CassandraSetting setting = new CassandraSetting();
        setting.setHost("127.0.0.1");
        setting.setNativeTransportPort(9042);
        setting.setTenantID("admin");
        setting.setSchema("formers");
        KeyValueAccess kva = new CassandraAccessDatastax(setting);

        try {
            kva.deleteByKeySingle(formIdToBeDeleted, FormFormatDto.class);
            kva.deleteByKeySingle(Arrays.asList(user, formIdToBeDeleted), FormFormat2Dto.class);

            SearchCondition searchId = SearchCondition.eq("formId", formIdToBeDeleted);
            Builder builder = new Builder(FormResponseDto.class, new SearchConditions(searchId));
            Delete deleteOperation = builder.build();
            kva.deleteByOperation(deleteOperation);
        } catch (CodecNotFoundException cnfe) {
            throw new DatabaseException("Error finding the form to delete.");
        }
    }
}
