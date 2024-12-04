package principal.demo.service;

import principal.demo.DAO.AproachDAOAntiguo;
import principal.demo.DAO.AsteroideDAO;
import principal.demo.model.Aproach;
import principal.demo.model.Asteroide;

import java.util.List;

public class AproachService {

    AproachDAOAntiguo aproachDAOAntiguo;

    public AproachService() {
        aproachDAOAntiguo = new AproachDAOAntiguo();
    }

    public List<Aproach> getListAproachFromService(Long id_asteroide) {
        System.out.println("Estoy en AproachService en metodo getListAproachFromService");

        System.out.println("he creado un objeto DAO?");
        List<Aproach> list = aproachDAOAntiguo.findAll(id_asteroide);

        return list;
    }



    public void removeAsteroide(Aproach aproach) {
//        aproachDAOAntiguo.delete(asteroide);

    }


}


