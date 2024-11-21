package principal.demo.DAO;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import principal.demo.model.Aproach;
import principal.demo.model.Asteroide;

import java.sql.Date;
import java.util.List;

public class AsteroideDaoImpl implements AsteroideDAO {

    SessionFactory myFactory;


    public AsteroideDaoImpl() {

        System.out.println("AsteroideDaoImpl creando objeto");
        // TODO AQUI ESTA EL ERROR
        this.myFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Asteroide.class).buildSessionFactory();

    }

    public void insertFromApiNasa(){
        SessionFactory myFactoryNasa = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Asteroide.class).addAnnotatedClass(Aproach.class)
                .buildSessionFactory();

        Session miSession = myFactoryNasa.openSession();


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
            myFactoryNasa.close();


        }
    }

    @Override
    public List<Asteroide> findAll() {
        System.out.println("Estoy en AsteroideDaoImpl detron del findAll");
        Session miSession = myFactory.openSession();
        List<Asteroide> listaAsteroides = null;
        try {
            // comenzar sesion Subir con pull
            miSession.beginTransaction();

            // consulta de asteroides

            listaAsteroides = miSession.createQuery("from Asteroide", Asteroide.class).getResultList();

            // cuando habre session tienes que poner un commit
            miSession.getTransaction().commit();


        } catch (Exception e) {
            System.out.println("Se ha producido un error en el metodo findAll de AsteroideDAoIMPL");
            if (miSession != null && miSession.getTransaction() != null) {
                miSession.getTransaction().rollback(); // Revertir la transacción en caso de error
            }
        } finally {
            // luego tienes que cerrar la sesion
            miSession.close();
        }
        return listaAsteroides;

    }

    @Override
    public Asteroide findById(Long aLong) {
        Session miSession = myFactory.openSession();
        Asteroide asteroide = null;
        try {
            // comenzar sesion
            miSession.beginTransaction();


            // para obtener un dato u otro ||
            asteroide = miSession.createQuery("from Asteroide as1 where as1.id = :id", Asteroide.class)
                    .setParameter("id", aLong)
                    .uniqueResult();

            // cuando habre session tienes que poner un commit
            miSession.getTransaction().commit();


        } catch (Exception e) {
            System.out.println("Se ha producido un error");
            if (miSession != null && miSession.getTransaction() != null) {
                miSession.getTransaction().rollback(); // Revertir la transacción en caso de error
            }
            throw new RuntimeException(e);
        } finally {
            // luego tienes que cerrar la sesion
            miSession.close();
        }
        return asteroide;

    }

    @Override
    public void save(Asteroide asteroide) {
        Session miSession = myFactory.openSession();

        try {

            miSession.beginTransaction();
            miSession.save(asteroide);
            miSession.getTransaction().commit();
            System.out.println("Insert correctamente");


        } catch (Exception e) {
            System.out.println("Error al insertar");
            System.out.println("Se ha producido un error");
            if (miSession != null && miSession.getTransaction() != null) {
                miSession.getTransaction().rollback(); // Revertir la transacción en caso de error
            }

        } finally {
            miSession.close();


        }
    }

    @Override
    public void update(Asteroide asteroide) {
        Session miSession = myFactory.openSession();
        try {

            miSession.beginTransaction();


            // Obtener el Asteroide existente desde la base de datos usando el ID del objeto proporcionado
            Asteroide miAsteroide = miSession.get(Asteroide.class, asteroide.getId());

            if (miAsteroide != null) {
                // Actualizar los campos del objeto persistente

                miAsteroide.setId_Nasa(asteroide.getId_Nasa());
                miAsteroide.setNombre(asteroide.getNombre());
                miAsteroide.setMagnitud(asteroide.getMagnitud());
                // TODO queda poner el atributo --> diameter_km_average
                miAsteroide.setEsPeligroso(asteroide.isEsPeligroso());
                // TODO mirar si se puede hacer lo de aqui abajo
//                miAsteroide = asteroide;


                miSession.getTransaction().commit();
                System.out.println("Se ha modificado correctamente correctamente");
            } else {
                System.out.println("Asteroide no encontrado con ID: " + asteroide.getId());
            }


        } catch (Exception e) {
            System.out.println("Error al actualizar el asteroide");
            if (miSession.getTransaction() != null) {
                miSession.getTransaction().rollback(); // Revertir la transacción en caso de error
            }
            throw new RuntimeException(e);

        } finally {
            miSession.close();


        }
    }

    @Override
    public void delete(Asteroide asteroide) {
        Session miSession = myFactory.openSession();
        try {

            miSession.beginTransaction();

            Long asteroideID = asteroide.getId();
            // Borrar el registro usando una consulta con un parámetro
            miSession.createQuery("delete from Asteroide where id = :id")
                    .setParameter("id", asteroideID)
                    .executeUpdate();

            miSession.getTransaction().commit();
            System.out.println("Se ha modificado correctamente correctamente");



        } catch (Exception e) {
            System.out.println("Error al eliminar el asteroide");
            if (miSession.getTransaction() != null) {
                miSession.getTransaction().rollback(); // Revertir la transacción en caso de error
            }
            throw new RuntimeException(e);

        } finally {
            miSession.close();


        }
    }

    private static void recorrerAsteroide(List<Asteroide> listaAsteroides) {
        for (Asteroide unAsteroide : listaAsteroides) {
            System.out.println(unAsteroide);
        }
    }
}
