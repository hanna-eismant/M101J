package hannaeismant.m101j.user;

import com.mongodb.client.MongoCollection;
import hannaeismant.m101j.AbstractIntegrationTest;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class UserServiceImplIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private UserService userService;

    private String username = "test user";
    private String password = "pass123";

    @Before
    public void setUp() {
        // drop all data from collection
        MongoCollection<Document> collection = mongoConfiguration.getCollection(UserDAO.COLLECTION_NAME);
        collection.drop();
    }

    @Test
    public void testCreate() {
        User user = userService.create(username, password);

        assertNotNull("Created user cannot be 'null'", user);
        assertEquals("Created user has incorrect username", username, user.username);
        assertEquals("Created user has incorrect password", password, user.password);
    }

    @Test(expected = Exception.class)
    public void testCreateDuplicated() {
        userService.create(username, password);
        userService.create(username, password);
    }

    @Test
    public void testFindRegistered() {
        userService.create(username, password);
        User user = userService.find(username);

        assertNotNull("Registered user cannot be 'null'", user);
        assertEquals("Registered user has incorrect username", username, user.username);
        assertEquals("Registered user has incorrect password", password, user.password);
    }

    @Test
    public void testFindUnregistered() {
        User user = userService.find(username);
        assertNull("Unregistered user should be 'null'", user);
    }

    @Test
    public void testUpdatePassword() {
        userService.create(username, password);
        String newPassword = password + "new!Pass";

        User user = userService.updatePassword(username, newPassword);
        assertNotNull("User cannot be 'null'", user);
        assertEquals("User has incorrect username", username, user.username);
        assertNotEquals("User has old password", password, user.password);
        assertEquals("User should have new password", newPassword, user.password);
    }
}
