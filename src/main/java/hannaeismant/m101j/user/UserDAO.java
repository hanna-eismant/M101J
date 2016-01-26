package hannaeismant.m101j.user;

import com.mongodb.MongoWriteException;
import hannaeismant.m101j.exceptions.TimeoutException;
import hannaeismant.m101j.exceptions.UnknownException;
import hannaeismant.m101j.exceptions.UserAlreadyExistException;
import org.bson.Document;

public interface UserDAO {
    String COLLECTION_NAME = "users";
    String USERNAME_FIELD = "_id";
    String PASSWORD_FIELD = "password";

    Document create(String username, String password) throws MongoWriteException, UserAlreadyExistException, UnknownException, TimeoutException;

    Document find(String _username);

    Document update(String _username, String _newPassword);

    Document find(String _username, String _password);
}
