package org.example.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "autores")
@ToString
@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"listaLibros"})

public class Autores {

    @Id
    @Column(name = "dni_autor")
    @NonNull
    private String dni;

    @Column(name = "nombre")
    @NonNull
    private String nombre;

    @Column(name = "nacionalidad")
    @NonNull
    private String nacionalidad;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "libros_autores",
            joinColumns = @JoinColumn(name = "dni_autor"),
            inverseJoinColumns = @JoinColumn(name = "id_libro"))
    List<Libros> listaLibros = new ArrayList<Libros>();

    @OneToOne(mappedBy = "autor",cascade = CascadeType.ALL)
    @JoinColumn(name = "dni_autor")
    private Telefonos telefono;

    public void addListaLibros(Libros libro){
        this.listaLibros.add(libro);
        libro.addAutor(this);
    }

    public void setTelefono(Telefonos telefono) {
        this.telefono = telefono;
        telefono.setAutor(this);
    }
    public void mostrarLibrosAutor(){
        for (Libros l : listaLibros) {
            System.out.println(l);
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Autores{");
        sb.append("dni='").append(dni).append('\'');
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", nacionalidad='").append(nacionalidad).append('\'');
        sb.append(", telefono=").append(telefono);
        return sb.toString();
    }
}
