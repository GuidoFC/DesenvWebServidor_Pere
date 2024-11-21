package principal.demo.service;

import principal.demo.DAO.AsteroideDAO;
import principal.demo.DAO.AsteroideDaoImpl;
import principal.demo.model.Asteroide;

import java.util.List;
import java.util.Optional;

public class AsteroideService {

    AsteroideDAO asteroideDao;

    public AsteroideService(AsteroideDAO asteroideDaoInterface) {
        asteroideDao = asteroideDaoInterface;
    }

    public List<Asteroide> getListAsteroideFromService() {
        System.out.println("Estoy en AsteroideService en metodo getListAsteroideFromService");

        // TODO aqui tengo un problema porque no puedo crear un objeto si uso AsteroideDaiImpl
        //  este problema no lo tengo con AsteroideDAOAntiguo!!
        System.out.println("he creado un objeto DAO?");
        List<Asteroide> list = asteroideDao.findAll();

        return list;
    }

    public Asteroide getAsteroideById(long id) {

        Asteroide asteroide = asteroideDao.findById(id);

        return asteroide;
    }


    public void updateAsteroide(Asteroide updatedAsteroide) {
        asteroideDao.update(updatedAsteroide);
//        AsteroideDaoImpl dao = new AsteroideDaoImpl();
//        dao.update(updatedAsteroide);
    }

    public void removeAsteroide(Asteroide asteroide) {
       asteroideDao.delete(asteroide);

    }

    public void removeAllAsteroide(List<Asteroide> listaAsteroide) {
        for (Asteroide asteroide : listaAsteroide){
            asteroideDao.delete(asteroide);
        }


    }

    public void crearAsteroide(Asteroide asteroide) {
        asteroideDao.save(asteroide);
    }
}
