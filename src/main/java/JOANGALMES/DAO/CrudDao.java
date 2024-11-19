package JOANGALMES.DAO;

import JOANGALMES.model.Tipus;

import java.util.List;

public interface CrudDao <T, ID> {
    List<T> findAll();
    T findById(ID id);
    void save(T t);
    void delete(T t);
}
