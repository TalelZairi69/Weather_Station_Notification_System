import java.util.Random;

public class APITester implements WeatherProvider {

    private int a = 0;
    private float temperature = 15;
    private float humidity = 65;
    private float pressure = 1020;

    WeatherRecordUpdater weatherRecordUpdater;
    private final Random random = new Random();

    public void setProvider(WeatherRecordUpdater weatherRecordUpdater) {
        this.weatherRecordUpdater = weatherRecordUpdater;
    }

    public void fetchData() {
        System.out.println("*** Fetching Data... ***");
        extractData();
    }

    private void extractData() {
        float randomDiff = random.nextInt(6) + 2;
        if (isNewData()) {
            temperature += randomDiff;
            humidity += randomDiff;
            pressure += randomDiff;
        }
        String city = "Istanbul";
        String localTime = "2026-04-23  15:55";
        String condition = "Cloudy";
        weatherRecordUpdater.updateRecord(new WeatherRecord(city, localTime, condition, temperature, humidity, pressure));
    }

    // 0,1 is false. 2 is true
    private boolean isNewData() {
        if (a == 0 || a == 1) {
            a++;
            return false;
        }
        a = 0;
        return true;
    }

}
