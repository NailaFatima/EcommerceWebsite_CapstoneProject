package com.example.productservice.Inheritance.joinedTable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "jt_user") //In this type uses different tables for each class in hierarchy
@Inheritance(strategy = InheritanceType.JOINED) //Joined with foreign key
public class User {
    @Id
    Long id;
    String name;
    String email;
    String password;

}
