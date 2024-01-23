package com.bsfh.pricing.service;

import com.bsfh.pricing.model.DBEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Stream;

@Repository
public class DBService<T extends DBEntity> {

    @PersistenceContext
    protected EntityManager em;

    @Transactional
    public Stream<T> getAll(Class<T> type) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(type);
        Root<T> root = cq.from(type);

        return em.createQuery(cq).getResultStream();
    }

    @Transactional
    public T find(Class<T> type, UUID id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(type);
        Root<T> root = cq.from(type);

        cq.where(cb.equal(root.get("id"), id));

        return em.createQuery(cq).getSingleResult();
    }

    @Transactional
    public boolean exists(Class<T> type) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(type);
        cq.from(type);

        return em.createQuery(cq).getResultStream().findAny().isPresent();
    }

    @Transactional
    public void create(T item) {
        em.persist(item);
    }

    @Transactional
    public void createAll(Collection<T> items) {
        for (T item : items) {
            em.persist(item);
        }
    }

    @Transactional
    public void update(T item) {
        em.merge(item);
    }

    @Transactional
    public void delete(Class<T> type, T item) {
        T itemFromDB = find(type, item.getId());
        em.remove(em.merge(itemFromDB));
    }
}
