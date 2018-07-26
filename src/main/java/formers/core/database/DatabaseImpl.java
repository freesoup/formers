package formers.core.database;

import java.util.List;

import com.google.gson.Gson;
import com.worksap.company.access.KeyValueAccess;
import com.worksap.company.access.cassandra.CassandraAccessDatastax;
import com.worksap.company.access.cassandra.setting.CassandraSetting;

import formers.core.database.dto.FormFormatDto;
import formers.core.form.utils.FormFormat;
import formers.core.form.utils.FormResponse;
import formers.core.form.utils.FormResult;

public class DatabaseImpl implements Database {

    public FormFormat getForm(String iD) {
        Gson gson = new Gson();

        CassandraSetting setting = new CassandraSetting();
        setting.setHost("127.0.0.1");
        setting.setNativeTransportPort(9042);
        setting.setTenantID("admin");
        setting.setSchema("formers");

        System.out.println("Submitting");
        KeyValueAccess kva = new CassandraAccessDatastax(setting);

        FormFormatDto ffDto = kva.getSingle(iD, FormFormatDto.class);

        String formJSON = ffDto.getFormFormat();

        FormFormat form = gson.fromJson(formJSON, FormFormat.class);

        return form;
    }

    public void submitResponse(FormResponse responses) {
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

        FormFormatDto formFormat = new FormFormatDto("test", "test", jsonInString);

        kva.createTable(FormFormatDto.class);
        kva.insert(formFormat);
    }

    public List<String> getAllFormId() {
        // TODO Auto-generated method stub
        return null;
    }

    public FormResult getFormResult(String iD) {
        // TODO Auto-generated method stub
        return null;
    }

}
