package hannaeismant.m101j;

import com.mongodb.client.MongoCollection;
import hannaeismant.m101j.session.SessionDAO;
import hannaeismant.m101j.user.UserDAO;
import org.bson.Document;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config-test.xml")
public abstract class AbstractIntegrationTest {

    @Autowired
    protected MongoConfiguration mongoConfiguration;

    @Before
    public void setUp() {
        // drop all data from collection
        MongoCollection<Document> users = mongoConfiguration.getCollection(UserDAO.COLLECTION_NAME);
        users.drop();

        MongoCollection<Document> sessions = mongoConfiguration.getCollection(SessionDAO.COLLECTION_NAME);
        sessions.drop();

        // create test data



    }
}
