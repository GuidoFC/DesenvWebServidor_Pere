package JOANGALMES.DAO;

import JOANGALMES.config.MySQLConnnection;
import JOANGALMES.model.Tipus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TipusDAOImpl implements TipusDao{
    private MySQLConnnection mySQLConnnection;

    public TipusDAOImpl() {
        this.mySQLConnnection = MySQLConnnection.getInstance();
    }

    @Override
    public List<Tipus> findAll() {
        String sql = "select * from tipus";
        try {
            PreparedStatement preparedStatement = this.mySQLConnnection.getConnection().prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Tipus> result = new ArrayList<>();

            while (resultSet.next()) {
                Tipus tipus = new Tipus();
                tipus.setId(resultSet.getLong("id"));
                tipus.setNom(resultSet.getString("nom"));

                result.add((tipus));

            }


        }catch (Exception e){
            System.out.println("problemas con la conexion");
            e.printStackTrace();
        }


        return List.of();
    }

    @Override
    public Tipus findById(Long aLong) {
        return null;
    }

    @Override
    public void save(Tipus tipus) {

    }

    @Override
    public void delete(Tipus tipus) {

    }
}
