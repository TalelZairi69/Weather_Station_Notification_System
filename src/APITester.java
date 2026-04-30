import java.util.Random;

public class APITester implements WeatherAPI {

    private String condition;

    private int a = 0;
    private float temperature = 15;
    private float humidity = 65;
    private float pressure = 1020;

    private final Random random = new Random();

    @Override
    public void fetchData() {
        float randomDiff = random.nextInt(6) + 2;

            temperature += randomDiff;
            humidity += randomDiff;
            pressure += randomDiff;

        condition = "Cloudy";
    }

    private boolean isNewData() {
        if (a == 0 || a == 1) {
            a++;
            return false;
        }
        a = 0;
        return true;
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