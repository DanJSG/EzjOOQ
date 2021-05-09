package com.jsg.jooqplayground.ezjooq;

import org.jooq.Record;
import org.jooq.impl.DSL;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

final class EntityConverter {

    private EntityConverter() {}

    static EntityRepresentation unwrap(Object entity) {
        org.jooq.Table<Record> table = getTable(entity.getClass());
        Field[] entityFields = entity.getClass().getDeclaredFields();
        List<Field> validFields = new ArrayList<>();
        org.jooq.Field<?>[] columns = getAllColumnsAndFields(entityFields, validFields).toArray(new org.jooq.Field[0]);
        Object[] values = getValues(entity, validFields);
        return new EntityRepresentation(table, columns, values);
    }

    static org.jooq.Table<Record> getTable(Class<?> type) {
        Annotation[] annotations = type.getDeclaredAnnotations();
        String tableName = null;
        for (Annotation annotation : annotations) {
            if (!(annotation instanceof Table)) continue;
            Table tableAnnotation = (Table) annotation;
            tableName = stripPackage(tableAnnotation.name().isEmpty() ? type.getName() : tableAnnotation.name());
            break;
        }
        return tableName != null ? DSL.table(tableName.toLowerCase()) : null;
    }

    private static Object[] getValues(Object entity, List<Field> fields) {
        Object[] values = new Object[fields.size()];
        for(int i = 0; i < fields.size(); i++) {
            try {
                values[i] = fields.get(i).get(entity);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return values;
    }

    private static List<org.jooq.Field<?>> getAllColumnsAndFields(Field[] fields, List<Field> validFields) {
        List<org.jooq.Field<?>> columns = new ArrayList<>();
        for (Field field : fields) {
            org.jooq.Field<?> column = getColumn(field);
            if (column == null) continue;
            field.setAccessible(true);
            columns.add(column);
            validFields.add(field);
        }
        return columns;
    }

    private static org.jooq.Field<?> getColumn(Field field) {
        Annotation[] annotations = field.getDeclaredAnnotations();
        String columnName = null;
        for (Annotation annotation : annotations) {
            if (!(annotation instanceof Column)) continue;
            Column columnAnnotation = (Column) annotation;
            columnName = columnAnnotation.name().isEmpty() ? field.getName() : columnAnnotation.name();
            break;
        }
        return columnName != null ? DSL.field(columnName) : null;
    }

    private static String stripPackage(String tableName) {
        int index = tableName.lastIndexOf(".");
        if (index == -1) return tableName;
        return tableName.substring(index + 1);
    }
}
