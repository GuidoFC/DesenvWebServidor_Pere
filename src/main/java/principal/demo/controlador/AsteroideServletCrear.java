package principal.demo.controlador;

import ObtenerJSonNasa.ChatGept;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import principal.demo.DAO.AsteroideDAOAntiguo;
import principal.demo.model.Aproach;
import principal.demo.model.Asteroide;
import principal.demo.service.AsteroideService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.util.List;

//Esta es una anotación para decirle a Java que esta clase será un "Servlet".
// Un servlet es básicamente una pequeña aplicación que responde a solicitudes en la web.
@WebServlet(
//        Nombre del Servlet
        name = "AsteroideServletCrear",
        // indica que este servlet responderá cuando alguien visite la URL /movie en el servidor.
        urlPatterns = "/crear")

//
// Declara la clase MovieServlet, que extiende HttpServlet, lo que significa que esta clase puede manejar solicitudes HTTP, como GET o POST.
public class AsteroideServletCrear extends HttpServlet {
    private AsteroideDAOAntiguo asteroideDAOAntiguo = new AsteroideDAOAntiguo();
    private AsteroideService asteroideService = new AsteroideService(asteroideDAOAntiguo);

    //    Este méto_do doGet se ejecuta automáticamente cuando alguien hace una solicitud GET a la URL /movie
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("accion");
        System.out.println("Estoy en AsteroideServletCrear CREAR!!! DoGet");

        if ("crearAsteroide".equals(action)) {
            System.out.println("Estoy en MovieServletCrear CREAR!!! DoGet dentro del IF");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/crear-asteroide.jsp");
            dispatcher.forward(request, response);


        } else if ("ApiNasa".equals(action)) {

            SessionFactory sessionFactory;
            final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
            try {
                sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

                try {
                    String apiKey = "pRlxWWDRqBNWpDg8WS54wEfM7lF3Gl7E6gaXAOsc";
                    URL url = new URL("https://api.nasa.gov/neo/rest/v1/neo/browse?api_key=" + apiKey);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");

                    int responseCode = connection.getResponseCode();

                    if (responseCode == 200) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        StringBuilder response1 = new StringBuilder();
                        String line;

                        while ((line = reader.readLine()) != null) {
                            response1.append(line);
                        }
                        reader.close();

                        JSONObject jsonResponse = new JSONObject(response1.toString());
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
                        connection.disconnect();
                    } else {
                        throw new Exception(responseCode + " Error: " + connection.getResponseMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    sessionFactory.close();  // Cerrar el SessionFactory al final
                }

            } catch (Exception e) {
                StandardServiceRegistryBuilder.destroy(registry);
                throw new RuntimeException(e);
            }
            processRequest(request, response);
        } else {
            processRequest(request, response); // Mostrar la lista de películas
        }
    }

    // TODO: Para que sirve el processRequest?????
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Asteroide> listaAsteroide = asteroideService.getListAsteroideFromService();
        //
        request.setAttribute("listaAsteroide", listaAsteroide);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/asteroide-record.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("accion");
        System.out.println("Estoy en MovieSerlet CREAR!!! DoPost");

        if ("crearAsteroide".equals(action)) {


            String nombre = request.getParameter("txtNombre");
            Double magnitud = Double.parseDouble(request.getParameter("txtMagnitud"));
            Double diameterKmAverage = Double.parseDouble(request.getParameter("txtDiameterKmAverage"));
            Boolean esPeligros = Boolean.parseBoolean(request.getParameter("txtPeligroso"));

            Asteroide crearAsteroide = new Asteroide(null, nombre, magnitud, diameterKmAverage, esPeligros);
            asteroideService.crearAsteroide(crearAsteroide);
            response.sendRedirect("litarTodasAsteroides"); // Redirigir a la lista de películas
        }
    }


}
