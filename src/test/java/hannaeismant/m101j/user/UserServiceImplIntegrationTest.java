package hannaeismant.m101j.user;

import hannaeismant.m101j.AbstractIntegrationTest;
import hannaeismant.m101j.exceptions.TimeoutException;
import hannaeismant.m101j.exceptions.UnknownException;
import hannaeismant.m101j.exceptions.UserAlreadyExistException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;

import static org.junit.Assert.*;

public class UserServiceImplIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private UserService userService;

    private String username = "test user";
    private String password = "pass123";


    // Create

    @Test
    public void test_Create() throws UnknownException, TimeoutException, UserAlreadyExistException {
        User user = userService.create(username, password);

        assertNotNull("Created user cannot be 'null'", user);
        assertEquals("Created user has incorrect username", username, user.username);
        assertEquals("Created user has incorrect password", password, user.password);
    }

    @Test(expected = UserAlreadyExistException.class)
    public void test_Create_Duplicated()
            throws UnknownException, TimeoutException, UserAlreadyExistException {

        userService.create(username, password);
        userService.create(username, password);
    }

    @Test(expected = ConstraintViolationException.class)
    public void test_Create_EmptyUsername()
            throws UnknownException, TimeoutException, UserAlreadyExistException {

        userService.create("", password);
    }

    @Test(expected = ConstraintViolationException.class)
    public void test_Create_NullUsername()
            throws UnknownException, TimeoutException, UserAlreadyExistException {

        userService.create(null, password);
    }

    @Test(expected = ConstraintViolationException.class)
    public void test_Create_EmptyPassword()
            throws UnknownException, TimeoutException, UserAlreadyExistException {

        userService.create(username, "");
    }

    @Test(expected = ConstraintViolationException.class)
    public void test_Create_NullPassword()
            throws UnknownException, TimeoutException, UserAlreadyExistException {

        userService.create(username, null);
    }


    // Find

    @Test
    public void test_Find_Registered()
            throws UnknownException, TimeoutException, UserAlreadyExistException {

        userService.create(username, password);
        User user = userService.find(username);

        assertNotNull("Registered user cannot be 'null'", user);
        assertEquals("Registered user has incorrect username", username, user.username);
        assertEquals("Registered user has incorrect password", password, user.password);
    }

    @Test
    public void test_Find_Unregistered() {
        User user = userService.find(username);
        assertNull("Unregistered user should be 'null'", user);
    }

    @Test(expected = ConstraintViolationException.class)
    public void test_Find_EmptyUsername() {
        userService.find("");
    }

    @Test(expected = ConstraintViolationException.class)
    public void test_Find_NullUsername() {
        userService.find(null);
    }


    // Update Password

    @Test
    public void test_UpdatePassword_Registered()
            throws UnknownException, TimeoutException, UserAlreadyExistException {

        userService.create(username, password);
        String newPassword = password + "new!Pass";

        User user = userService.updatePassword(username, newPassword);
        assertNotNull("User cannot be 'null'", user);
        assertEquals("User has incorrect username", username, user.username);
        assertNotEquals("User has old password", password, user.password);
        assertEquals("User should have new password", newPassword, user.password);
    }

    @Test
    public void test_UpdatePassword_Unregistered() {

    }

    @Test(expected = ConstraintViolationException.class)
    public void test_UpdatePassword_EmptyUsername() {
        String newPassword = password + "new!Pass";
        userService.updatePassword("", newPassword);
    }

    @Test(expected = ConstraintViolationException.class)
    public void test_UpdatePassword_NullUsername() {
        String newPassword = password + "new!Pass";
        userService.updatePassword(null, newPassword);
    }

    @Test(expected = ConstraintViolationException.class)
    public void test_UpdatePassword_EmptyPassword() {
        userService.updatePassword(username, "");
    }

    @Test(expected = ConstraintViolationException.class)
    public void test_UpdatePassword_NullPassword() {
        userService.updatePassword(username, null);
    }


    // Check Credentials

    @Test
    public void test_CheckCredentials_CorrectPassword()
            throws UnknownException, TimeoutException, UserAlreadyExistException {

        userService.create(username, password);
        boolean isCorrect = userService.checkCredentials(username, password);

        assertTrue(isCorrect);
    }

    @Test
    public void test_CheckCredentials_IncorrectPassword()
            throws UnknownException, TimeoutException, UserAlreadyExistException {

        String newPassword = password + "new!Pass";
        userService.create(username, password);
        boolean isCorrect = userService.checkCredentials(username, newPassword);

        assertFalse(isCorrect);
    }

    @Test()
    public void test_CheckCredentials_UnregisteredUser() {
        boolean isCorrect = userService.checkCredentials(username, password);

        assertFalse(isCorrect);
    }
}
