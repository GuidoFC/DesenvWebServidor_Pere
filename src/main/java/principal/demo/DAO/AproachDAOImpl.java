package principal.demo.DAO;

import JOANGALMES.config.MySQLConnnection;
import principal.demo.model.Aproach;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AproachDAOImpl implements AproachDao {


    private MySQLConnnection mySQLConnnection;

    public AproachDAOImpl() {
        this.mySQLConnnection = MySQLConnnection.getInstance();
    }

    @Override
    public List<Aproach> findAll() {



        return List.of();
    }

    @Override
    public Aproach findById(Long aLong) {
        return null;
    }

    @Override
    public void save(Aproach Aproach) {

    }

    @Override
    public void update(Aproach Aproach) {

    }

    @Override
    public void delete(Aproach Aproach) {

    }
}
