package com.example.todo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor; // <--- AJOUTE ÇA
import lombok.Data;
import lombok.NoArgsConstructor;  // <--- AJOUTE ÇA (Crucial pour JPA)

@Entity
@Data
@NoArgsConstructor // Constructeur vide (obligatoire pour JPA)
@AllArgsConstructor // Constructeur avec tous les arguments
public class Task {

    @Id    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private boolean completed;
}