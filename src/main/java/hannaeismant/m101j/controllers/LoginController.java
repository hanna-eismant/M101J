package hannaeismant.m101j.controllers;

import hannaeismant.m101j.session.SessionService;
import hannaeismant.m101j.session.SessionTokenGenerator;
import hannaeismant.m101j.user.UserService;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class LoginController extends AbstractRoute {

    private SessionService sessionService;
    private UserService userService;

    public LoginController(final SessionService _sessionService, final UserService _userService) {
        sessionService = _sessionService;
        userService = _userService;
    }

    @Override
    public Object get(final Request request, final Response response) throws Exception {
        Map<String, String> params = new HashMap<>(1);
        params.put("title", "Login");
        return processTemplate(params);
    }

    @Override
    public Object post(final Request request, final Response response) throws Exception {
        String username = request.queryParams("username");
        String password = request.queryParams("password");

        boolean isCorrect = userService.checkCredentials(username, password);

        if (isCorrect) {
            String token = SessionTokenGenerator.generate();
            sessionService.create(username, token);
            response.cookie(COOKIE_NAME, token, SECONDS_IN_HOUR * COOKIE_AGE_HOURS);
            response.redirect("/");

            return "";
        } else {
            Map<String, String> params = new HashMap<>(2);
            params.put("title", "Login");
            params.put("error", "Username or password is incorrect");

            return processTemplate(params);
        }
    }
}
