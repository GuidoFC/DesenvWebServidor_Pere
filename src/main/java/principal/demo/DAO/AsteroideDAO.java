package principal.demo.DAO;


import principal.demo.model.Asteroide;

public interface AsteroideDAO extends CrudDao<Asteroide, Long>{
    Asteroide findById_NASA(Long idNAsa);
}
