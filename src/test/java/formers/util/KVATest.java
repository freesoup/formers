package formers.util;

import org.junit.Test;

import com.worksap.company.access.KeyValueAccess;
import com.worksap.company.access.cassandra.CassandraAccessDatastax;
import com.worksap.company.access.cassandra.setting.CassandraSetting;

import formers.core.database.dto.FormFormatDto;

public class KVATest {
    @Test
    public void casTest() {
        CassandraSetting setting = new CassandraSetting();
        setting.setHost("127.0.0.1");
        setting.setNativeTransportPort(9042);
        setting.setTenantID("admin");
        setting.setSchema("formers");

        KeyValueAccess kva = new CassandraAccessDatastax(setting);

        FormFormatDto formFormat = new FormFormatDto("abc123", "test", "test");
        kva.createTable(FormFormatDto.class);
        kva.insert(formFormat);
    }
}
