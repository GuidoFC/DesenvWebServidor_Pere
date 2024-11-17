package principal.demo.model.ManyToOne;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import principal.demo.model.Asteroide;

import java.util.List;

public class SelectAsteroideConAproach {
    public static void main(String[] args) {
        SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Asteroide.class).buildSessionFactory();

        Session miSession = myFactory.openSession();

        try{
            // comenzar sesion
            miSession.beginTransaction();

            // consulta de asteroides

            List<Asteroide> listaAsteroides = miSession.createQuery("from Asteroide").getResultList();

            recorrerAsteroide(listaAsteroides);
            System.out.println();

            // cuando habre session tienes que poner un commit
            miSession.getTransaction().commit();



        } catch (Exception e) {
            System.out.println("Se ha producido un error");
            throw new RuntimeException(e);
        }finally {
            miSession.close();
        }
    }

    private static void recorrerAsteroide(List<Asteroide> listaAsteroides) {
        for (Asteroide unAsteroide: listaAsteroides){
            System.out.println(unAsteroide);
        }
    }
}
