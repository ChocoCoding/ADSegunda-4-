package org.example.repositories;

import org.example.models.Libros;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.management.Query;
import java.util.List;

public class LibrosRepository implements Repository<Libros>{
    Session session;

    public LibrosRepository(Session session){
        this.session = session;
    }


    @Override
    public void create(Libros libro) {
        Transaction trx = session.beginTransaction();
        session.persist(libro);
        trx.commit();
    }

    @Override
    public List<Libros> findAll() {
        Transaction trn = session.beginTransaction();
        List<Libros> libros = session.createQuery("SELECT l FROM Libros l").getResultList();
        trn.commit();
        return libros;

    }

    @Override
    public Libros findById(String id) {

        Libros libro = (Libros) session.createQuery("SELECT l FROM Libros l WHERE l.id = :id").setParameter("id",id).getSingleResult();
        return libro;
    }

    public Libros findByTitulo(String titulo){
        return (Libros) session.createQuery("SELECT l FROM Libros l WHERE l.titulo = :titulo").setParameter("titulo",titulo).getSingleResult();
    }

    @Override
    public void delete(Libros libro) {
        Transaction trn = session.beginTransaction();
        session.delete(libro);
        trn.commit();
    }

    @Override
    public void update(Libros libro) {
        Transaction trn = session.beginTransaction();
        session.update(libro);
        trn.commit();
    }


}