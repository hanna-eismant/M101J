package hannaeismant.m101j.session;

import org.bson.Document;

public class SessionServiceImpl implements SessionService {

    private SessionDAO sessionDAO;

    public SessionServiceImpl(final SessionDAO _sessionDAO) {
        sessionDAO = _sessionDAO;
    }

    @Override
    public Session create(final String _username, final String _token) {
        Document document = sessionDAO.create(_username, _token);
        return buildSessionObject(document);
    }

    @Override
    public Session find(final String _token) {
        Document document = sessionDAO.find(_token);
        return buildSessionObject(document);
    }

    @Override
    public void remove(final String _token) {
        sessionDAO.remove(_token);
    }

    private Session buildSessionObject(final Document _document) {
        if (_document == null) {
            return null;
        }

        Session session = new Session();
        session.token = _document.getString(SessionDAO.TOKEN_FIELD);
        session.username = _document.getString(SessionDAO.USERNAME_FIELD);

        return session;
    }
}
