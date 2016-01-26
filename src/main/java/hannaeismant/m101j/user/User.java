package hannaeismant.m101j.user;

public class User {

    public String username;
    public String password;

    public User() {
    }

    public User(final String _username, final String _password) {
        username = _username;
        password = _password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
