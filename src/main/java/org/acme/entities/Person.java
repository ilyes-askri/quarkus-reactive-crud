package org.acme.entities;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@MongoEntity(database="Person") Optional here, we can specify it in application.yml
public class Person extends ReactivePanacheMongoEntity {

    private String name;
    private Integer age;
    private String email;
    private String job;
}
