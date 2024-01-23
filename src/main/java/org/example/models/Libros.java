package org.example.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Table(name = "libros")
@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"listaAutores"})
public class Libros {

    @Column(name = "id_libro")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NonNull
    @Column(name = "titulo")
    private String titulo;

    @NonNull
    @Column(name = "precio")
    private double precio;

    @ManyToMany(mappedBy = "listaLibros")
    private List<Autores> listaAutores = new ArrayList<Autores>();

    public void addAutor(Autores autor){
        this.listaAutores.add(autor);
    }
}
