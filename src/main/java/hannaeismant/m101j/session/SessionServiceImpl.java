package hannaeismant.m101j.session;

public class SessionServiceImpl implements SessionService {

    private SessionDAO sessionDAO;

    public SessionServiceImpl(final SessionDAO _sessionDAO) {
        sessionDAO = _sessionDAO;
    }

    @Override
    public Session createSession(final String username, final String token) {
        return null;
    }

    @Override
    public Session findSession(final String token) {
        return null;
    }

    @Override
    public void removeSession(final String token) {

    }
}
