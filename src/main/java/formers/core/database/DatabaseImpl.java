package formers.core.database;

import java.util.List;

import com.worksap.company.access.KeyValueAccess;
import com.worksap.company.access.cassandra.CassandraAccessDatastax;
import com.worksap.company.access.cassandra.setting.CassandraSetting;

import formers.core.database.dto.FormFormatDto;
import formers.core.form.utils.FormFormat;
import formers.core.form.utils.FormResponse;
import formers.core.form.utils.FormResult;

public class DatabaseImpl implements Database {

    public FormFormat getForm(String iD) {
        return null;
        // TODO Auto-generated method stub

    }

    public void submitResponse(FormResponse responses) {
        CassandraSetting setting = new CassandraSetting();
        setting.setHost("127.0.0.1");
        setting.setNativeTransportPort(9042);
        setting.setTenantID("admin");
        setting.setSchema("formers");

        KeyValueAccess kva = new CassandraAccessDatastax(setting);

        FormFormatDto formFormat = new FormFormatDto("test", "test");

        kva.createTable(FormFormatDto.class);
    }

    public void submitNewForm(FormFormat form) {

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
