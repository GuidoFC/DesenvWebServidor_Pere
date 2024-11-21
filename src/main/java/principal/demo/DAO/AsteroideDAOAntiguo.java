package principal.demo.DAO;

import principal.demo.Config.Conexion;
import principal.demo.model.Asteroide;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//DAO (Data Access Object) es un patrón de diseño que organiza el acceso a la base de datos en una clase específica.
//MovieDAO en tu aplicación se encarga de todas las operaciones relacionadas con la base de datos para las películas.
//Gracias a MovieDAO, otras partes de la aplicación pueden interactuar con los datos de las películas sin preocuparse de los detalles técnicos de la base de datos

public class AsteroideDAOAntiguo implements AsteroideDAO{
    // realizar la conexion con la BD
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    Asteroide asteroide = new Asteroide();


    public List<Asteroide> findAll() {
        ArrayList<Asteroide> list = new ArrayList<>();
        String sql = "select * from Asteroide";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
//   result.get...: Obtiene los valores de cada columna (id, title, description, year) para crear un objeto Movie
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                Double absolute_magnitude = rs.getDouble("absolute_magnitude");
                Double diameter_km_average = rs.getDouble("diameter_km_average");
                Boolean isPotentiallyHazardous = rs.getBoolean("is_potentially_hazardous");


                Asteroide asteroide1 = new Asteroide(id, name, absolute_magnitude, diameter_km_average, isPotentiallyHazardous);

                list.add(asteroide1);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Esto imprimirá la traza completa del error
            System.out.println("AsteroideDAOAntiguo en findAll hay un error");
        }
        System.out.println("AsteroideDAOAntiguo en findAll he devuelvo la lista");
        return list;
    }


    public Asteroide list(int id) {
        return null;
    }


    public Asteroide findById(Long id) {
        Asteroide asteroide1 = null;
        String sql = "SELECT * FROM Asteroide WHERE id = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setLong(1, id); // Configura el valor del parámetro `id` en la consulta
            rs = ps.executeQuery();

            if (rs.next()) {
                // Obtiene los valores de cada columna para crear el objeto Movie
//                Long id = rs.getLong("id");
                String name = rs.getString("name");
                Double absolute_magnitude = rs.getDouble("absolute_magnitude");
                Double diameter_km_average = rs.getDouble("diameter_km_average");
                Boolean isPotentiallyHazardous = rs.getBoolean("is_potentially_hazardous");


                asteroide1 = new Asteroide(id, name, absolute_magnitude, diameter_km_average, isPotentiallyHazardous);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Muestra el error en la consola si ocurre una excepción
        } finally {
            // Cierra recursos (rs, ps, con) aquí si es necesario para evitar fugas de memoria
        }
        return asteroide1;
    }


    public void update(Asteroide asteroide) {

        String sql = "UPDATE Asteroide SET name = ?, absolute_magnitude = ?, diameter_km_average = ?, is_potentially_hazardous = ? WHERE id = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, asteroide.getNombre());       // Configura el nuevo título
            ps.setDouble(2, asteroide.getMagnitud());  // Configura la nueva descripción
            ps.setDouble(3, asteroide.getDiameter_km_average());            // Configura el nuevo año
            ps.setBoolean(4, asteroide.isEsPeligroso());            // Configura el nuevo año
            ps.setLong(5, asteroide.getId());             // Configura el ID para especificar la película a actualizar

            // Ejecuta la actualización y comprueba si se actualizaron filas
            ps.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace(); // Muestra el error en la consola si ocurre una excepción
        } finally {
            // Cierra recursos (rs, ps, con) aquí si es necesario para evitar fugas de memoria
        }

    }


    public void delete(Asteroide asteroide) {
        Long id = asteroide.getId();
        String sql = "DELETE FROM Asteroide WHERE id = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setLong(1, id); // Configura el ID de la película a eliminar

            // Ejecuta la eliminación
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace(); // Muestra el error en la consola si ocurre una excepción
        } finally {
            // Cierra recursos (rs, ps, con) aquí si es necesario para evitar fugas de memoria
        }
    }


//    public Long obtenerUltimoID() {
//        Long ultimoId = null;
////        De esta forma tenemos el ultimo ID
//        String sql = "SELECT MAX(id) AS max_id25 FROM movies";
//
//        try {
//            con = cn.getConnection();
//            ps = con.prepareStatement(sql);
//            rs = ps.executeQuery();
//
//            if (rs.next()) {
//                ultimoId = rs.getLong("max_id25");
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            // Cierra los recursos (rs, ps, con) para evitar fugas de memoria
//        }
//        return ultimoId;
//    }


    public void save(Asteroide crearAsteroide) {
        String sql = "INSERT INTO movies ( name, absolute_magnitude, is_potentially_hazardous) VALUES (?, ?, ?, ?)";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            // TODO creo que no es necesario si tengo Id autoincrement
//            ps.setLong(1, crearAsteroide.getId());
            ps.setString(1, crearAsteroide.getNombre());
            ps.setDouble(2, crearAsteroide.getMagnitud());
            ps.setDouble(3, crearAsteroide.getDiameter_km_average());
            ps.setBoolean(3, crearAsteroide.isEsPeligroso());

            // Ejecuta la inserción
            ps.executeUpdate();
            System.out.println("Asteroide insertada correctamente en la base de datos.");

        } catch (Exception e) {
            e.printStackTrace(); // Muestra el error en la consola si ocurre una excepción
        } finally {
            // Cierra los recursos (ps, con) para evitar fugas de memoria
        }
    }


}
