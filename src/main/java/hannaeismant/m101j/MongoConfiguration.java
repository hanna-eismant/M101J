package hannaeismant.m101j;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public abstract class MongoConfiguration {

    public static final String DATABASE_NAME = "hannaeismant";

    private static MongoDatabase database;

    static {
        init();
    }

    public static MongoCollection<Document> getCollection(final String collectionName) {
        return database.getCollection(collectionName);
    }

    synchronized private static void init() {
        MongoClient client = new MongoClient();
        database = client.getDatabase(DATABASE_NAME);
    }
}
