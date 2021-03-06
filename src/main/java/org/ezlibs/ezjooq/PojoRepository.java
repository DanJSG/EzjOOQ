package org.ezlibs.ezjooq;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.impl.DSL;

import java.util.List;

public class PojoRepository<E> implements CrudRepository<E> {

    private final DSLContext db;
    private final Class<E> type;

    /**
     * Create a new repository for performing CRUD operations on POJOs.
     *
     * @param db a pre-configured jOOQ DSLContext
     * @param type the POJO class
     */
    public PojoRepository(DSLContext db, Class<E> type) {
        this.db = db;
        this.type = type;
    }

    @Override
    public boolean create(E entity) {
        EntityRepresentation converted = EntityConverter.convert(entity);
        return db.insertInto(converted.getTable()).columns(converted.getFields()).values(converted.getValues()).execute() > 0;
    }

    @Override
    public void createMany(List<E> entities) {
        db.transaction(c -> {
            entities.forEach(e -> {
                EntityRepresentation converted = EntityConverter.convert(e);
                c.dsl().insertInto(converted.getTable()).columns(converted.getFields()).values(converted.getValues()).execute();
            });
        });
    }

    @Override
    public List<E> readAll() {
        return db.select().from(EntityConverter.getTable(type)).fetch().into(type);
    }

    @Override
    public List<E> readAllWhere(Condition condition) {
        return db.select().from(EntityConverter.getTable(type)).where(condition).fetch().into(type);
    }

    @Override
    public E readOneWhere(Condition condition) {
        Record record = db.select().from(EntityConverter.getTable(type)).where(condition).limit(1).fetchOne();
        return record != null ? record.into(type) : null;
    }

    @Override
    public boolean updateWhere(E entity, Condition condition) {
        EntityRepresentation converted = EntityConverter.convert(entity);
        return db.update(converted.getTable()).set(converted.getEntityMap()).where(condition).execute() > 0;
    }

    @Override
    public boolean delete(E entity) {
        EntityRepresentation converted = EntityConverter.convert(entity);
        return db.delete(converted.getTable()).where(DSL.condition(converted.getEntityMap())).execute() > 0;
    }

}
