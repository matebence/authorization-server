package com.blesk.authorizationserver.DAO;

import com.blesk.authorizationserver.Utility.Criteria;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DAOImpl<T> implements DAO<T> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public T save(T t) {
        Session session = entityManager.unwrap(Session.class);
        try {
            session.save(t);
        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            session.clear();
            session.close();
        }
        return t;
    }

    @Override
    @Transactional
    public boolean update(T t) {
        Session session = entityManager.unwrap(Session.class);
        try {
            session.update(t);
        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            session.clear();
            session.close();
        }
        return session.getTransaction().getStatus() == TransactionStatus.COMMITTED;
    }

    @Override
    @Transactional
    public boolean delete(T t) {
        Session session = entityManager.unwrap(Session.class);
        try {
            session.delete(t);
        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            session.clear();
            session.close();
        }
        return session.getTransaction().getStatus() == TransactionStatus.COMMITTED;
    }

    @Override
    @Transactional
    public T get(Class c, Long id) {
        Session session = entityManager.unwrap(Session.class);
        return (T) session.get(c, id);
    }

    @Transactional
    public List getAll(Class c, int pageNumber, int pageSize) {
        Session session = entityManager.unwrap(Session.class);

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(c);
        Root select = criteriaQuery.from(c);
        CriteriaQuery entity = criteriaQuery.select(select).orderBy(criteriaBuilder.asc(select.get("createdAt")));

        Query typedQuery = session.createQuery(entity);
        typedQuery.setFirstResult(pageNumber);
        typedQuery.setMaxResults(pageSize);

        return typedQuery.getResultList();
    }

    @Override
    @Transactional
    public List searchBy(Class c, HashMap<String, HashMap<String, String>> criterias) {
        int pageNumber, pageSize, totalItems, numberOfPages;
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Long> countCriteria = criteriaBuilder.createQuery(Long.class);
        countCriteria.select(criteriaBuilder.count(countCriteria.from(c)));
        Long count = entityManager.createQuery(countCriteria).getSingleResult();

        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(c);
        Root root = criteriaQuery.from(c);

        List<Predicate> predicates = new ArrayList<Predicate>();
        CriteriaQuery select = criteriaQuery.select(root);

        if (criterias.get(Criteria.ORDER_BY) != null) {
            for (Object o : criterias.get(Criteria.SEARCH).entrySet()) {
                Map.Entry pair = (Map.Entry) o;
                if (pair.getValue().toString().toUpperCase().equals("ASC")) {
                    select.orderBy(criteriaBuilder.asc(root.get(pair.getKey().toString())));
                } else if (pair.getValue().toString().toUpperCase().equals("DESC")) {
                    select.orderBy(criteriaBuilder.asc(root.get(pair.getKey().toString())));
                }
            }
        }

        if (criterias.get(Criteria.SEARCH) != null) {
            for (Object o : criterias.get(Criteria.SEARCH).entrySet()) {
                Map.Entry pair = (Map.Entry) o;
                predicates.add(criteriaBuilder.equal(root.get(pair.getKey().toString()), pair.getValue().toString()));
            }
            select.where(predicates.toArray(new Predicate[]{}));
        }

        TypedQuery typedQuery = entityManager.createQuery(select);

        if (criterias.get(Criteria.PAGINATION) != null) {
            pageNumber = Integer.parseInt(criterias.get(Criteria.PAGINATION).get(Criteria.PAGE_NUMBER));
            pageSize = Integer.parseInt(criterias.get(Criteria.PAGINATION).get(Criteria.PAGE_SIZE));
            totalItems = count.intValue();
            numberOfPages = totalItems / pageNumber;

            if (pageNumber < numberOfPages) {
                typedQuery.setFirstResult(pageNumber);
                typedQuery.setMaxResults(pageSize);
            }
        }

        return typedQuery.getResultList();
    }
}