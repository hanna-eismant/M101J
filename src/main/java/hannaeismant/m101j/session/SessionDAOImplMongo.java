package hannaeismant.m101j.session;

import com.mongodb.client.MongoCollection;
import hannaeismant.m101j.MongoConfiguration;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

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
    public Document create(final String _username, final String _token) {
        Document session = new Document()
                .append(TOKEN_FIELD, _token)
                .append(USERNAME_FIELD, _username);

        collection.insertOne(session);
        return session;
    }

    @Override
    public Document find(final String _token) {
        return collection.find().filter(eq(TOKEN_FIELD, _token)).first();
    }

    @Override
    public void remove(final String _token) {
        collection.findOneAndDelete(eq(TOKEN_FIELD, _token));
    }
}
