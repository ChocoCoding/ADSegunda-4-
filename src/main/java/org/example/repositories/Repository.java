package org.example.repositories;

import java.util.List;

public interface Repository <T>{

    void create(T t);
    List<T> findAll();
    T findById(String id);
    void delete(T t);
    void update(T t);

}
