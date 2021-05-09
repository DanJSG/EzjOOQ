package com.jsg.jooqplayground.ezjooq;

import org.jooq.Condition;

import java.util.List;

public interface CrudRepository<E> {

    boolean create(E entity);

    void createMany(List<E> entities);

    List<E> readAll();

    List<E> readAllWhere(Condition condition);

    E readOneWhere(Condition condition);

    boolean updateWhere(E entity, Condition condition);

    boolean delete(E entity);

}
