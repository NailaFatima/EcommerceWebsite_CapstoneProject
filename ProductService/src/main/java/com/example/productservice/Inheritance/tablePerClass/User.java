package com.example.productservice.Inheritance.tablePerClass;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity (name = "tpc_user")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
    // In this type of inheritance strategy all classes have separate tables
    //it has better normalization than single table inheritance but
    // can result in more complex queries
    @Id
    Long id;
    String name;
    String email;
    String password;
}
