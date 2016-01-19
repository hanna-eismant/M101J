import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class TemplateConfiguration {

    private static Map<String, Template> templates;
    private static Configuration configuration;

    static {
        init();
    }

    public static Template getTemplate(final String templateName) {
            return templates.get(templateName);
    }

    private TemplateConfiguration() {

    }

    synchronized private static void init() {
        configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setClassForTemplateLoading(TemplateConfiguration.class, "/");

        templates = new HashMap<>(3);

        try {
            Template homeTemplate = configuration.getTemplate("home.ftl");
            Template loginTemplate = configuration.getTemplate("login.ftl");
            Template singupTemplate = configuration.getTemplate("singup.ftl");

            templates.put("home", homeTemplate);
            templates.put("login", loginTemplate);
            templates.put("signup", singupTemplate);
        } catch (IOException e) {
            // todo: process errors
            e.printStackTrace();
        }
    }
}
