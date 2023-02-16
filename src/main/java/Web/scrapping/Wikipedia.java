package Web.scrapping;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import java.io.IOException;

public class Wikipedia {
    public static void main(String[] args) throws IOException {
        Wikipedia run = new Wikipedia("israel", "en");
    }
    public Wikipedia(String query, String Lang) throws IOException{
        String url = "https://"+ Lang +".wikipedia.org/api/rest_v1/page/summary/" + query.replaceAll(" ", "_");
        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        JSONObject json = new JSONObject(response.toString());
        String firstParagraph = json.getString("extract");
        System.out.println(firstParagraph);
    }
}
