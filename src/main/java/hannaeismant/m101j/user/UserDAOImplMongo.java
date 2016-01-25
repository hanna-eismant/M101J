package hannaeismant.m101j.user;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.ReturnDocument;
import hannaeismant.m101j.MongoConfiguration;
import org.bson.Document;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class UserDAOImplMongo implements UserDAO {

    private MongoConfiguration mongoConfiguration;
    private MongoCollection<Document> collection;

    public UserDAOImplMongo(final MongoConfiguration _mongoConfiguration) {
        mongoConfiguration = _mongoConfiguration;
    }

    private void init() {
        collection = mongoConfiguration.getCollection(COLLECTION_NAME);
    }

    @Override
    public Document create(final String _username, final String _password) {
        Document user = new Document()
                .append(USERNAME_FIELD, _username)
                .append(PASSWORD_FIELD, _password);

        collection.insertOne(user);

        return user;
    }

    @Override
    public Document find(final String _username) {
        return collection.find()
                .filter(eq(UserDAO.USERNAME_FIELD, _username))
                .first();
    }

    @Override
    public Document update(final String _username, final String _newPassword) {
        Document user = new Document()
                .append(USERNAME_FIELD, _username)
                .append(PASSWORD_FIELD, _newPassword);

        FindOneAndReplaceOptions options = new FindOneAndReplaceOptions().returnDocument(ReturnDocument.AFTER);
        return collection.findOneAndReplace(eq(UserDAO.USERNAME_FIELD, _username), user, options);
    }

    @Override
    public Document find(final String _username, final String _password) {
        return collection.find()
                .filter(
                        and(
                                eq(UserDAO.USERNAME_FIELD, _username),
                                eq(UserDAO.PASSWORD_FIELD, _password)))
                .first();
    }
}
