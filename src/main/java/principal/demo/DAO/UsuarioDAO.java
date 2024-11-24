package principal.demo.DAO;

import principal.demo.Config.Conexion;
import principal.demo.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {
    // realizar la conexion con la BD
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;


    
    public void crearUsuarioBD(Usuario crearUsuario) {
        String sql = "INSERT INTO Usuario ( nombre, email, contrasena) VALUES ( ?, ?, ?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, crearUsuario.getNombre());
            ps.setString(2, crearUsuario.getEmail());
            ps.setString(3, crearUsuario.getContrasena());

            // Ejecuta la inserción
            ps.executeUpdate();
            System.out.println("Usuario insertado correctamente en la base de datos.");

        } catch (Exception e) {
            e.printStackTrace(); // Muestra el error en la consola si ocurre una excepción
        } finally {
            // Cierra los recursos (rs, ps, con) para evitar fugas de memoria
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public Usuario getUserByID(int id) {
        return null;
    }

    
    public void modificarUsuario(Usuario updatedMovie) {

    }

    
    public void eliminarUsuario(int id) {

    }




// TODO vale la pena hacer este metodo o basta q en la BD tenga la columna email como unique
    public boolean existeEmailEnUsuarios(String email) {
// SELECT 1 -> si encuentra el email devolver 1
        String sql = "SELECT 1 FROM Usuario WHERE email = ?";
        boolean existe = false;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, email);  // Configura el email en el PreparedStatement
            rs = ps.executeQuery();

            // Si se obtiene algún resultado, significa que el email existe en la tabla
            if (rs.next()) {
                existe = true;
            }
        } catch (
                Exception e) {
            e.printStackTrace(); // Muestra el error en la consola si ocurre una excepción
        } finally {
            // Cierra los recursos (rs, ps, con) para evitar fugas de memoria
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return existe;
    }

    public Usuario getUsuarioPorEmail(String email) {
        Usuario usuario = null;
        String sql = "SELECT * FROM Usuario WHERE email = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setEmail(rs.getString("email"));
                usuario.setContrasena(rs.getString("contrasena")); // La contraseña ya está cifrada en la BD
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return usuario;
    }
}
