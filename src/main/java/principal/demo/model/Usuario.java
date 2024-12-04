package principal.demo.model;

import lombok.Data;

@Data
public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private String contrasena;
    private Rol rol;


    public Usuario() {}

    public Usuario(int id, String nombre, String email, String contrasena, Rol rol) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        this.rol = rol;

    }


}
