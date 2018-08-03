package formers.core.users;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import formers.core.database.Database;

public class AdminCoreTest {
    @Test
    public void testCombinedIdIsHandledCorrectly() {

        Database db = mock(Database.class);

        // setup
        AdminCore adminCore = new AdminCore(db);

        // do some action
        adminCore.viewFormFromCombinedId("user-123");

        // assert
        verify(db).getForm(eq("123"));

    }

}
