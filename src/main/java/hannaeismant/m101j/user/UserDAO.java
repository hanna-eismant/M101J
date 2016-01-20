package hannaeismant.m101j.user;

import org.bson.Document;

public interface UserDAO {
    String COLLECTION_NAME = "users";
    String USERNAME_FIELD = "_id";
    String PASSWORD_FIELD = "password";

    Document create(String username, String password);

    Document find(String _username);

    Document update(String _username, String _newPassword);
}
