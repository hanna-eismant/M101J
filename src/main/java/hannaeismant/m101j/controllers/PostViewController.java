package hannaeismant.m101j.controllers;

import freemarker.template.TemplateException;
import hannaeismant.m101j.post.Post;
import hannaeismant.m101j.post.PostService;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.HashMap;

public class PostViewController extends AbstractController {

    public static final String TEMPLATE_NAME = "post_view";

    private PostService postService;

    public PostViewController(final PostService _postService) {
        postService = _postService;
    }

    @Override
    public Object get(final Request request, final Response response) throws IOException, TemplateException {
        String permalink = request.params(":permalink");
        Post post = postService.find(permalink);
        HashMap<String, Object> params = new HashMap<>(1);
        params.put("post", post);
        return processTemplate(params, TEMPLATE_NAME);
    }

    @Override
    public Object post(final Request request, final Response response) {
        return null;
    }
}
