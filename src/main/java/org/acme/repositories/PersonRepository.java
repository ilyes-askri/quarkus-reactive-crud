package org.acme.repositories;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entities.Person;

import java.util.List;


@ApplicationScoped
public class PersonRepository implements ReactivePanacheMongoRepository<Person> {

    public Uni<Person> findByName(String name){
        return find("name", name).firstResult();
    }
}
