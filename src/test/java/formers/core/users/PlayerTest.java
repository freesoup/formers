package formers.core.users;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import formers.core.authentication.Authorization;
import formers.core.exception.InsufficientAuthorityException;
import formers.core.form.utils.FormFormat;

/**
 * Test written with the help of Mockito.
 * 
 * @author jackie
 */
public class PlayerTest {
    @Test(expected = InsufficientAuthorityException.class)
    public void initForm_NonAdmin_InsufficientAuthority() throws InsufficientAuthorityException {
        // Setup
        UserCore userMock = mock(UserCore.class);
        AdminCore adminMock = mock(AdminCore.class);
        Player player = new Player(adminMock, userMock);

        // do some action
        player.initForm("usertest", Authorization.USER);
    }

    @Test
    public void submitFormFormat_Admin_Success() throws InsufficientAuthorityException {
        UserCore userMock = mock(UserCore.class);
        AdminCore adminMock = mock(AdminCore.class);
        FormFormat formMock = mock(FormFormat.class);
        Player player = new Player(adminMock, userMock);

        player.submitFormFormat(formMock, Authorization.ADMIN);

        verify(adminMock).submitFormFormat(eq(formMock));
    }

    @Test(expected = InsufficientAuthorityException.class)
    public void submitFormFormat_isStranger_InsufficientAuthority() throws InsufficientAuthorityException {
        UserCore userMock = mock(UserCore.class);
        AdminCore adminMock = mock(AdminCore.class);
        FormFormat formMock = mock(FormFormat.class);
        Player player = new Player(adminMock, userMock);

        player.submitFormFormat(formMock, Authorization.STRANGER);
    }

    @Test
    public void viewAllForm_Admin_Success() throws InsufficientAuthorityException {
        UserCore userMock = mock(UserCore.class);
        AdminCore adminMock = mock(AdminCore.class);
        Player player = new Player(adminMock, userMock);

        player.viewAllForm("usertest", Authorization.ADMIN);

        verify(adminMock).viewAllForm(eq("usertest"));
    }

}
