package at.htl.nvs.survey.rest;

import at.htl.nvs.survey.business.repositories.UserRepository;
import at.htl.nvs.survey.entities.User;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("user")
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
    @Path("{id}")
    public Response getById(@PathParam("id") long id) {
        return Response.ok(userRepository.find(id)).build();
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
