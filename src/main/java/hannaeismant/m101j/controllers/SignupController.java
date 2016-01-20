package hannaeismant.m101j.controllers;

import freemarker.template.Template;
import hannaeismant.m101j.AbstractRoute;
import hannaeismant.m101j.TemplateConfiguration;
import hannaeismant.m101j.datasources.UserDAO;
import spark.Request;
import spark.Response;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class SignupController extends AbstractRoute {

    private UserDAO userDAO;

    public SignupController(final UserDAO _userDAO) {
        userDAO = _userDAO;
    }

    @Override
    public Object get(final Request request, final Response response) throws Exception {
        System.out.println(request.requestMethod() + " Signup");

        Template template = TemplateConfiguration.getTemplate("signup");
        Map<String, String> params = new HashMap<>(1);
        params.put("title", "Sign Up");
        StringWriter stringWriter = new StringWriter();
        template.process(params, stringWriter);
        return stringWriter;
    }

    @Override
    public Object post(final Request request, final Response response) throws Exception {
        System.out.println(request.requestMethod() + " Singup");

        String username = request.queryParams("username");
        String password = request.queryParams("password");
        String email = request.queryParams("email");

        userDAO.create(username, password, email);

        response.redirect("/");
        return "";
    }
}
