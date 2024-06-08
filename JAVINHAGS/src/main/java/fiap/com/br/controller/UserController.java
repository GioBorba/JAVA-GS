package fiap.com.br.controller;

import fiap.com.br.exception.EmailInUseException;
import fiap.com.br.exception.SenhaIncorretaException;
import fiap.com.br.model.User;
import fiap.com.br.service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response registerUser(User user) {
        try {
            userService.createUser(user);
            return Response.status(Response.Status.CREATED).entity("User registered successfully").build();
        } catch (EmailInUseException e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response loginUser(User user) {
        try {
            userService.loginUser(user.getEmail(), user.getPassword());
            return Response.ok("Login successful").build();
        } catch (SenhaIncorretaException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByEmail(@PathParam("email") String email) {
        User user = userService.getUserByEmail(email);
        if (user != null) {
            return Response.ok(user).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/{email}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateUser(@PathParam("email") String email, User user) {
        User existingUser = userService.getUserByEmail(email);
        if (existingUser != null) {
            user.setEmail(email); // Ensure the email remains unchanged
            userService.updateUser(user);
            return Response.ok("User updated successfully").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{email}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteUser(@PathParam("email") String email) {
        userService.deleteUser(email);
        return Response.ok("User deleted successfully").build();
    }
}
