package org.example.entities;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nom;
}
