package org.acme.repositories;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.models.Person;

import java.util.List;

@ApplicationScoped
public class PersonRepository implements ReactivePanacheMongoRepository<Person> {

    public Uni<List<Person>> findByJob(String job) {
        return find("job", job).list();
    }
}
