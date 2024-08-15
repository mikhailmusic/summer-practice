package rut.miit.hotel.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Optional;

public abstract class BaseRepository<T, ID> {
    private final Class<T> entityClass;
    @PersistenceContext
    private EntityManager entityManager;

    public BaseRepository(Class<T> entityClass) {
        this.entityClass  = entityClass;
    }

    public T save(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    public T update(T entity) {
        entityManager.merge(entity);
        return entity;
    }

    public Optional<T> findById(ID id) {
        return Optional.ofNullable(entityManager.find(entityClass, id));
    }

}
