package com.example.productservice.Inheritance.mappedSuperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "mps_instructor")
public class Instructor extends User {
    String subject;
    Double salary;
}
