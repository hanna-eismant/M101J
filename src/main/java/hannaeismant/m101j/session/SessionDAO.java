package hannaeismant.m101j.session;

import org.bson.Document;

public interface SessionDAO {
    String COLLECTION_NAME = "sessions";
    String USERNAME_FIELD = "username";
    String TOKEN_FIELD = "_id";

    Document create(final String _username, final String _token);

    Document find(final String _token);

    void remove(final String _token);
}
