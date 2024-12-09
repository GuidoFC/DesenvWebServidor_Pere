package principal.demo.DAO.JDBC;

import principal.demo.Config.Conexion;
import principal.demo.model.Aproach;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AproachDAOAntiguo {
    // realizar la conexion con la BD
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List<Aproach> findAll(Long id_asteroide) {
        ArrayList<Aproach> list = new ArrayList<>();
        String sql = "select * from Aproach where asteroid_id = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setLong(1, id_asteroide);
            rs = ps.executeQuery();
            while (rs.next()) {
//   result.get...: Obtiene los valores de cada columna (id, title, description, year) para crear un objeto Movie
                Long id = rs.getLong("id");


                Date date = rs.getDate("approach_date");

                Float velocity = rs.getFloat("velocity");
                Float distance = rs.getFloat("distance");
                String orbiting_body = rs.getString("orbiting_body");

//                 Long asteroid_id = rs.getLong("asteroid_id");


                Aproach aproach = new Aproach(id, date, velocity,distance, orbiting_body);

                list.add(aproach);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Esto imprimirá la traza completa del error
            System.out.println("AsteroideDAOAntiguo en findAll hay un error");
        }
        System.out.println("AsteroideDAOAntiguo en findAll he devuelvo la lista");
        return list;
    }
}
