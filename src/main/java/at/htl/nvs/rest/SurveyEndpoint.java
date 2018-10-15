package at.htl.nvs.rest;

import at.htl.nvs.entities.Survey;
import at.htl.nvs.persistence.SurveyRepository;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("survey")
public class SurveyEndpoint {

    @EJB
    private SurveyRepository surveyRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.ok(surveyRepository.getAll()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getById(@PathParam("id") long id) {
        return Response.ok(surveyRepository.find(id)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Survey survey) {
        if(surveyRepository.create(survey)) {
            return Response.created(URI.create(survey.getId().toString())).build();
        } else {
            return Response.notModified().build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Survey survey) {
        if(surveyRepository.update(survey)) {
            return Response.accepted().build();
        } else {
            return Response.notModified().build();
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response delete(@PathParam("id") long id) {
        if(surveyRepository.delete(id)) {
            return Response.accepted().build();
        } else {
            return Response.notModified().build();
        }
    }
}
