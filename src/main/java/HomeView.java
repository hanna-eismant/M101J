import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class HomeView implements Route {

    @Override
    public Object handle(Request request, Response response) throws Exception {
        Template template = TemplateConfiguration.getTemplate("home");
        Map<String, String> params = new HashMap<>(2);
        params.put("title", "Home");
        params.put("username", null);
        StringWriter stringWriter = new StringWriter();
        template.process(params, stringWriter);
        return stringWriter;
    }
}
