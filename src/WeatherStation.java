import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherStation implements Subject {

    private final List<Observer> observers = new CopyOnWriteArrayList<>();
    private WeatherData currentWeatherData;

    private final String API_KEY = "f2533a22c87b46fe8f5153605262303";
    private final String CITY = "Istanbul";

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        if (currentWeatherData == null) return;
        for (Observer o : observers) {
            o.update(currentWeatherData);
        }
    }

    public void setWeatherData(WeatherData weatherData) {
        this.currentWeatherData = weatherData;
        notifyObservers();
    }

    public void fetchWeatherData() {
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

            parseWeather(response.toString());

        } catch (Exception e) {
            System.err.println("Error fetching weather data: " + e.getMessage());
        }
    }

    private void parseWeather(String json) {
        try {
            String city = "Unknown";
            float temperature = 0.0f;
            float humidity = 0.0f;
            float pressure = 0.0f;

            Matcher cityMatcher = Pattern.compile("\"name\":\"([^\"]+)\"").matcher(json);
            if (cityMatcher.find()) city = cityMatcher.group(1);

            Matcher tempMatcher = Pattern.compile("\"temp_c\":([\\d.]+)").matcher(json);
            if (tempMatcher.find()) temperature = Float.parseFloat(tempMatcher.group(1));

            Matcher humidityMatcher = Pattern.compile("\"humidity\":([\\d.]+)").matcher(json);
            if (humidityMatcher.find()) humidity = Float.parseFloat(humidityMatcher.group(1));

            Matcher pressureMatcher = Pattern.compile("\"pressure_mb\":([\\d.]+)").matcher(json);
            if (pressureMatcher.find()) pressure = Float.parseFloat(pressureMatcher.group(1));

            setWeatherData(new WeatherData(city, temperature, humidity, pressure));

        } catch (Exception e) {
            System.err.println("Parsing failed: " + e.getMessage());
        }
    }
}