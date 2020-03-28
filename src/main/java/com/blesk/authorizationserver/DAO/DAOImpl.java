package com.blesk.authorizationserver.DAO;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.*;

@Repository
public class DAOImpl<T> implements DAO<T> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public T save(T t) {
        Session session = this.entityManager.unwrap(Session.class);
        try {
            session.save(t);
        } catch (Exception e) {
            session.clear();
            session.close();
            return null;
        }
        return t;
    }

    @Override
    @Transactional
    public boolean update(T t) {
        Session session = this.entityManager.unwrap(Session.class);
        try {
            session.update(t);
        } catch (Exception e) {
            session.clear();
            session.close();
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean delete(T t) {
        Session session = this.entityManager.unwrap(Session.class);
        try {
            session.delete(t);
        } catch (Exception e) {
            session.clear();
            session.close();
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public T get(Class c, Long id) {
        Session session = this.entityManager.unwrap(Session.class);
        return (T) session.get(c, id);
    }

    @Transactional
    public List getAll(Class c, int pageNumber, int pageSize) {
        Session session = this.entityManager.unwrap(Session.class);
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<Long> countCriteria = criteriaBuilder.createQuery(Long.class);
        countCriteria.select(criteriaBuilder.count(countCriteria.from(c)));
        Long count = this.entityManager.createQuery(countCriteria).getSingleResult();

        if (pageNumber > Math.floor(count.intValue() / pageSize)) {
            return Collections.emptyList();
        }

        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(c);
        Root select = criteriaQuery.from(c);

        CriteriaQuery entity = criteriaQuery.select(select).orderBy(criteriaBuilder.asc(select.get("createdAt")));

        Query typedQuery = session.createQuery(entity);
        typedQuery.setFirstResult(pageNumber);
        typedQuery.setMaxResults(pageSize);

        return typedQuery.getResultList();
    }
}