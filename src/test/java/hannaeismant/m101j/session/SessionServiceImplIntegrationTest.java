package hannaeismant.m101j.session;

import com.mongodb.MongoWriteException;
import hannaeismant.m101j.AbstractIntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SessionServiceImplIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private SessionService sessionService;

    private String username = "hanna";
    private String token = "token";


    // Create

    @Test
    public void test_Create() {
        Session session = sessionService.createSession(username, token);

        assertNotNull("Created session cannot be 'null'", session);
        assertEquals("Created session has incorrect id", token, session.token);
        assertEquals("Created session has incorrect username", username, session.username);
    }

    @Test(expected = MongoWriteException.class)
    public void test_Create_DuplicatedToken() {
        sessionService.createSession(username, token);
        sessionService.createSession(username, token);
    }

    @Test
    public void test_Create_DuplicatedUsername() {
        String anotherToken = token + "_test";
        sessionService.createSession(username, token);
        sessionService.createSession(username, anotherToken);
    }

    @Test(expected = Exception.class)
    public void test_Create_UnregisteredUsername() {
        String anotherUsername = username + "_test";
        sessionService.createSession(anotherUsername, token);
    }

    @Test(expected = ConstraintViolationException.class)
    public void test_Create_EmptyToken() {
        sessionService.createSession(username, "");
    }

    @Test(expected = ConstraintViolationException.class)
    public void test_Create_NullToken() {
        sessionService.createSession(username, null);
    }

    @Test(expected = ConstraintViolationException.class)
    public void test_Create_EmptyUsername() {
        sessionService.createSession("", token);
    }

    @Test(expected = ConstraintViolationException.class)
    public void test_Create_NullUsername() {
        sessionService.createSession(null, token);
    }


    // Find

    public void test_Find_Existing() {
        sessionService.createSession(username, token);
        Session session = sessionService.findSession(token);

        assertNotNull("Saved session cannot be 'null'", session);
        assertEquals("Saved session has incorrect token", token, session.token);
        assertEquals("Saved session has incorrect username", username, session.username);
    }

    public void test_Find_NotExisting() {
        Session session = sessionService.findSession("token");
        assertNotNull("Found session should be 'null'", session);
    }

    @Test(expected = ConstraintViolationException.class)
    public void test_Find_EmptyToken() {
        sessionService.findSession("");
    }

    @Test(expected = ConstraintViolationException.class)
    public void test_Find_NullToken() {
        sessionService.findSession(null);
    }


    // Remove

    public void test_Remove_Existing() {
        sessionService.createSession(username, token);
        sessionService.removeSession(token);
        Session session = sessionService.findSession(token);

        assertNotNull("Removed session should be 'null'", session);
    }

    public void test_Remove_NotExisting() {
        sessionService.removeSession("token");
    }

    @Test(expected = ConstraintViolationException.class)
    public void test_Remove_EmptyToken() {
        sessionService.removeSession("");
    }

    @Test(expected = ConstraintViolationException.class)
    public void test_Remove_NullToken() {
        sessionService.removeSession(null);
    }

}
