package hannaeismant.m101j;

import spark.Request;
import spark.Response;
import spark.Route;

public abstract class AbstractRoute implements Route {

    @Override
    public Object handle(Request request, Response response) throws Exception {
        if (request.requestMethod().equals("GET")) {
            return get(request, response);
        } else if (request.requestMethod().equals("POST")) {
            return post(request, response);
        }

        return "";  // or throw exception
    }

    public abstract Object get(Request request, Response response) throws Exception;

    public abstract Object post(Request request, Response response) throws Exception;
}
