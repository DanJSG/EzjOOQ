package org.ezlibs.ezjooq;

import org.jooq.Condition;

import java.util.List;

/**
 * <p>
 * Repository providing basic CRUD operations for POJOs.
 * </p>
 * <br>
 * <p>
 * Entity fields should be annotated using the Java Persistence API with:<br>
 *     {@code @Column(name = "YOUR_COLUMN_NAME")}.
 * </p>
 * <br>
 * <p>
 * The class should be annotated using the Java Persistence API with: <br>
 *     {@code @Table(name = "YOUR_TABLE_NAME)} <br>
 * unless the class has the same name as the database table.
 * </p>
 * @param <E> the type to perform CRUD operations on
 */
public interface CrudRepository<E> {

    /**
     * Insert an entity into the database.
     *
     * @param entity the entity to persist
     * @return {@code true} if successful, {@code false} otherwise
     */
    boolean create(E entity);

    /**
     * Insert a list of entities into the database.
     *
     * @param entities the entities to persist
     */
    void createMany(List<E> entities);

    /**
     * Get all entities of type {@code <E>} from the database.
     *
     * @return a list of entities returned from the database
     */
    List<E> readAll();

    /**
     * Get all entities of type {@code <E>} from the database where a certain condition is met.
     *
     * @param condition the condition to meet
     * @return a list of entities returned from the database, or an empty list if there are no results
     */
    List<E> readAllWhere(Condition condition);

    /**
     * Get a single entity of type {@code <E>} from the database where a certain condition is met.
     *
     * @param condition the condition to meet
     * @return an entity returned from the database, or {@code null} if there are no results
     */
    E readOneWhere(Condition condition);

    /**
     * Replace entities in the database with the provided entity where a specific condition is met.
     *
     * @param entity the entity to replace the entities with
     * @param condition the condition to meet
     * @return {@code true} if the update was successful, {@code false} otherwise
     */
    boolean updateWhere(E entity, Condition condition);

    /**
     * Delete an entity from the database.
     *
     * @param entity the entity to delete from the database
     * @return {@code true} if the entity was deleted, {@code false} otherwise
     */
    boolean delete(E entity);

}
