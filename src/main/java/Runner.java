import hannaeismant.m101j.controllers.*;

import static spark.Spark.get;
import static spark.Spark.post;

public class Runner {

    public static void main(String[] args) {

        get("/", new HomeView());

        get("/login", new LoginView());
        post("/login", new LoginProcessor());

        get("/signup", new SignupView());
        post("/signup", new SignupProcessor());

        /*
        exception(NumberFormatException.class, (e, req, res) -> res.status(Calculator.NOT_FOUND));

        exception(ArithmeticException.class, (e, req, res) -> {
            res.status(Calculator.BED_REQUEST);
            res.body("This doesn't seem like a good idea.");
        });
         */
    }
}
