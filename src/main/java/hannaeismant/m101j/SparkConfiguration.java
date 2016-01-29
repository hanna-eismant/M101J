package hannaeismant.m101j;

import hannaeismant.m101j.controllers.AbstractController;
import spark.servlet.SparkApplication;

import java.util.Map;
import java.util.Set;

import static spark.Spark.get;
import static spark.Spark.post;

public class SparkConfiguration implements SparkApplication {

    private Map<String, AbstractController> routing;

    public SparkConfiguration(final Map<String, AbstractController> _routing) {
        routing = _routing;
    }

    @Override
    public void init() {
        Set<Map.Entry<String, AbstractController>> entries = routing.entrySet();

        for (Map.Entry<String, AbstractController> entry : entries) {
            get(entry.getKey(), entry.getValue());
            post(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void destroy() {

    }
}
