package principal.demo.DAO;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import principal.demo.model.Asteroide;

import java.util.List;

public class AsteroideDaoImpl implements AsteroideDAO {

    EntityManager entityManager;
    public AsteroideDaoImpl() {
     EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
     entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public List<Asteroide> findAll() {
        // TODO esto esta hecho por Joan
        String sql = "select a from Asteroide a";
        List<Asteroide> Asteroides = this.entityManager.createQuery(sql, Asteroide.class).getResultList();
        return Asteroides;
    }

    @Override
    public Asteroide findById(Long aLong) {
        return null;

    }

    @Override
    public void save(Asteroide Asteroide) {
        SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Asteroide.class).buildSessionFactory();

        Session miSession = myFactory.openSession();

        try {
            Asteroide asteroide1 = new Asteroide( "MayorPeroNoPeligros", 250.55, 5.26, true);
            miSession.beginTransaction();
            miSession.save(asteroide1);
            miSession.getTransaction().commit();
            System.out.println("Insert correctamente");

            miSession.close();




        } catch (Exception e) {
            System.out.println("Error al insertar");
            throw new RuntimeException(e);

        } finally {
            miSession.close();


        }
    }

    @Override
    public void update(Asteroide Asteroide) {

    }

    @Override
    public void delete(Asteroide Asteroide) {

    }
}
