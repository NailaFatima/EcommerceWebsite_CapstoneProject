package com.example.productservice.Inheritance.singleTable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity (name = "st_user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User {
    //In this type of inheritance, all the classes are stored in a single table.
    // The table has a column to identify the type of the record.
    @Id
    Long id;
    String name;
    String email;
    String password;
}
