package principal.demo.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class SaveAsteroide {
    public static void main(String[] args) {


        SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Asteroide.class).buildSessionFactory();

        Session miSession = myFactory.openSession();

        try {
            Asteroide asteroide1 = new Asteroide(null, "Pepe", 2.85, 5.26, true);
            miSession.beginTransaction();
            miSession.save(asteroide1);
            miSession.getTransaction().commit();
            System.out.println("Insert correctamente");

        } catch (Exception e) {
            System.out.println("Error al insertar");
            throw new RuntimeException(e);

        } finally {
            miSession.close();


        }
    }
}
