import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class LoginView implements Route {

    @Override
    public Object handle(Request request, Response response) throws Exception {
        Template template = TemplateConfiguration.getTemplate("login");
        Map<String, String> params = new HashMap<>(1);
        params.put("title", "Login");
        StringWriter stringWriter = new StringWriter();
        template.process(params, stringWriter);
        return stringWriter;

    }
}
