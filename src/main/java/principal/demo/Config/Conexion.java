package principal.demo.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    Connection con;

    public Conexion() {
        try {
//            Este controlador permite que Java se conecte a bases de datos MySQL.
            Class.forName("com.mysql.cj.jdbc.Driver");
//            Crea una conexión a la base de datos movies usando el usuario root y la contraseña root
            con = DriverManager.getConnection("jdbc:mysql://mysqlfinal:3306/nasa", "root", "root");

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Connection getConnection(){
        return con;
    }

}
