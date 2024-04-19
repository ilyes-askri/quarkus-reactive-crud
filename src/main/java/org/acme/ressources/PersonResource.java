package org.acme.ressources;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.models.Person;
import org.acme.services.servicesInterface.PersonServiceImpl;

import java.util.List;


@Path("api/v1/persons")
public class PersonResource {

    @Inject
    PersonServiceImpl personService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Response> createPerson(Person person) {
        return personService.createPerson(person);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> getPersonById(@PathParam("id") String id) {
        return personService.getPersonById(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Response> updatePersonById(@PathParam("id") String id, Person person){
        return personService.updatePersonById(id, person);

    }

    @DELETE
    @Path("/{id}")
    public Uni<Response> deletePersonById(@PathParam("id") String id){

        return personService.deletePersonById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
   public Uni<List<Person>> getPersons(@QueryParam("pageSize") @DefaultValue("5") Integer pageSize,
                                       @QueryParam("pageNumber") @DefaultValue("1") Integer pageNumber) {
        return personService.getPersonsPaginated(pageSize, pageNumber);

   }

    @GET
    @Path("/byJob")
    public Uni<List<Person>> getByJob(@QueryParam("job") String job) {
        return personService.getByJob(job);
    }
}
