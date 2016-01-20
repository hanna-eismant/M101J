package hannaeismant.m101j;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoConfiguration {

    private String databaseName;
    private MongoDatabase database;

    public MongoConfiguration(final String _databaseName) {
        databaseName = _databaseName;
    }

    private void init() {
        MongoClient client = new MongoClient();
        database = client.getDatabase(databaseName);
    }

    public MongoCollection<Document> getCollection(final String collectionName) {
        return database.getCollection(collectionName);
    }
}
