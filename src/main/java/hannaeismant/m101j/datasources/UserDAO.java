package hannaeismant.m101j.datasources;

import com.mongodb.client.MongoCollection;
import hannaeismant.m101j.MongoConfiguration;
import org.bson.Document;

public class UserDAO {

    public static final String COLLECTION_NAME = "users";

    private static final String USERNAME_FIELD = "_id";
    private static final String PASSWORD_FIELD = "password";
    private static final String EMAIL_FIELD = "email";

    private static UserDAO instance;

    private MongoCollection<Document> collection;

    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
            instance.init();
        }

        return instance;
    }

    public void create(final String username, final String password, final String email) {
        Document user = new Document()
                .append(USERNAME_FIELD, username)
                .append(PASSWORD_FIELD, password)
                .append(EMAIL_FIELD, email);

        collection.insertOne(user);
    }

    private void init() {
        collection = MongoConfiguration.getCollection(COLLECTION_NAME);
    }
}
