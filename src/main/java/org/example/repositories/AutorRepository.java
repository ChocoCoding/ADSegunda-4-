package org.example.repositories;

import org.example.models.Autores;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AutorRepository implements Repository<Autores> {

    Session session;

    public AutorRepository(Session session){
        this.session = session;

    }
    @Override
    public void create(Autores autor) {
        Transaction trx = session.beginTransaction();
        session.persist(autor);
        trx.commit();
    }

    @Override
    public List<Autores> findAll() {
        Transaction trx = session.beginTransaction();
        List<Autores> autores = session.createQuery("SELECT a from Autores a").getResultList();
        trx.commit();
        return autores;
    }

    @Override
    public Autores findById(String id) {
        try {
            return (Autores) session.createQuery("SELECT a FROM Autores a WHERE id = :id").setParameter("id",id).getSingleResult();
        }catch (Exception e){
            System.out.println("El autor no existe");
        }
        return null;
    }

    @Override
    public void delete(Autores autor) {
        Transaction trx = session.beginTransaction();
        session.delete(autor);
        trx.commit();
    }

    @Override
    public void update(Autores autor) {
        Transaction trx = session.beginTransaction();
        session.update(autor);
        trx.commit();
    }



}
