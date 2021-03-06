package hannaeismant.m101j.user;

import hannaeismant.m101j.exceptions.TimeoutException;
import hannaeismant.m101j.exceptions.UnknownException;
import hannaeismant.m101j.exceptions.UserAlreadyExistException;
import org.bson.Document;


public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    public UserServiceImpl(final UserDAO _userDAO) {
        userDAO = _userDAO;
    }

    @Override
    public User create(final String _username, final String _password)
            throws TimeoutException, UserAlreadyExistException, UnknownException {
        Document document = userDAO.create(_username, _password);
        return buildUserObject(document);
    }

    @Override
    public User find(final String _username) {
        Document document = userDAO.find(_username);
        return buildUserObject(document);
    }

    @Override
    public User updatePassword(final String _username, final String _newPassword) {
        Document document = userDAO.update(_username, _newPassword);
        return buildUserObject(document);
    }

    @Override
    public boolean checkCredentials(final String _username, final String _password) {
        Document document = userDAO.find(_username, _password);
        return document != null;
    }

    private User buildUserObject(final Document _document) {
        if (_document == null) {
            return null;
        }

        User user = new User();
        user.username = _document.getString(UserDAO.USERNAME_FIELD);
        user.password = _document.getString(UserDAO.PASSWORD_FIELD);

        return user;
    }
}
