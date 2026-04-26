import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherApiClient implements WeatherProvider {

    WeatherRecordUpdater weatherRecordUpdater;

    private final String API_KEY = "f2533a22c87b46fe8f5153605262303";
    private final String city;

    public WeatherApiClient(String city){
        this.city =city;
    }

    public void setProvider(WeatherRecordUpdater weatherRecordUpdater) {
        this.weatherRecordUpdater = weatherRecordUpdater;
    }

    public void fetchData() {
        System.out.println("*** Fetching Data... ***");
        try {
            String urlString = "http://api.weatherapi.com/v1/current.json?key=" + API_KEY + "&q=" + city;
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            extractData(response.toString());

        } catch (Exception e) {
            System.err.println("Error at WeatherApiClient.fetchWeatherData() ");
        }
    }

    private void extractData(String json) {
        try {
            String city = extractString(json, "\"name\":\"([^\"]+)\"");
            String localTime = extractString(json, "\"localtime\":\"([^\"]+)\"");
            String condition = extractString(json, "\"condition\":\\{\"text\":\"([^\"]+)\"");

            float temperature = extractFloat(json, "\"temp_c\":([\\d.-]+)");
            float humidity = extractFloat(json, "\"humidity\":([\\d.]+)");
            float pressure = extractFloat(json, "\"pressure_mb\":([\\d.]+)");

            weatherRecordUpdater.updateRecord(new WeatherRecord(city, localTime, condition, temperature, humidity, pressure));

        } catch (Exception e) {
            System.err.println("Error at WeatherApiClient.parseWeather()");
        }

    }

    private String extractString(String json, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(json);
        return matcher.find() ? matcher.group(1) : "Unknown";
    }

    private float extractFloat(String json, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(json);
        return matcher.find() ? Float.parseFloat(matcher.group(1)) : 0.0f;
    }


}