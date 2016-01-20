package hannaeismant.m101j.user;

public interface UserService {

    User create(final String _username, final String _password);

    User find(final String _username);

    User updatePassword(final String _username, final String _newPassword);
}
