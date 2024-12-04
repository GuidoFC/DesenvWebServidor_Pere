package principal.demo.DAO;

import principal.demo.model.Aproach;
// funciona?
public interface AproachDao extends CrudDao<Aproach, Long> {
    // Si hacemos el extends no hace falta hacer lo de aqui abajo:
//    List<Tipus> findAll();
//    Tipus findById(Long id);
//    void save(Tipus tipus);
//    void delete(Tipus tipus);

}
