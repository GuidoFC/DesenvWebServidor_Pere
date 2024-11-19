package principal.demo.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import principal.demo.model.Asteroide;

import java.util.List;

public class SelectAsteroide {
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
            System.out.println("Miramos NoPeligro_MAyor50");

            // para obtener un dato u otro ||
            List<Asteroide> NoPeligro_MAyor50 = miSession.createQuery("from Asteroide as1 where as1.esPeligroso=false " +
                    "or as1.magnitud > 50").getResultList();

            recorrerAsteroide(NoPeligro_MAyor50);
            System.out.println("Fin asteroide no peligroso y mayor a 50");


            // consulta dame solo los asteorides que tiene el ID 252

            // fijate as1.esPeligroso=true que le pongo el nombre como lo tengo definido en Intelij y no como
            // esta deinido en Mysql
            List<Asteroide> listaAsteroidesNoPeligrosos = miSession.createQuery("from Asteroide as1 where as1.esPeligroso=false ").getResultList();

            System.out.println();
            System.out.println("Ver los asteroiodes NO peligrosos:");
            recorrerAsteroide(listaAsteroidesNoPeligrosos);
            // cuando habre session tienes que poner un commit
            miSession.getTransaction().commit();

            // luego tienes que cerrar la sesion
            miSession.close();

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
