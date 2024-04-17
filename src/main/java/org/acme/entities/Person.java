package org.acme.entities;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person extends PanacheMongoEntity {

    private String name;
    private Integer age;
    private String email;
    private String job;

}
