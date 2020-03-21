package com.blesk.authorizationserver.DAO;

import java.util.List;

public interface DAO<T> {

    T save(T t);

    void update(T t);

    void delete(T t);

    T get(Long id);

    List<T> getAll(int pageNumber, int pageSize);
}