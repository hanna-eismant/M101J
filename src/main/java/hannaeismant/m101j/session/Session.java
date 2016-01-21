package hannaeismant.m101j.session;

public class Session {

    public String username;

    public String token;

    public Session() {
    }

    public Session(final String _username, final String _token) {
        username = _username;
        token = _token;
    }

    @Override
    public String toString() {
        return "Session{" +
                "username='" + username + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
