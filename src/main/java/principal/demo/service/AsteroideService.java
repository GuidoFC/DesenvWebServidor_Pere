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
                Long id_Asteroide_Nasa = asteroid.getLong("id");
                String name = asteroid.getString("name");
                double magnitud = asteroid.getDouble("absolute_magnitude_h");
                boolean peligroso = asteroid.getBoolean("is_potentially_hazardous_asteroid");

                JSONObject diametro = asteroid.getJSONObject("estimated_diameter");

                // TODO metodo
                double  diameter_km_avararge = calcularDiamtroEstimado(diametro);
                // Guardar el Asteroide
                Session miSession = sessionFactory.openSession();
                Asteroide asteroide1 = new Asteroide(id_Asteroide_Nasa, name, magnitud, diameter_km_avararge, peligroso);
                try {
                    miSession.beginTransaction();
                    miSession.save(asteroide1);
                    miSession.getTransaction().commit();
                    System.out.println("Insert correctamente del Asteroide usando Api nasa");
                } catch (Exception e) {
                    miSession.getTransaction().rollback();
                    System.out.println("Error al insertar el Asteroide usando Api nasa");
                    e.printStackTrace();
                } finally {
                    miSession.close();
                }

                // Obtener y guardar Aproach
                JSONArray closeApproachData = asteroid.getJSONArray("close_approach_data");
                for (int j = 0; j < closeApproachData.length(); j++) {
                    JSONObject approach = closeApproachData.getJSONObject(j);
                    String closeApproachDate = approach.getString("close_approach_date");
                    // Transformo un String en una Fecha - DATE
                    Date approachDateFormato = Date.valueOf(closeApproachDate);

                    JSONObject relativeVelocity = approach.getJSONObject("relative_velocity");
                    Float kilometersPerSecond = relativeVelocity.getFloat("kilometers_per_second");

                    // TODO nuevo
                    String orbiting_body = approach.getString("orbiting_body");
                    JSONObject miss_distance = approach.getJSONObject("miss_distance");
                    Float dintancia_luna = miss_distance.getFloat("lunar");

                    Session miSession1 = sessionFactory.openSession();
                    try {
                        miSession1.beginTransaction();
                        Asteroide miAsteroide = miSession1.get(Asteroide.class, id_Asteroide_Nasa);

                        if (miAsteroide != null) {  // Verificar si miAsteroide no es null
                            Aproach miAproachDef = new Aproach(approachDateFormato, kilometersPerSecond, dintancia_luna, orbiting_body, miAsteroide);
                            miAsteroide.agregarAproach(miAproachDef);
                            miSession1.save(miAproachDef);
                            miSession1.getTransaction().commit();
                            System.out.println("Insert correctamente del Aproach");
                        } else {
                            System.out.println("No se encontró el Asteroide con id: " + id_Asteroide_Nasa);

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
        } finally {
            sessionFactory.close();  // Cerrar el SessionFactory al final
        }
    }

    public void crearAsteroide(Asteroide asteroide) {
        asteroideDao.save(asteroide);
    }

    private double calcularDiamtroEstimado(JSONObject diametro) {
        JSONObject kilometros = diametro.getJSONObject("kilometers");

        double diametro_min = kilometros.getDouble("estimated_diameter_min");
        double diametro_max = kilometros.getDouble("estimated_diameter_max");

        double media = (diametro_max + diametro_min) / 2;
        return media;
    }
}
