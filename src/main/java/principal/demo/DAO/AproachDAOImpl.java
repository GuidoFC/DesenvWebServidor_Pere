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
        // TODO esto esta hecho por Joan, tengo que modificarlo
        String sql = "select * from Aproach";
        try {
            PreparedStatement preparedStatement = this.mySQLConnnection.getConnection().prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Aproach> result = new ArrayList<>();

            while (resultSet.next()) {
                Aproach Aproach = new Aproach();
                Aproach.setId(resultSet.getLong("id"));
//                Aproach.setNom(resultSet.getString("nom"));

                result.add((Aproach));

            }


        }catch (Exception e){
            System.out.println("problemas con la conexion");
            e.printStackTrace();
        }


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
