package org.example.models;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Table(name = "telefonos")
@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor

public class Telefonos implements Serializable {

    @Id
    @OneToOne(cascade = CascadeType.REMOVE)
    private Autores autor;

    @NonNull
    @Column(name = "numero_telefono")
    private String numeroTlf;

    @NonNull
    @Column(name = "dni")
    private String dni;

}



