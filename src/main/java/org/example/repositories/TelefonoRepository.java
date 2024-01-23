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
        return null;
    }

    @Override
    public Telefonos findById(String id) {
        return null;
    }

    @Override
    public void delete(Telefonos telefono) {

    }

    @Override
    public void update(Telefonos telefono) {

    }
}
