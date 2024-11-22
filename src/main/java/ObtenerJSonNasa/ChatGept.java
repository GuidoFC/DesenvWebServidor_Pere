package ObtenerJSonNasa;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.json.JSONArray;
import org.json.JSONObject;
import principal.demo.model.Aproach;
import principal.demo.model.Asteroide;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;


public class ChatGept {
    public static void main(String[] args) {
        ChatGept obj = new ChatGept();
        obj.procesarDatosNASA();
    }

    public ChatGept() {
    }

    public void procesarDatosNASA()  {
        String apiKey = "pRlxWWDRqBNWpDg8WS54wEfM7lF3Gl7E6gaXAOsc";
        SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Asteroide.class).addAnnotatedClass(Aproach.class)
                .buildSessionFactory();

        try {
            URL url = new URL("https://api.nasa.gov/neo/rest/v1/neo/browse?api_key=" + apiKey);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                JSONObject jsonResponse = new JSONObject(response.toString());
                JSONArray nearEarthObjects = jsonResponse.getJSONArray("near_earth_objects");

                for (int i = 0; i < nearEarthObjects.length(); i++) {
                    JSONObject asteroid = nearEarthObjects.getJSONObject(i);
                    Long idAsteroide = asteroid.getLong("id");
                    String name = asteroid.getString("name");
                    double magnitud = asteroid.getDouble("absolute_magnitude_h");
                    boolean peligroso = asteroid.getBoolean("is_potentially_hazardous_asteroid");

                    // Guardar el Asteroide
                    Session miSession = myFactory.openSession();
                    Asteroide asteroide1 = new Asteroide(idAsteroide, name, magnitud, 0, peligroso);
                    try {
                        miSession.beginTransaction();
                        miSession.save(asteroide1);
                        miSession.getTransaction().commit();
                        System.out.println("Insert correctamente del Asteroide");
                    } catch (Exception e) {
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

                        Session miSession1 = myFactory.openSession();
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
                connection.disconnect();
            } else {
                throw new Exception(responseCode + " Error: " + connection.getResponseMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myFactory.close();  // Cerrar el SessionFactory al final
        }
    }
}

