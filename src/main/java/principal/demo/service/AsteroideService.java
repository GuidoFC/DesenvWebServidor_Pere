package principal.demo.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import principal.demo.DAO.AsteroideDAO;
import principal.demo.model.Aproach;
import principal.demo.model.Asteroide;

import java.sql.Date;
import java.util.List;

public class AsteroideService {

    AsteroideDAO asteroideDao;

    public AsteroideService(AsteroideDAO asteroideDaoInterface) {
        asteroideDao = asteroideDaoInterface;
    }

    public List<Asteroide> getListAsteroideFromService() {
        System.out.println("Estoy en AsteroideService en metodo getListAsteroideFromService");

        // TODO aqui tengo un problema porque no puedo crear un objeto si uso AsteroideDaiImpl
        //  este problema no lo tengo con AsteroideDAOAntiguo!!
        System.out.println("he creado un objeto DAO?");
        List<Asteroide> list = asteroideDao.findAll();

        return list;
    }

    public Asteroide getAsteroideById(long id) {

        Asteroide asteroide = asteroideDao.findById(id);

        return asteroide;
    }


    public void updateAsteroide(Asteroide updatedAsteroide) {
        asteroideDao.update(updatedAsteroide);
//        AsteroideDaoImpl dao = new AsteroideDaoImpl();
//        dao.update(updatedAsteroide);
    }

    public void removeAsteroide(Asteroide asteroide) {
        asteroideDao.delete(asteroide);

    }

    public void removeAllAsteroide(List<Asteroide> listaAsteroide) {
        for (Asteroide asteroide : listaAsteroide) {
            asteroideDao.delete(asteroide);
        }


    }

    public void guardarInformacionNasaApi(JSONObject jsonResponse) {
        SessionFactory sessionFactory;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        try {


            JSONArray nearEarthObjects = jsonResponse.getJSONArray("near_earth_objects");
            for (int i = 0; i < nearEarthObjects.length(); i++) {
                JSONObject asteroid = nearEarthObjects.getJSONObject(i);
                Long idAsteroide = asteroid.getLong("id");
                String name = asteroid.getString("name");
                double magnitud = asteroid.getDouble("absolute_magnitude_h");
                boolean peligroso = asteroid.getBoolean("is_potentially_hazardous_asteroid");

                // Guardar el Asteroide
                Session miSession = sessionFactory.openSession();
                Asteroide asteroide1 = new Asteroide(idAsteroide, name, magnitud, 0, peligroso);
                try {
                    miSession.beginTransaction();
                    miSession.save(asteroide1);
                    miSession.getTransaction().commit();
                    System.out.println("Insert correctamente del Asteroide");
                } catch (Exception e) {
                    miSession.getTransaction().rollback();
                    System.out.println("Error al insertar el Asteroide");
                    e.printStackTrace();
                } finally {
                    miSession.close();
                }

                // Obtener y guardar Aproach
                JSONArray closeApproachData = asteroid.getJSONArray("close_approach_data");
                for (int j = 0; j < closeApproachData.length(); j++) {
                    JSONObject approach = closeApproachData.getJSONObject(j);
                    String closeApproachDate = approach.getString("close_approach_date");
                    Date approachDateFormato = Date.valueOf(closeApproachDate);

                    JSONObject relativeVelocity = approach.getJSONObject("relative_velocity");
                    Float kilometersPerSecond = relativeVelocity.getFloat("kilometers_per_second");

                    Session miSession1 = sessionFactory.openSession();
                    try {
                        miSession1.beginTransaction();
                        Asteroide miAsteroide = miSession1.get(Asteroide.class, idAsteroide);

                        if (miAsteroide != null) {  // Verificar si miAsteroide no es null
                            Aproach miAproachDef = new Aproach(approachDateFormato, kilometersPerSecond, 14.6f, "TierraFalta", miAsteroide);
                            miAsteroide.agregarAproach(miAproachDef);
                            miSession1.save(miAproachDef);
                            miSession1.getTransaction().commit();
                            System.out.println("Insert correctamente del Aproach");
                        } else {
                            System.out.println("No se encontró el Asteroide con id: " + idAsteroide);

                        }

                    } catch (Exception e) {
                        System.out.println("Error al insertar el Aproach");
                        e.printStackTrace();
                    } finally {
                        miSession1.close();
                    }
                }
            }
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
            throw new RuntimeException(e);
        }finally {
            sessionFactory.close();  // Cerrar el SessionFactory al final
        }
    }

    public void crearAsteroide(Asteroide asteroide) {
        asteroideDao.save(asteroide);
    }
}
