package JOANGALMES.service;

import JOANGALMES.DAO.TipusDao;
import JOANGALMES.model.Tipus;

import java.util.List;

public class TipusService {
    TipusDao tipusDao;

    // Le estoy pasando una Interficie para aplicar la injeccion de indepencencia
    public TipusService(TipusDao tipusDao) {
        this.tipusDao = tipusDao;
    }

    public List<Tipus> findAll() {
        return tipusDao.findAll();
    }
}
