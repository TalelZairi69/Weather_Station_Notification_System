public class TVDisplay implements Observer, DisplayElement {

    private String localTime;
    private float temperature;
    private float humidity;
    private float pressure;

    private Subject weatherData;

    public TVDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(String city, String region, String country, String localTime,
                       String condition, String windDir, float temperature, float feelsLike,
                       float humidity, float pressure, float cloud, float uv,
                       float windSpeed, float visibility, int isDay) {
        this.localTime = localTime;
        this.temperature= temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

    @Override
    public void display() {
        System.out.println("\n=== TV Display ===");
        System.out.println("Local Time: " + localTime + " | ");
        System.out.println("Temperature: " + temperature + "°C");
        System.out.println("Humidity: " + humidity + "% | Pressure: " + pressure + " hPa");
        System.out.println("===============================\n");
    }
}