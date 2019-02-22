import com.google.gson.Gson;
import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        port(8080);
        UserService userService = new UserMapping();

        get("/",(request, response) -> {

            response.redirect("/users");
            return null;
        } );

        get("/users",(request, response) -> {
            response.type("application/json");
            return new Gson().toJson(
                    new StandardResponse(StatusResponse.SUCCESS,new Gson().toJsonTree(userService.getUsers())));
        });

       /* get("/users/:id",(request, response) -> {
            response.type("application/json");
            return new Gson().toJson(
                    new StandardResponse(StatusResponse.SUCCESS,new Gson().toJsonTree(userService.getUser(request.params(":id")))));
        });

        delete("/users/:id",(request, response) -> {
            response.type("application/json");

            userService.deleteUser(request.params(":id"));
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, "user deleted"));

        });
        */

        post("/users", (request, response) -> {
            response.type("application/json");

            User user = new Gson().fromJson(request.body(), User.class);
            if (userService.userExist(user.getId())==false) {
                userService.addUser(user);
                response.status(201);

                return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
            }
            return "User exist";
        });

       /* options("/users/:id",(request, response) ->
        {
            response.type("application/json");
            return new Gson().toJson(
                    new StandardResponse(StatusResponse.SUCCESS,(userService.userExist(request.params(":id")))? "User found" : "user not Found"));
        });

        put("/users/:id", (request, response) -> {
            response.type("application/json");
            if (userService.userExist(request.params(":id")) == true) {
                User toEdit = new Gson().fromJson(request.body(), User.class);
                User editedUser = userService.editUser(toEdit);

                if (editedUser != null) {
                    return new Gson().toJson(
                            new StandardResponse(StatusResponse.SUCCESS, new Gson()
                                    .toJsonTree(editedUser)));
                } else {
                    return new Gson().toJson(
                            new StandardResponse(StatusResponse.ERROR, new Gson()
                                    .toJson("User not found or error in edit")));
                }
            }
            else {
                return new Gson().toJson(
                        new StandardResponse(StatusResponse.ERROR, new Gson()
                                .toJson("User not found or error in edit")));
            }
        });
*/
    }


}
