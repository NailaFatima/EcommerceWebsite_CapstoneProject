package com.example.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor  // Add this to allow Jackson to instantiate
@AllArgsConstructor
public class Category extends BaseModel {
    String description;
}
