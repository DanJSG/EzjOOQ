package com.jsg.jooqplayground.ezsql;

import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Table;

import java.util.HashMap;
import java.util.Map;

class EntityRepresentation {

    private final Field<?>[] columns;
    private final Object[] values;
    private final Table<Record> table;
    private final Map<Field<?>, Object> entityMap;

    EntityRepresentation(Table<Record> table, Field<?>[] columns, Object[] values) {
        this.columns = columns;
        this.values = values;
        this.table = table;
        this.entityMap = buildEntityMap(columns, values);
    }

    Field<?>[] getFields() {
        return columns;
    }

    Object[] getValues() {
        return values;
    }

    Table<Record> getTable() {
        return table;
    }

    public Map<Field<?>, Object> getEntityMap() {
        return entityMap;
    }

    private static <E extends Entity> Map<Field<?>, Object> buildEntityMap(Field<?>[] fields, Object[] values) {
        Map<Field<?>, Object> entityMap = new HashMap<>();
        for (int i = 0; i < fields.length; i++) {
            entityMap.put(fields[i], values[i]);
        }
        return entityMap;
    }

}
