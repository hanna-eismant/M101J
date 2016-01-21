package hannaeismant.m101j.controllers;

import freemarker.template.Template;
import hannaeismant.m101j.TemplateConfiguration;
import spark.Request;
import spark.Response;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class LoginController extends AbstractRoute {

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
        response.redirect("/");
        return "";
    }
}
