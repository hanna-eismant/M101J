package hannaeismant.m101j.controllers;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import hannaeismant.m101j.TemplateConfiguration;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

public abstract class AbstractController implements Route {

    protected static final String COOKIE_NAME = "user_session";
    protected static final int SECONDS_IN_HOUR = 3600;
    protected static final int COOKIE_AGE_HOURS = 24;

    private static final String METHOD_GET = "GET";
    private static final String METHOD_POST = "POST";

    protected TemplateConfiguration templateConfiguration;

    @Override
    public Object handle(Request request, Response response) throws Exception {
        if (request.requestMethod().equals(METHOD_GET)) {
            return get(request, response);
        } else if (request.requestMethod().equals(METHOD_POST)) {
            return post(request, response);
        }

        return "";  // or throw exception
    }

    protected StringWriter processTemplate(Map<String, String> params, final String name)
            throws TemplateException, IOException {

        Template template = templateConfiguration.getTemplate(name);
        StringWriter stringWriter = new StringWriter();
        template.process(params, stringWriter);
        return stringWriter;
    }

    public abstract Object get(Request request, Response response) throws Exception;

    public abstract Object post(Request request, Response response) throws Exception;

    public void setTemplateConfiguration(final TemplateConfiguration _templateConfiguration) {
        templateConfiguration = _templateConfiguration;
    }
}
