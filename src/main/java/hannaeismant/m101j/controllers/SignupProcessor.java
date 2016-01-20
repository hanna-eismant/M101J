package hannaeismant.m101j.controllers;

import hannaeismant.m101j.datasources.UserDAO;
import spark.Request;
import spark.Response;
import spark.Route;

public class SignupProcessor implements Route {

    @Override
    public Object handle(Request request, Response response) throws Exception {

        System.out.println(request.requestMethod() + " SingupProcessor");

        String username = request.queryParams("username");
        String password = request.queryParams("password");
        String email = request.queryParams("email");

        UserDAO userDAO = UserDAO.getInstance();
        userDAO.create(username, password, email);

        response.redirect("/");
        return "";
    }
}
