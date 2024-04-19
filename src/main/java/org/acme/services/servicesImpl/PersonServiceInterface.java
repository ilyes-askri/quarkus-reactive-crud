package org.acme.services.servicesImpl;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.acme.models.Person;

import java.util.List;

public interface PersonServiceInterface {

    public Uni<Response> createPerson(Person person);

    public Uni<Response> getPersonById(String id);

    public Uni<Response> updatePersonById(@PathParam("id") String id, Person person);

    public Uni<Response> deletePersonById(String id);

    public Uni<List<Person>> getPersonsPaginated( Integer pageSize, Integer pageNumber);

    public Uni<List<Person>> getByJob(String job);

}
