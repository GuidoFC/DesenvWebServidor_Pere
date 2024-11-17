package Services;

import org.json.JSONArray;
import org.json.JSONObject;
import principal.demo.model.Asteroide;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class AsteroideService {
    private ArrayList<Asteroide> listaAsteroide;

    public AsteroideService() {
        this.listaAsteroide = new ArrayList<>();

    }
}

