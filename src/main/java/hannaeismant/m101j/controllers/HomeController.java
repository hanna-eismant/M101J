package hannaeismant.m101j.controllers;

import freemarker.template.Template;
import hannaeismant.m101j.TemplateConfiguration;
import hannaeismant.m101j.session.Session;
import hannaeismant.m101j.session.SessionService;
import hannaeismant.m101j.user.UserService;
import spark.Request;
import spark.Response;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class HomeController extends AbstractRoute {

    private UserService userService;
    private SessionService sessionService;

    public HomeController(final UserService _userService, final SessionService _sessionService) {
        userService = _userService;
        sessionService = _sessionService;
    }

    @Override
    public Object get(final Request request, final Response response) throws Exception {
        Map<String, String> params = new HashMap<>(2);
        params.put("title", "Home");

        // get cookie from request
        String cookie = request.cookie(COOKIE_NAME);

        if (cookie != null) {
            // get session
            Session session = sessionService.find(cookie);

            if (session == null) {
                // if not exist → put «null» username
                params.put("username", null);
            } else {
                // if exist → put username to parameters
                params.put("username", session.username);

                // update cookie age
                response.cookie(COOKIE_NAME, session.token, SECONDS_IN_HOUR * COOKIE_AGE_HOURS);
            }
        }

        // process template with parameters
        Template template = TemplateConfiguration.getTemplate("home");
        StringWriter stringWriter = new StringWriter();
        template.process(params, stringWriter);

        return stringWriter;
    }

    @Override
    public Object post(final Request request, final Response response) throws Exception {
        return ""; // or throw exception
    }
}
