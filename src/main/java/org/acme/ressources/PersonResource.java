package org.acme.ressources;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.acme.entities.Person;
import org.acme.repositories.PersonRepository;
import org.bson.types.ObjectId;


@Path("api/v1/persons")
public class PersonResource {

    @Inject
    PersonRepository personRepository;

    @POST
    @Consumes("application/json")
    public Uni<Response> createPerson(Person person) {
        return personRepository.persist(person)
                .onItem().transformToUni(id -> Uni.createFrom().item(Response.ok().build()));
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Uni<Response> getPersonById(@PathParam("id") String id) {
        return personRepository.findById(new ObjectId(id))
                .onItem().transform(Response::ok)
                .onItem().transform(Response.ResponseBuilder::build);
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    public Uni<Response> updatePersonById(@PathParam("id") String id, Person person){
        person.id = new ObjectId(id);
        return personRepository.update(person)
                .onItem().transform(updatedPerson -> Response.ok(updatedPerson).build());
    }

    @DELETE
    @Path("/{id}")
    public Uni<Response> deletePersonById(@PathParam("id") String id){
        return personRepository.deleteById(new ObjectId(id))
                .onItem().transform(ignore -> Response.noContent().build());
    }

    @GET
   @Produces("application/json")
   public Multi<Person> getPersons() {
        return personRepository.mongoCollection().find();
   }

}
