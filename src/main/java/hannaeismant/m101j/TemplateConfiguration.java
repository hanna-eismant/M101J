package hannaeismant.m101j;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// todo: make me spring bean
public abstract class TemplateConfiguration {

    private static final String BASE_DIRECTORY = "/templates";

    private static Map<String, Template> templates;

    static {
        init();
    }

    public static Template getTemplate(final String templateName) {
        return templates.get(templateName);
    }

    synchronized private static void init() {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setClassForTemplateLoading(TemplateConfiguration.class, BASE_DIRECTORY);

        templates = new HashMap<>(3);

        try {
            Template homeTemplate = configuration.getTemplate("home.ftl");
            Template loginTemplate = configuration.getTemplate("login.ftl");
            Template singupTemplate = configuration.getTemplate("singup.ftl");
            Template template = configuration.getTemplate("post_create.ftl");

            templates.put("home", homeTemplate);
            templates.put("login", loginTemplate);
            templates.put("signup", singupTemplate);
            templates.put("post_create", template);
        } catch (IOException e) {
            // todo: process errors
            e.printStackTrace();
        }
    }
}
