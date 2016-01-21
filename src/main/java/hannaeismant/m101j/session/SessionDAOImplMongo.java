package hannaeismant.m101j.session;

import com.mongodb.client.MongoCollection;
import hannaeismant.m101j.MongoConfiguration;
import org.bson.Document;

public class SessionDAOImplMongo implements SessionDAO {

    private MongoConfiguration mongoConfiguration;
    private MongoCollection<Document> collection;

    public SessionDAOImplMongo(final MongoConfiguration _mongoConfiguration) {
        mongoConfiguration = _mongoConfiguration;
    }

    public void init () {
        collection = mongoConfiguration.getCollection(COLLECTION_NAME);
    }

    @Override
    public Document create(final String username, final String token) {
        return null;
    }

    @Override
    public Document find(final String token) {
        return null;
    }

    @Override
    public void remove(final String token) {

    }
}
