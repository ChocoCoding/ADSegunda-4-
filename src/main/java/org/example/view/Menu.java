package org.example.view;

import org.example.models.Autores;
import org.example.models.Libros;
import org.example.models.Telefonos;
import org.example.repositories.AutorRepository;
import org.example.repositories.LibrosRepository;
import org.example.repositories.TelefonoRepository;
import org.example.utilities.HibernateUtil;
import org.hibernate.Session;

import java.util.List;
import java.util.Scanner;

public class Menu {
    Scanner sc = new Scanner(System.in);
    AutorRepository autorRepository;
    LibrosRepository librosRepository;
    TelefonoRepository telefonoRepository;
    public void dialog(){
        System.out.println("Test");
        Session session = HibernateUtil.get().openSession();

        autorRepository = new AutorRepository(session);
        librosRepository = new LibrosRepository(session);
        telefonoRepository = new TelefonoRepository(session);

        int opt = 1;
        do {
            System.out.println("\n********************** Bienvenido a la biblioteca ************************");
            System.out.println("\n\t1. Insertar.\t\t\t\t3. Consultar");
            System.out.println("\n\t2. Borrar.\t\t\t\t\t0. Salir.");
            System.out.println("\n**************************************************************************");
            System.out.println("Introduce una opcion: ");
            opt = sc.nextInt();

            switch (opt){
                case 1:
                    menuInsercion();
                    break;
                case 2:
                    menuBorrado();
                    break;
                case 3:
                    menuConsultas();
                    break;
                case 0:
                    System.exit(0);
            }
        }while (opt != 0);
        session.close();
        System.out.println("Finalizando la conexion a MySQL");
    }

    public void menuInsercion(){
        Scanner sc = new Scanner(System.in);
        int opt = 1;
        do {
            System.out.println("\n********************** Bienvenido a la biblioteca ************************");
            System.out.println("\n\t1. Insertar autor.\t\t\t\t\t3. Insertar telefono.");
            System.out.println("\n\t2. Insertar libro.\t\t\t\t\t4. Enlazar autor y libro.");
            System.out.println("\n0.SALIR");
            System.out.println("\n**************************************************************************");

            System.out.println("Introduce una opcion: ");
            opt = sc.nextInt();

            switch (opt){
                case 1:
                    String dni = pedirString("Introduce el dni del autor");
                    String nombre = pedirString("Introduce el nombre del autor");
                    String nacionalidad = pedirString("Introduce la nacionalidad del autor");

                    Autores autor = new Autores(dni,nombre,nacionalidad);
                    autorRepository.create(autor);
                    break;
                case 2:
                    String titulo = pedirString("Introduce el titulo del libro");
                    System.out.println("Introduce el precio del libro");
                    double precio = sc.nextDouble();

                    Libros libro = new Libros(titulo,precio);
                    librosRepository.create(libro);
                    break;
                case 3:
                    String DNI = pedirString("Introduzca el DNI del autor");
                    String numTel = pedirString("Introduzca el número de telefono");

                    Telefonos telefono = new Telefonos(numTel,DNI);
                    Autores autor1 = autorRepository.findById(DNI);

                    autor1.setTelefono(telefono);
                    autorRepository.update(autor1);
                    telefonoRepository.create(telefono);
                    break;
                case 4:
                    String DNI2 = pedirString("Introduzca el DNI del autor");
                    String titulo2 = pedirString("Introduce el titulo del libro");

                    Autores autor2 = autorRepository.findById(DNI2);
                    Libros libro2 = librosRepository.findByTitulo(titulo2);

                    autor2.addListaLibros(libro2);

                    librosRepository.update(libro2);
                    break;

            }
        }while (opt != 0);
    }

    public void menuBorrado(){
        Scanner sc = new Scanner(System.in);
        int opt = 1;
        do {
            System.out.println("\n********************** Bienvenido a la biblioteca ************************");
            System.out.println("\n\t1. Borrar autor.\t\t\t\t\t2. Borrar Libro");
            System.out.println("\n\t0. SALIR.");
            System.out.println("\n**************************************************************************");

            System.out.println("Introduce una opcion: ");
            opt = sc.nextInt();

            switch (opt){
                case 1:
                    String dni = pedirString("Introduce el DNI del autor para borrar: ");
                    Autores autor = autorRepository.findById(dni);
                    autorRepository.delete(autor);
                    break;
                case 2:
                    String id = pedirString("Introduce el ID del libro a borrar: ");
                    Libros libro = librosRepository.findById(id);
                    librosRepository.delete(libro);
                    break;
            }
        }while (opt != 0);
    }

    public void menuConsultas(){
        Scanner sc = new Scanner(System.in);
        int opt = 1;
        do {
            System.out.println("\n********************** Bienvenido a la biblioteca ************************");
            System.out.println("\n\t1. Ver Libro.\t\t\t\t\t\t3. Ver todos los libros.");
            System.out.println("\n\t2. Ver Libros de un autor.\t\t\t4. Ver todos los autores con sus libros.");
            System.out.println("\n\t0. SALIR.");
            System.out.println("\n**************************************************************************");

            System.out.println("Introduce una opcion: ");
            opt = sc.nextInt();

            switch (opt){
                case 1:
                    String titulo = pedirString("Introduce el titulo del libro que quieres ver: ");
                    Libros libro = librosRepository.findByTitulo(titulo);

                    System.out.println(libro.getId());
                    System.out.println(libro.getTitulo());
                    System.out.println(libro.getPrecio());

                    for (Autores a: libro.getListaAutores()) {
                        System.out.println("Info Autor: ");
                        System.out.println(a.getDni());
                        System.out.println(a.getNombre());
                        System.out.println(a.getNacionalidad());
                        System.out.println(a.getTelefono());
                    }
                    break;

                case 2:
                    String dni = pedirString("Introduce el DNI del autor para buscar sus libros: ");
                    Autores autor = autorRepository.findById(dni);
                    autor.mostrarLibrosAutor();
                    break;
                case 3:
                    List<Libros> libros = librosRepository.findAll();
                    for (Libros l: libros) {
                        System.out.println(l);
                    }
                case 4:
                    List<Autores> autores = autorRepository.findAll();
                    for (Autores a : autores) {
                        System.out.println("Autor: " + a.getNombre());
                        a.mostrarLibrosAutor();
                    }
            }
        }while (opt != 0);
    }

    public String pedirString(String mensaje){
        System.out.println(mensaje);
        return sc.next();
    }
}




