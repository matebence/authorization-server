package com.blesk.authorizationserver.DAO.Accounts;

import com.blesk.authorizationserver.Model.Accounts;
import org.hibernate.Session;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.*;

@Repository
public class AccountDAOImpl extends RepresentationModel<AccountDAOImpl> implements AccountsDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Accounts save(Accounts accounts) {
        Session session = entityManager.unwrap(Session.class);
        session.save(accounts);
        return accounts;
    }

    @Override
    @Transactional
    public void update(Accounts accounts) {
        Session session = entityManager.unwrap(Session.class);
        session.update(accounts);
    }

    @Override
    @Transactional
    public void delete(Accounts accounts) {
        Session session = entityManager.unwrap(Session.class);
        session.delete(accounts);
    }

    @Override
    @Transactional
    public Accounts get(Long id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(Accounts.class, id);
    }

    @Override
    @Transactional
    public List<Accounts> getAll(int pageNumber, int pageSize) {
        Session session = entityManager.unwrap(Session.class);
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Accounts> criteriaQuery = criteriaBuilder.createQuery(Accounts.class);
        Root<Accounts> account = criteriaQuery.from(Accounts.class);
        CriteriaQuery<Accounts> accounts = criteriaQuery.select(account);
        TypedQuery<Accounts> typedQuery = session.createQuery(accounts);
        typedQuery.setFirstResult(pageNumber);
        typedQuery.setMaxResults(pageSize);
        return typedQuery.getResultList();
    }

    @Override
    @Transactional
    public Accounts getAccountInformations(String userName) {
        Session session = entityManager.unwrap(Session.class);
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Accounts> criteriaQuery = criteriaBuilder.createQuery(Accounts.class);
        Root<Accounts> root = criteriaQuery.from(Accounts.class);
        return entityManager.createQuery(criteriaQuery
                .where(criteriaBuilder.equal(root.get("userName"), userName))
                .orderBy(criteriaBuilder.asc(root.get("createdAt")))).getSingleResult();
    }

    @Override
    @Transactional
    public List<Accounts> searchBy(HashMap<String, HashMap<String, String>> criterias) {
        int pageNumber, pageSize, totalItems, numberOfPages;

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Long> countCriteria = criteriaBuilder.createQuery(Long.class);
        countCriteria.select(criteriaBuilder.count(countCriteria.from(Accounts.class)));
        Long count = entityManager.createQuery(countCriteria).getSingleResult();

        CriteriaQuery<Accounts> criteriaQuery = criteriaBuilder.createQuery(Accounts.class);
        Root<Accounts> root = criteriaQuery.from(Accounts.class);

        List<Predicate> predicates = new ArrayList<Predicate>();
        CriteriaQuery<Accounts> select = criteriaQuery.select(root).orderBy(criteriaBuilder.asc(root.get("createdAt")));

        if (criterias.get("pagination") != null) {
            for (Object o : criterias.get("search").entrySet()) {
                Map.Entry pair = (Map.Entry) o;
                System.out.println(pair.getKey() + " = " + pair.getValue());
                predicates.add(criteriaBuilder.equal(root.get(pair.getKey().toString()), pair.getValue().toString()));
            }
            select.where(predicates.toArray(new Predicate[]{}));
        }

        TypedQuery<Accounts> typedQuery = entityManager.createQuery(select);

        if (criterias.get("pagination") != null) {
            pageNumber = Integer.parseInt(criterias.get("pagination").get("pageNumber"));
            pageSize = Integer.parseInt(criterias.get("pagination").get("pageSize"));
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