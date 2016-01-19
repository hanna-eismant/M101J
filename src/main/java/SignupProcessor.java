import spark.Request;
import spark.Response;
import spark.Route;

public class SignupProcessor implements Route {
    @Override
    public Object handle(Request request, Response response) throws Exception {
        response.redirect("/");
        return "";
    }
}
