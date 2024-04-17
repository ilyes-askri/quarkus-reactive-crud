package org.acme.repositories;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entities.Person;

import java.util.List;


@ApplicationScoped
public class PersonRepository implements PanacheMongoRepository<Person> {

    public Person findByName(String name){
        return find("name",name).firstResult();
    }

    public List<Person> findOrderName(){
        return listAll(Sort.by("name"));
    }

}
