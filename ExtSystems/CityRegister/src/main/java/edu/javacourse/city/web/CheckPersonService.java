package edu.javacourse.city.web;

import edu.javacourse.city.domian.PersonResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/check")
public class CheckPersonService {
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public PersonResponse checkPerson(@PathParam("id") int id,
                                      @QueryParam("name") String name) {
        return new PersonResponse();
    }
}
