package at.htl.nvs.rest;

import at.htl.nvs.entities.User;
import at.htl.nvs.persistence.UserRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/user")
public class UserEndpoint {

    @Inject
    private UserRepository userRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.ok(userRepository.getAll()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("id/{id}")
    public Response getById(@PathParam("id") long id) {
        return Response.ok(userRepository.find(id)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("name/{regex}")
    public Response getById(@PathParam("regex") String regex) {
        return Response.ok(userRepository.getByRegexUsername(regex)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(User user) {
        if(userRepository.create(user)) {
            return Response.created(URI.create(user.getId().toString())).build();
        } else {
            return Response.notModified().build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(User user) {
        if(userRepository.update(user)) {
            return Response.accepted().build();
        } else {
            return Response.notModified().build();
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response delete(@PathParam("id") long id) {
        if(userRepository.delete(id)) {
            return Response.accepted().build();
        } else {
            return Response.notModified().build();
        }
    }
}
