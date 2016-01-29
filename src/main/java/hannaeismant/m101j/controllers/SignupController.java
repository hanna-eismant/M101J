package hannaeismant.m101j.controllers;

import freemarker.template.TemplateException;
import hannaeismant.m101j.exceptions.TimeoutException;
import hannaeismant.m101j.exceptions.UnknownException;
import hannaeismant.m101j.exceptions.UserAlreadyExistException;
import hannaeismant.m101j.session.Session;
import hannaeismant.m101j.session.SessionService;
import hannaeismant.m101j.session.SessionTokenGenerator;
import hannaeismant.m101j.user.UserService;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SignupController extends AbstractController {

    public static final String TEMPLATE_NAME = "signup";

    private UserService userService;
    private SessionService sessionService;

    public SignupController(final UserService _userService, final SessionService _sessionService) {
        userService = _userService;
        sessionService = _sessionService;
    }

    @Override
    public Object get(final Request request, final Response response) throws Exception {
        String cookie = request.cookie(COOKIE_NAME);

        if (cookie != null) {
            // get session
            Session session = sessionService.find(cookie);

            if (session != null) {
                response.redirect("/");
                return "";
            }
        }

        Map<String, Object> params = new HashMap<>(1);
        params.put("title", "Sing Up");
        return processTemplate(params, TEMPLATE_NAME);
    }

    @Override
    public Object post(final Request request, final Response response)
            throws TimeoutException, UnknownException, IOException, TemplateException {

        String username = request.queryParams("username");
        String password = request.queryParams("password");

        try {
            userService.create(username, password);

            String token = SessionTokenGenerator.generate();
            sessionService.create(username, token);
            response.cookie("/", COOKIE_NAME, token, SECONDS_IN_HOUR * COOKIE_AGE_HOURS, false);
            response.redirect("/");

            return "";

        } catch (UserAlreadyExistException e) {
            e.printStackTrace();

            Map<String, Object> params = new HashMap<>(2);
            params.put("title", "Sing Up");
            params.put("error", "User already exist, please try another username");

            return processTemplate(params, TEMPLATE_NAME);
        }
    }
}
