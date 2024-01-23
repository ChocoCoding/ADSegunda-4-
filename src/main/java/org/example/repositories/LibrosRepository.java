package org.example.repositories;

import org.example.models.Libros;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
        return null;
    }

    @Override
    public Libros findById(String id) {
        return null;
    }

    @Override
    public void delete(Libros libro) {
        Transaction trx = session.beginTransaction();
        session.delete(libro);
        trx.commit();
    }

    @Override
    public void update(Libros libro) {

    }


}