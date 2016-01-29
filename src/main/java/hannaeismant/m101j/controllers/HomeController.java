package hannaeismant.m101j.controllers;

import hannaeismant.m101j.post.Post;
import hannaeismant.m101j.post.PostService;
import hannaeismant.m101j.session.Session;
import hannaeismant.m101j.session.SessionService;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeController extends AbstractController {

    public static final String TEMPLATE_NAME = "home";

    private SessionService sessionService;
    private PostService postService;

    public HomeController(final SessionService _sessionService, final PostService _postService) {
        sessionService = _sessionService;
        postService = _postService;
    }

    @Override
    public Object get(final Request request, final Response response) throws Exception {
        Map<String, Object> params = new HashMap<>(2);
        params.put("title", "Home");

        // get cookie from request
        String cookie = request.cookie(COOKIE_NAME);

        if (cookie != null) {
            // get session
            Session session = sessionService.find(cookie);

            if (session == null) {
                // if not exist → put «null» username
                params.put("username", null);    //response.removeCookie(cookie);

                // if you are here, then client has cooke, but server not has session for this token
                // so, delete it from base
                sessionService.remove(cookie);
            } else {
                // if exist → put username to parameters
                params.put("username", session.username);

                // update cookie age
                response.cookie("/", COOKIE_NAME, session.token, SECONDS_IN_HOUR * COOKIE_AGE_HOURS, false);
            }
        }

        // get posts
        List<Post> posts = postService.findLast(5);
        params.put("posts", posts);

        // process template with parameters
        return processTemplate(params, TEMPLATE_NAME);
    }

    @Override
    public Object post(final Request request, final Response response) throws Exception {
        return ""; // or throw exception
    }
}
