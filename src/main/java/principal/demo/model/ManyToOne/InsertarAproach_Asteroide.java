package principal.demo.model.ManyToOne;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import principal.demo.model.Aproach;
import principal.demo.model.Asteroide;

import java.sql.Date;

public class InsertarAproach_Asteroide {
    public static void main(String[] args) {
        SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Asteroide.class).addAnnotatedClass(Aproach.class)
                .buildSessionFactory();

        Session miSession = myFactory.openSession();


        try {

            miSession.beginTransaction();

            Date approachDate = Date.valueOf("2035-11-17"); // Formato: "yyyy-MM-dd"
            Date approachDate2 = Date.valueOf("1996-11-17");
            Date approachDate3 = Date.valueOf("2000-11-17");

            // obtener el asteroide de la tabla asteroide de la BBDD
            Asteroide miAsteroide = miSession.get(Asteroide.class, 252);

            // crear Avistamiento
            Aproach miAproach = new Aproach(approachDate, 1.6f, 14.6f, "Tierra", miAsteroide);
            Aproach miAproach2 = new Aproach(approachDate2, 16.6f, 14.6f, "Marte", miAsteroide);
            Aproach miAproach3 = new Aproach(approachDate3, 1655.6f, 14.6f, "Venus", miAsteroide);

            miAsteroide.agregarAproach(miAproach);
            miAsteroide.agregarAproach(miAproach2);
            miAsteroide.agregarAproach(miAproach3);

            // guardar los aproach en la base de datos

            miSession.save(miAproach);
            miSession.save(miAproach2);
            miSession.save(miAproach3);

            miSession.getTransaction().commit();
            System.out.println("Insert correctamente");

            miSession.close();


        } catch (
                Exception e) {
            System.out.println("Error al insertar");
            throw new RuntimeException(e);

        } finally {
            miSession.close();
            myFactory.close();


        }
    }

}
