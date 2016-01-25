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
        Session session = sessionService.create(username, token);

        assertNotNull("Created session cannot be 'null'", session);
        assertEquals("Created session has incorrect id", token, session.token);
        assertEquals("Created session has incorrect username", username, session.username);
    }

    @Test(expected = MongoWriteException.class)
    public void test_Create_DuplicatedToken() {
        sessionService.create(username, token);
        sessionService.create(username, token);
    }

    @Test
    public void test_Create_DuplicatedUsername() {
        String anotherToken = token + "_test";
        sessionService.create(username, token);
        sessionService.create(username, anotherToken);
    }

    @Test(expected = Exception.class)
    public void test_Create_UnregisteredUsername() {
        String anotherUsername = username + "_test";
        sessionService.create(anotherUsername, token);
    }

    @Test(expected = ConstraintViolationException.class)
    public void test_Create_EmptyToken() {
        sessionService.create(username, "");
    }

    @Test(expected = ConstraintViolationException.class)
    public void test_Create_NullToken() {
        sessionService.create(username, null);
    }

    @Test(expected = ConstraintViolationException.class)
    public void test_Create_EmptyUsername() {
        sessionService.create("", token);
    }

    @Test(expected = ConstraintViolationException.class)
    public void test_Create_NullUsername() {
        sessionService.create(null, token);
    }


    // Find

    public void test_Find_Existing() {
        sessionService.create(username, token);
        Session session = sessionService.find(token);

        assertNotNull("Saved session cannot be 'null'", session);
        assertEquals("Saved session has incorrect token", token, session.token);
        assertEquals("Saved session has incorrect username", username, session.username);
    }

    public void test_Find_NotExisting() {
        Session session = sessionService.find("token");
        assertNotNull("Found session should be 'null'", session);
    }

    @Test(expected = ConstraintViolationException.class)
    public void test_Find_EmptyToken() {
        sessionService.find("");
    }

    @Test(expected = ConstraintViolationException.class)
    public void test_Find_NullToken() {
        sessionService.find(null);
    }


    // Remove

    public void test_Remove_Existing() {
        sessionService.create(username, token);
        sessionService.remove(token);
        Session session = sessionService.find(token);

        assertNotNull("Removed session should be 'null'", session);
    }

    public void test_Remove_NotExisting() {
        sessionService.remove("token");
    }

    @Test(expected = ConstraintViolationException.class)
    public void test_Remove_EmptyToken() {
        sessionService.remove("");
    }

    @Test(expected = ConstraintViolationException.class)
    public void test_Remove_NullToken() {
        sessionService.remove(null);
    }

}
