package com.blesk.authorizationserver.DAO.Accounts;

import com.blesk.authorizationserver.DAO.DAOImpl;
import com.blesk.authorizationserver.Model.Accounts;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;

@Repository
public class AccountDAOImpl extends DAOImpl<Accounts> implements AccountDAO {

    @PersistenceContext
    private EntityManager entityManager;

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
}