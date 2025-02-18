package com.example.productservice.Inheritance.mappedSuperclass;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass// All the fields of this class will be inherited by the child classes
public class User {
    @Id
    Long id;
    String name;
    String email;
    String password;
}
