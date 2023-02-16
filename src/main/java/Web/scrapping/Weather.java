package Web.scrapping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Weather {
    public Weather(String choose) throws IOException {
        String url = "https://weather.com/weather/today/l/78c89788573709eebe79ab6397599d86111a202ea82c7646acf5b00e72cc77ae";
        Document web = Jsoup.connect(url).get();
        Elements getData = web.select("span.CurrentConditions--tempValue--MHmYY[data-testid=TemperatureValue]");
        String tempFahrenheit = getData.text();
        String finalFahrenheit = tempFahrenheit.substring(0, tempFahrenheit.length() - 1);
        float tempCelsius = (Float.parseFloat(finalFahrenheit) - 32) * 5/9;
        int celsius = (int)tempCelsius;
        if (choose.equals("C")){
            System.out.println("The current temperature is " + celsius+"°");
        } else {
            System.out.println("The current temperature is " + tempFahrenheit);
        }

    }

    public static void main(String[] args) throws IOException {
        Weather run = new Weather("C");
        Weather run2 = new Weather("F");
    }
}
