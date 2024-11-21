//package principal.demo.service;
//
//
//import org.mindrot.jbcrypt.BCrypt;
//import principal.hellodia24.Importante.modelo.Usuario;
//import principal.hellodia24.Importante.modeloDAO.UsuarioDAO;
//
//public class UsuarioService {
//    // encriptar la contraseña
//    // Mé_todo para encriptar la contraseña
//    public static String hashPassword(String plainPassword) {
//        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(12)); // 12 es el nivel de dificultad
//    }
//
//    // Mé_todo para verificar la contraseña
//    public static boolean checkPassword(String plainPassword, String hashedPassword) {
//        return BCrypt.checkpw(plainPassword, hashedPassword);
//    }
//
//    public static boolean existeEmail(String email) {
//        UsuarioDAO usuarioDAO = new UsuarioDAO();
//        return usuarioDAO.existeEmailEnUsuarios(email);
//    }
//
//    public static Usuario obtenerUsuarioPorEmail(String email) {
//        UsuarioDAO usuarioDAO = new UsuarioDAO();
//        return usuarioDAO.getUsuarioPorEmail(email);
//    }
//
//    public void crearUsuario(Usuario usuario) {
//        UsuarioDAO usuarioDAO = new UsuarioDAO();
//        usuarioDAO.crearUsuarioBD(usuario);
//
//    }
//}
