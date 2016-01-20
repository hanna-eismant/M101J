package hannaeismant.m101j.controllers;

import freemarker.template.Template;
import hannaeismant.m101j.TemplateConfiguration;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class LoginView implements Route {

    @Override
    public Object handle(Request request, Response response) throws Exception {

        System.out.println(request.requestMethod() + " LoginView");

        Template template = TemplateConfiguration.getTemplate("login");
        Map<String, String> params = new HashMap<>(1);
        params.put("title", "Login");
        StringWriter stringWriter = new StringWriter();
        template.process(params, stringWriter);
        return stringWriter;

    }
}
