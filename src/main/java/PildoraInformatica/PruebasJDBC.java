package PildoraInformatica;

import JOANGALMES.config.MySQLConnnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class PruebasJDBC {

    private Connection connetion;
    private static MySQLConnnection instance;

    public PruebasJDBC() {
        try{
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            // DriverManager es una clase de Java que maneja la conexión con bases de datos.
            // El méto_do getConnection se usa para establecer una conexión
            // con la base de datos específica que estás intentando usar
            connetion = DriverManager.getConnection("jdbc:mysql://localhost:3302/nasa", "root", "root");
            if (connetion!=null){
                System.out.println("Conectado con Exito a Mysql");
            }
        }catch (Exception e){
            System.out.println("No funciona la conexion");
            System.err.println(e.getMessage());
        }
    }

    public static MySQLConnnection getInstance(){
        if (instance==null){
            instance = new MySQLConnnection();
        }
        return instance;
    }

    public void desconectar(){
        if (connetion!=null){
            try {
                connetion.close();
            } catch (Exception e) {
                System.err.println( "Error desconectado mysql " +  e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        PruebasJDBC pruebasJDBC = new PruebasJDBC();
        pruebasJDBC.desconectar();

    }
}
