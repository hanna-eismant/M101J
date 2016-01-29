package hannaeismant.m101j.post;

import org.bson.Document;

import java.util.List;

public interface PostDAO {

    String COLLECTION_NAME = "posts";
    String TITLE_FIELD = "title";
    String TEXT_FIELD = "text";
    String TAGS_FIELD = "tags";
    String PERMALINK_FIELD = "_id";
    String CREATED_FIELD = "created";

    Document create(final String _title, final String _text, final String _tags);

    List<Document> findLast(final int _count);
}
