public class PhoneDisplay implements Observer, DisplayElement {

    private String city;
    private String country;
    private float temperature;
    private float feelsLike;
    private float humidity;

    private Subject weatherData;

    public PhoneDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(String city, String region, String country, String localTime,
                       String condition, String windDir, float temperature, float feelsLike,
                       float humidity, float pressure, float cloud, float uv,
                       float windSpeed, float visibility, int isDay) {
        this.city = city;
        this.country = country;
        this.temperature= temperature;
        this.feelsLike= feelsLike;
        this.humidity = humidity;
        display();
    }

    @Override
    public void display() {
        System.out.println("\n=== Phone Display ===");
        System.out.println("Location: " + city + ", " + ", " + country);
        System.out.println("Temperature: " + temperature + "°C (Feels like " + feelsLike + "°C)");
        System.out.println("Humidity: " + humidity + "%");
        System.out.println("===============================\n");
    }
}