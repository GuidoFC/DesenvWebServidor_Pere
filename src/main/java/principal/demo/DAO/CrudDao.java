package principal.demo.DAO;

import java.util.List;

public interface CrudDao<T, ID> {
    List<T> findAll();

    T findById(ID id);

    void save(T t);

    void update(T t);

    void delete(T t);
}
