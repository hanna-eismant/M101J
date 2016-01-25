package hannaeismant.m101j.controllers;

import freemarker.template.Template;
import hannaeismant.m101j.TemplateConfiguration;
import hannaeismant.m101j.session.SessionService;
import hannaeismant.m101j.session.SessionTokenGenerator;
import hannaeismant.m101j.user.UserService;
import spark.Request;
import spark.Response;

import java.io.StringWriter;
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
        Template template = TemplateConfiguration.getTemplate("login");
        Map<String, String> params = new HashMap<>(1);
        params.put("title", "Login");
        StringWriter stringWriter = new StringWriter();
        template.process(params, stringWriter);
        return stringWriter;
    }

    @Override
    public Object post(final Request request, final Response response) throws Exception {
        String username = request.queryParams("username");
        String password = request.queryParams("password");

        boolean isCorrect = userService.checkCredentials(username, password);

        if (isCorrect) {
            String token = SessionTokenGenerator.generate();
            sessionService.createSession(username, token);
            response.cookie(COOKIE_NAME, token, SECONDS_IN_HOUR * COOKIE_AGE_HOURS);
            response.redirect("/");
        } else {
            // do not redirect and add error message to show for user
        }

        return "";
    }
}
