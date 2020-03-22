package com.blesk.authorizationserver.DAO.Roles;

import com.blesk.authorizationserver.DAO.DAOImpl;
import com.blesk.authorizationserver.Model.Privileges;
import com.blesk.authorizationserver.Model.Roles;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RolesDAOImpl extends DAOImpl<Roles> implements RolesDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Roles> getListOfRoles(String[] names) {
        Session session = entityManager.unwrap(Session.class);

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Roles> criteriaQuery = criteriaBuilder.createQuery(Roles.class);

        Root<Roles> root = criteriaQuery.from(Roles.class);
        List<Predicate> predicates = new ArrayList<Predicate>();

        CriteriaQuery select = criteriaQuery.select(root);

        for(String name : names){
            predicates.add(criteriaBuilder.equal(root.get("name"), name.toUpperCase()));
        }

        select.where(predicates.toArray(new Predicate[]{}));
        return entityManager.createQuery(select).getResultList();
    }

    @Override
    public Roles getRoleByName(String name) {
        Session session = entityManager.unwrap(Session.class);
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Roles> criteriaQuery = criteriaBuilder.createQuery(Roles.class);
        Root<Roles> root = criteriaQuery.from(Roles.class);
        return entityManager.createQuery(criteriaQuery
                .where(criteriaBuilder.equal(root.get("name"), name))).getSingleResult();
    }

    @Override
    public List<Privileges> getPrivilegesAssignedToRole(String name) {
        return getRoleByName(name).getPrivileges();
    }
}