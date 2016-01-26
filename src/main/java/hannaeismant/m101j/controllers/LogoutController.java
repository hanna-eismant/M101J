package hannaeismant.m101j.controllers;

import hannaeismant.m101j.session.SessionService;
import spark.Request;
import spark.Response;

public class LogoutController extends AbstractRoute {

    private SessionService sessionService;

    public LogoutController(final SessionService _sessionService) {
        sessionService = _sessionService;
    }

    @Override
    public Object get(final Request request, final Response response) throws Exception {
        return post(request, response);
    }

    @Override
    public Object post(final Request request, final Response response) throws Exception {
        String cookie = request.cookie(COOKIE_NAME);
        sessionService.remove(cookie);
        response.removeCookie(cookie);
        response.redirect("/");
        return "";
    }
}
