package com.education.ztu.dao;

import java.util.List;

public abstract class AbstractDAO<T> {
    public abstract boolean create(T entity);
    public abstract List<T> findAll();
    public abstract boolean delete(int id);
}