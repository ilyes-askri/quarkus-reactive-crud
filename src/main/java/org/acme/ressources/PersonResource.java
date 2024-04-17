package org.acme.ressources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.acme.entities.Person;
import org.acme.repositories.PersonRepository;
import org.bson.types.ObjectId;

import java.net.URI;
import java.net.URISyntaxException;


@Path("api/v1/persons")
public class PersonResource {

    @Inject
    PersonRepository personRepository;

    @POST
    public Response createPerson(Person person) throws URISyntaxException {
        personRepository.persist(person);
        return Response.created(new URI("/" + person.id)).build();
    }
    
    @GET
    @Path("/{id}")
    public Response getPersonById(@PathParam("id") String id) {
        Person person = personRepository.findById(new ObjectId(id));
        return Response.ok(person).build();
    }

    //TODO: Needs to be paginated
    @GET
    public Response getPersons() {
        return Response.ok(personRepository.listAll()).build();
    }

    @PUT
    @Path("/{id}")
    public Response updatePersonById(@PathParam("id") String id, Person person){
        person.id = new ObjectId(id);
        personRepository.update(person);
        return Response.ok(person).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePersonById(@PathParam("id") String id){
        personRepository.deleteById(new ObjectId(id));
        return Response.noContent().build();
    }

}
