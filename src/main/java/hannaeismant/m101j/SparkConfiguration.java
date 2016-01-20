package hannaeismant.m101j;

import spark.servlet.SparkApplication;

import java.util.Map;
import java.util.Set;

import static spark.Spark.get;
import static spark.Spark.post;

public class SparkConfiguration implements SparkApplication {

    private Map<String, AbstractRoute> routing;

    public SparkConfiguration(final Map<String, AbstractRoute> _routing) {
        routing = _routing;
    }

    @Override
    public void init() {
        Set<Map.Entry<String, AbstractRoute>> entries = routing.entrySet();

        for (Map.Entry<String, AbstractRoute> entry : entries) {
            get(entry.getKey(), entry.getValue());
            post(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void destroy() {

    }
}
