package hannaeismant.m101j.controllers;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import hannaeismant.m101j.TemplateConfiguration;
import hannaeismant.m101j.TimeoutException;
import hannaeismant.m101j.UnknownException;
import hannaeismant.m101j.session.SessionService;
import hannaeismant.m101j.session.SessionTokenGenerator;
import hannaeismant.m101j.user.UserAlreadyExistException;
import hannaeismant.m101j.user.UserService;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class SignupController extends AbstractRoute {

    private UserService userService;
    private SessionService sessionService;

    public SignupController(final UserService _userService, final SessionService _sessionService) {
        userService = _userService;
        sessionService = _sessionService;
    }

    @Override
    public Object get(final Request request, final Response response) throws Exception {
        return processTemplate(new HashMap<>(1));
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
            response.cookie(COOKIE_NAME, token, SECONDS_IN_HOUR * COOKIE_AGE_HOURS);
            response.redirect("/");

            return "";

        }  catch (UserAlreadyExistException e) {
            e.printStackTrace();

            Map<String, String> params = new HashMap<>(2);
            params.put("error", "User already exist, please try another username");

            return processTemplate(params);

        }
    }

    private StringWriter processTemplate(Map<String, String> params) throws TemplateException, IOException {
        Template template = TemplateConfiguration.getTemplate("signup");
        params.put("title", "Sing Up");
        StringWriter stringWriter = new StringWriter();
        template.process(params, stringWriter);
        return stringWriter;
    }
}
