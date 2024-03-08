package org.example.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nom;
    private String prenom;
    private String email;

    @ManyToOne
    @JoinColumn(name = "departement_id")
    private Departement departement;

}
