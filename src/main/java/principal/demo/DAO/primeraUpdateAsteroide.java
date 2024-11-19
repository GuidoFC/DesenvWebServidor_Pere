package principal.demo.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import principal.demo.model.Asteroide;


public class primeraUpdateAsteroide {
    public static void main(String[] args) {


        SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Asteroide.class).buildSessionFactory();

        Session miSession = myFactory.openSession();

        try {
            // TODO 1r formna

            Long ClienteID = 252L;

            miSession.beginTransaction();

            Asteroide miAsteroide = miSession.get(Asteroide.class, ClienteID);

            miAsteroide.setNombre("ModifcarAsteroide");
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
