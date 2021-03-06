package Teatrus.persistence;

import java.util.ArrayList;
import java.util.List;

public interface IRepository<ID,T> {
    int size();
    void save(T entity);
    void delete(ID id);
    void update(ID id, T entity);
    T findOne(ID id);
    ArrayList<T> findAll();
}
