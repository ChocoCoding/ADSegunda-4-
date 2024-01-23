package org.example.repositories;

import org.example.models.Autores;
import org.example.models.Telefonos;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TelefonoRepository implements Repository<Telefonos> {

    Session session;

    public TelefonoRepository(Session session){
        this.session = session;
    }

    @Override
    public void create(Telefonos telefono) {
        Transaction trx = session.beginTransaction();
        session.persist(telefono);
        trx.commit();
    }

    @Override
    public List<Telefonos> findAll() {
        return (List<Telefonos>) session.createQuery("SELECT t FROM Telefonos t");
    }

    @Override
    public Telefonos findById(String id) {
        return (Telefonos) this.session.createQuery("SELECT t FROM Telefonos t WHERE id = :id").setParameter("id",id);
    }

    @Override
    public void delete(Telefonos telefono) {
        Transaction trn = this.session.beginTransaction();
        this.session.delete(telefono);
        trn.commit();
    }

    @Override
    public void update(Telefonos telefono) {
        Transaction trn = this.session.beginTransaction();
        this.session.update(telefono);
        trn.commit();
    }
}
