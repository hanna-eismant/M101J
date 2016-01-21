package hannaeismant.m101j.controllers;

import spark.Request;
import spark.Response;
import spark.Route;

public abstract class AbstractRoute implements Route {

    protected static final String COOKIE_NAME = "user_session";
    protected static final int SECONDS_IN_HOUR = 3600;
    protected static final int COOKIE_AGE_HOURS = 24;
    private static final String METHOD_GET = "GET";
    private static final String METHOD_POST = "POST";

    @Override
    public Object handle(Request request, Response response) throws Exception {
        if (request.requestMethod().equals(METHOD_GET)) {
            return get(request, response);
        } else if (request.requestMethod().equals(METHOD_POST)) {
            return post(request, response);
        }

        return "";  // or throw exception
    }

    public abstract Object get(Request request, Response response) throws Exception;

    public abstract Object post(Request request, Response response) throws Exception;
}
