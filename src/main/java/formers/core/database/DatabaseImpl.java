package formers.core.database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.worksap.company.access.KeyValueAccess;
import com.worksap.company.access.cassandra.CassandraAccessDatastax;
import com.worksap.company.access.cassandra.setting.CassandraSetting;
import com.worksap.company.dto.key.SearchCondition;
import com.worksap.company.dto.key.SearchConditions;

import formers.core.database.dto.AccountDto;
import formers.core.database.dto.FormFormat2Dto;
import formers.core.database.dto.FormFormatDto;
import formers.core.database.dto.FormResponseDto;
import formers.core.form.utils.FormFormat;
import formers.core.form.utils.FormResponse;

public class DatabaseImpl implements Database {

    public FormFormat getForm(String iD) {
        Gson gson = new Gson();

        CassandraSetting setting = new CassandraSetting();
        setting.setHost("127.0.0.1");
        setting.setNativeTransportPort(9042);
        setting.setTenantID("admin");
        setting.setSchema("formers");

        KeyValueAccess kva = new CassandraAccessDatastax(setting);

        FormFormatDto ffDto = kva.getSingle(iD, FormFormatDto.class);

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
    public String getPass(String user) {
        CassandraSetting setting = new CassandraSetting();
        setting.setHost("127.0.0.1");
        setting.setNativeTransportPort(9042);
        setting.setTenantID("admin");
        setting.setSchema("formers");
        KeyValueAccess kva = new CassandraAccessDatastax(setting);

        AccountDto acdto = kva.getSingle(user, AccountDto.class);

        if (acdto == null) {
            return null;
        }

        return acdto.getHashedPass();
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

        AccountDto acdto = new AccountDto(user, hashedPass);
        kva.insert(acdto);

        return true;
    }
}
