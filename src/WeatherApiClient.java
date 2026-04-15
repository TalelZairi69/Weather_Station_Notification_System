import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherApiClient {
    private final String API_KEY = "f2533a22c87b46fe8f5153605262303";
    private final String CITY = "Istanbul";

    public void fetchWeatherData(WeatherData weatherData) {
        try {
            String urlString = "http://api.weatherapi.com/v1/current.json?key=" + API_KEY + "&q=" + CITY;
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

            parseWeather(response.toString(), weatherData);

        } catch (Exception e) {
            System.err.println("Error fetching weather data: " + e.getMessage());
        }
    }

    private void parseWeather(String json, WeatherData weatherData) {
        try {
            String city = extractString(json, "\"name\":\"([^\"]+)\"");
            String region = extractString(json, "\"region\":\"([^\"]+)\"");
            String country = extractString(json, "\"country\":\"([^\"]+)\"");
            String localTime = extractString(json, "\"localtime\":\"([^\"]+)\"");
            String condition = extractString(json, "\"condition\":\\{\"text\":\"([^\"]+)\"");
            String windDir = extractString(json, "\"wind_dir\":\"([^\"]+)\"");

            float temperature = extractFloat(json, "\"temp_c\":([\\d.-]+)");
            float feelsLike = extractFloat(json, "\"feelslike_c\":([\\d.-]+)");
            float humidity = extractFloat(json, "\"humidity\":([\\d.]+)");
            float pressure = extractFloat(json, "\"pressure_mb\":([\\d.]+)");
            float cloud = extractFloat(json, "\"cloud\":([\\d.]+)");
            float uv = extractFloat(json, "\"uv\":([\\d.]+)");
            float windSpeed = extractFloat(json, "\"wind_kph\":([\\d.]+)");
            float visibility = extractFloat(json, "\"vis_km\":([\\d.]+)");
            int isDay = (int) extractFloat(json, "\"is_day\":([\\d]+)");

            weatherData.setExtendedMeasurements(city, region, country, localTime, condition, windDir,
                                                temperature, feelsLike, humidity, pressure, cloud, uv, windSpeed, visibility, isDay);

        } catch (Exception e) {
            System.err.println("Parsing failed: " + e.getMessage());
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