package hannaeismant.m101j.post;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Sorts;
import hannaeismant.m101j.MongoConfiguration;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostDAOImplMongo implements PostDAO {

    private MongoConfiguration mongoConfiguration;
    private MongoCollection<Document> collection;

    public PostDAOImplMongo(final MongoConfiguration _mongoConfiguration) {
        mongoConfiguration = _mongoConfiguration;
    }

    public void init () {
        collection = mongoConfiguration.getCollection(COLLECTION_NAME);
    }

    @Override
    public Document create(final String _title, final String _text, final String _tags) {
        String permalink = createPermalink(_title);

        Document post = new Document()
                .append(TITLE_FIELD, _title)
                .append(TEXT_FIELD, _text)
                .append(PERMALINK_FIELD, permalink)
                .append(TAGS_FIELD, _tags)
                .append(CREATED_FIELD, new Date().getTime());

        collection.insertOne(post);
        return post;
    }

    @Override
    public List<Document> findLast(final int _count) {
        List<Document> documents = new ArrayList<>(_count);

        FindIterable<Document> iterable = collection.find()
                .limit(_count)
                .sort(Sorts.descending(CREATED_FIELD));

        for (Document document : iterable) {
            documents.add(document);
        }

        return documents;
    }

    private String createPermalink(final String _title) {
        String permalink = _title.replaceAll("\\s", "_"); // whitespace becomes _
        permalink = permalink.replaceAll("\\W", ""); // get rid of non alphanumeric
        permalink = permalink.toLowerCase();
        permalink = permalink + (new Date()).getTime();
        return permalink;
    }
}
