package ObtenerJSonNasa;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DAOJsonPrueba {

    public static void main(String[] args) {
        String apiKey = "pRlxWWDRqBNWpDg8WS54wEfM7lF3Gl7E6gaXAOsc";
        try {
//            Creas un objeto URL que apunta a la API de la NASA con tu apiKey.
            URL url = new URL("https://api.nasa.gov/neo/rest/v1/neo/browse?api_key=" + apiKey);
//            Abres una conexión HTTP (HttpURLConnection) para realizar una solicitud GET a la API.
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

//  Obtienes el código de respuesta (responseCode) de la conexión para verificar si la solicitud fue exitosa (código 200).
            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {
//             connection.getInputStream(): Esto obtiene los datos que vienen de la conexión a la API
//             InputStreamReader: Convierte el flujo de bytes de la respuesta en caracteres.
//             BufferedReader: Lee los caracteres de manera eficiente, línea por línea, desde el flujo de entrada.
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Parsear el JSON -> "convertir" la respuesta de texto (que tiene el formato de un archivo JSON) en algo que el programa pueda entender y manejar
                // Convierte ese texto en un objeto JSON, que es como una estructura de datos organizada en forma de "clave-valor".
                JSONObject jsonResponse = new JSONObject(response.toString());
                // Tengo todos los array de near_earth_objects
                JSONArray nearEarthObjects = jsonResponse.getJSONArray("near_earth_objects");

// TODO para ver como esta estructurado el Json usar este link https://jsonformatter.org/json-pretty-print y
//  usar el formato FORM. Recuerda { es un objeto }, [es un Array]
                // Iterar sobre cada objeto en el array

                for (int i = 0; i < nearEarthObjects.length(); i++) {
                    JSONObject asteroid = nearEarthObjects.getJSONObject(i);
                    String id = asteroid.getString("id");
                    String name = asteroid.getString("name");
                    double magnitud = asteroid.getDouble("absolute_magnitude_h");
                    boolean peligroso = asteroid.getBoolean("is_potentially_hazardous_asteroid");


                    // Imprimir la información del asteroide
                    System.out.println("ID: " + id);
                    System.out.println("Name: " + name);
                    System.out.println("Absolute Magnitude: " + magnitud);
                    System.out.println("Peligroso: " + peligroso);
                    System.out.println("-----------------------------");

                    // Acceder al OBJETO "estimated_diameter" dentro de cada asteroide
                    // CUANDO ES UN OBJETO NO HAY QUE RECORRERLO DENTRO DE UN FOR
                    JSONObject estimated_diameter = asteroid.getJSONObject("estimated_diameter");
                    JSONObject kilometers = estimated_diameter.getJSONObject("kilometers");

                    double kmMin = kilometers.getDouble("estimated_diameter_min");
                    double kmMax = kilometers.getDouble("estimated_diameter_max");
                    double kmMedia = (kmMin + kmMax) / 2;

                    System.out.println("Diametro min " + kmMin);
                    System.out.println("Diametro max " + kmMax);
                    System.out.println("Diametro Media " + kmMedia);


                    // TODO hasta aqui Primera parte
                    // Acceder al array "close_approach_data" dentro de cada asteroide
                    // de cada asteoriede cojo sus fechas de aproximacion
                    JSONArray closeApproachData = asteroid.getJSONArray("close_approach_data");


                    for (int j = 0; j < closeApproachData.length(); j++) {
                        // por cada fecha de ese mismo asteoriode cojo el objeto
                        JSONObject approach = closeApproachData.getJSONObject(j);
                        String closeApproachDate = approach.getString("close_approach_date");


                        // TODO no es necesario para el trabajo de pere es una prueba
                        // Prueba para Acceder al objeto "relative_velocity"
                        JSONObject relativeVelocity = approach.getJSONObject("relative_velocity");
                        String kilometersPerSecond = relativeVelocity.getString("kilometers_per_second");
                        String kilometersPerHour = relativeVelocity.getString("kilometers_per_hour");
                        String milesPerHour = relativeVelocity.getString("miles_per_hour");

                        // Imprimir la información de close_approach_data
                        System.out.println("Close Approach Date: " + closeApproachDate);
                        System.out.println("Relative Velocity (km/s): " + kilometersPerSecond);
                        System.out.println("Relative Velocity (km/h): " + kilometersPerHour);
                        System.out.println("Relative Velocity (mph): " + milesPerHour);


                    }
                    System.out.println("-----------------------------");


                }

                connection.disconnect();
            } else {
                throw new Exception(responseCode + " Error: " + connection.getResponseMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
