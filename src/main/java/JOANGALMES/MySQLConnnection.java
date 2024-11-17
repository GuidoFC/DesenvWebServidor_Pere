package JOANGALMES;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnnection {
    private Connection connetion;
    private static MySQLConnnection instance;

    public MySQLConnnection() {
        try{
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            connetion = DriverManager.getConnection("jdbc:mysql://mysqlfinal:3306/nasa", "root", "root");
            if (connetion!=null){
                System.out.println("Conectado con Exito a Mysql");
            }
        }catch (Exception e){
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
}
