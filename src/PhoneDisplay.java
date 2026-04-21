public class PhoneDisplay implements Observer, DisplayElement {

    private String city;
    private String country;
    private float temperature;
    private float feelsLike;
    private float humidity;

    private WeatherData weatherData;

    public PhoneDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update() {
        this.city = weatherData.getCity();
        this.country = weatherData.getCountry();
        this.temperature = weatherData.getTemperature();
        this.feelsLike = weatherData.getFeelsLike();
        this.humidity = weatherData.getHumidity();
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