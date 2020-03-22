package com.blesk.authorizationserver.DAO;

import com.blesk.authorizationserver.Model.Accounts;

import java.util.HashMap;
import java.util.List;

public interface DAO<T> {

    T save(T t);

    boolean update(T t);

    boolean delete(T t);

    T get(Class c, Long id);

    List<T> getAll(Class c);

    List<Accounts> searchBy(Class c, HashMap<String, HashMap<String, String>> criterias);
}