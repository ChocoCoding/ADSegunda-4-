package org.example.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Table(name = "telefonos")
@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Telefonos implements Serializable {

    @Id
    @OneToOne
    private Autores autor;

    @NonNull
    @Column(name = "numero_telefono")
    private String numeroTlf;

}



