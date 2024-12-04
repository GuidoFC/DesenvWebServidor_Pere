package principal.demo.service;

import jakarta.json.JsonObject;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NasaApiService {
    private final String API_KEY = "pRlxWWDRqBNWpDg8WS54wEfM7lF3Gl7E6gaXAOsc";

    public NasaApiService() {
    }

    public JSONObject getNasaApi() throws IOException {
        URL url = new URL("https://api.nasa.gov/neo/rest/v1/neo/browse?api_key=" + API_KEY);
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
            connection.disconnect();
            return new JSONObject(response1.toString());
        }else {
            connection.disconnect();
            return null;
        }

    }
}
