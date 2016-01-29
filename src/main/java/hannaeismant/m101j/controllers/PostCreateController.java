package hannaeismant.m101j.controllers;

import freemarker.template.TemplateException;
import hannaeismant.m101j.session.Session;
import hannaeismant.m101j.session.SessionService;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.HashMap;

public class PostCreateController extends AbstractController {

    public static final String TEMPLATE_NAME = "post_create";

    private SessionService sessionService;

    public PostCreateController(final SessionService _sessionService) {
        sessionService = _sessionService;
    }

    @Override
    public Object get(final Request request, final Response response)
            throws IOException, TemplateException {

        HashMap<String, String> params = new HashMap<>(1);

        String cookie = request.cookie(COOKIE_NAME);

        if (cookie != null) {
            // get session
            Session session = sessionService.find(cookie);

            if (session == null) {
                response.redirect("/");
                return "";
            } else {
                // if exist → put username to parameters
                params.put("username", session.username);

                // update cookie age
                response.cookie(COOKIE_NAME, session.token, SECONDS_IN_HOUR * COOKIE_AGE_HOURS);
            }
        }

        params.put("title", "Create new post");
        return processTemplate(params, TEMPLATE_NAME);
    }

    @Override
    public Object post(final Request request, final Response response) {



        response.redirect("/");
        return "";
    }
}
