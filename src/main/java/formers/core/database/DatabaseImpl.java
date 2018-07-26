package formers.core.database;

import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.worksap.company.access.KeyValueAccess;
import com.worksap.company.access.cassandra.CassandraAccessDatastax;
import com.worksap.company.access.cassandra.setting.CassandraSetting;

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

        kva.createTable(FormFormatDto.class);
        kva.insert(formFormat);
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<FormFormat> getAllFormFormat(String userName) {
        // TODO Auto-generated method stub
        return null;
    }
}
