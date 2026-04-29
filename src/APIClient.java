import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class APIClient implements WeatherDataSource {

    private String condition;
    private float temperature;
    private float humidity;
    private float pressure;

    private final String API_KEY = "f2533a22c87b46fe8f5153605262303";
    private final String city = "Istanbul";

    @Override
    public void fetchData() {
        HttpURLConnection conn = null;
        try {
            String urlString = "http://api.weatherapi.com/v1/current.json?key=" + API_KEY + "&q=" + city;
            URL url = new URL(urlString);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                extractData(response.toString());
            }

        } catch (Exception ignored) {
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    private void extractData(String json) {
        try {
            this.condition = extractString(json, "\"condition\":\\{\"text\":\"([^\"]+)\"");
            this.temperature = extractFloat(json, "\"temp_c\":([\\d.-]+)");
            this.humidity = extractFloat(json, "\"humidity\":([\\d.]+)");
            this.pressure = extractFloat(json, "\"pressure_mb\":([\\d.]+)");
        } catch (Exception ignored) {
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

    @Override
    public String condition() {
        return condition;
    }

    @Override
    public float temperature() {
        return temperature;
    }

    @Override
    public float humidity() {
        return humidity;
    }

    @Override
    public float pressure() {
        return pressure;
    }
}