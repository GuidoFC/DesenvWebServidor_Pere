package principal.demo.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class SegundoUpdateAsteroide {
    public static void main(String[] args) {


        SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Asteroide.class).buildSessionFactory();

        Session miSession = myFactory.openSession();

        try {
            // TODO 2n formna


            miSession.beginTransaction();

          miSession.createQuery("update Asteroide set nombre='Hello112' where nombre like 'H%'" ).executeUpdate();
          // Borrar un registro
            miSession.createQuery("delete Asteroide  where id=502" ).executeUpdate();

            miSession.getTransaction().commit();
            System.out.println("Se ha modificado correctamente correctamente");

            miSession.close();




        } catch (Exception e) {
            System.out.println("Error al insertar");
            throw new RuntimeException(e);

        } finally {
            miSession.close();


        }
    }
}
