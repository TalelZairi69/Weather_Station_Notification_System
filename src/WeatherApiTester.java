import java.util.Random;

public class WeatherApiTester implements WeatherProvider {

    private int a = 0;
    private float temperature = 15;
    private float humidity = 65;
    private float pressure = 1020;

    WeatherStation weatherStation;
    private final Random random = new Random();

    public WeatherApiTester(WeatherStation weatherStation) {
        this.weatherStation = weatherStation;
    }

    public void fetchData() {
        paresData();
    }

    public void paresData() {
        float randomDiff = random.nextInt(6) + 2;
        if (isNewData()) {
            temperature += randomDiff;
            humidity += randomDiff;
            pressure += randomDiff;
        }
        String city = "Istanbul";
        String localTime = "2026-04-23  15:55";
        String condition = "Cloudy";
        weatherStation.updateData(city, localTime, condition, temperature, humidity, pressure);
    }

    private boolean isNewData() {
        if (a == 1 || a == 2) {
            a++;
            return false;
        } else if (a > 3) {
            a = 2;
            return false;
        }
        a++;
        return true;
    }

}
