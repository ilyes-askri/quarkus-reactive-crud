package org.acme.services.servicesInterface;

import io.quarkus.panache.common.Page;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.acme.models.Person;
import org.acme.repositories.PersonRepository;
import org.acme.services.servicesImpl.PersonServiceInterface;
import org.bson.types.ObjectId;

import java.util.List;

@ApplicationScoped
public class PersonServiceImpl implements PersonServiceInterface {

    @Inject
    PersonRepository personRepository;

    @Override
    public Uni<Response> createPerson(Person person) {
        return personRepository.persist(person)
                .onItem().transformToUni(id -> Uni.createFrom().item(Response.ok().build()));    }

    @Override
    public Uni<Response> getPersonById(String id) {
        return personRepository.findById(new ObjectId(id))
                .onItem().transform(Response::ok)
                .onItem().transform(Response.ResponseBuilder::build);
    }

    @Override
    public Uni<Response> updatePersonById(String id, Person person) {
        person.id = new ObjectId(id);
        return personRepository.update(person)
                .onItem().transform(updatedPerson -> Response.ok(updatedPerson).build());
    }

    @Override
    public Uni<Response> deletePersonById(String id) {
        return personRepository.deleteById(new ObjectId(id))
                .onItem().transform(ignore -> Response.noContent().build());    }

    @Override
    public Uni<List<Person>> getPersonsPaginated(Integer pageSize, Integer pageNumber) {
        return personRepository
                .findAll()
                .page(Page.of(pageNumber - 1, pageSize))
                .list();    }

    @Override
    public Uni<List<Person>> getByJob(String job) {
        return personRepository.findByJob(job);
    }
}
