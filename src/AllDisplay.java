/*
        currently not implemented in Main. this class is for testing all variables.
 */
public class AllDisplay implements Observer, DisplayElement{

    private String city;
    private String region;
    private String country;
    private String localTime;
    private String condition;
    private String windDir;
    private float temperature;
    private float feelsLike;
    private float humidity;
    private float pressure;
    private float cloud;
    private float uv;
    private float windSpeed;
    private float visibility;
    private int isDay;

    private WeatherData weatherData;

    public AllDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update() {
        this.city = weatherData.getCity();
        this.region = weatherData.getRegion();
        this.country = weatherData.getCountry();
        this.localTime = weatherData.getLocalTime();
        this.condition = weatherData.getCondition();
        this.windDir = weatherData.getWindDir();
        this.temperature = weatherData.getTemperature();
        this.feelsLike = weatherData.getFeelsLike();
        this.humidity = weatherData.getHumidity();
        this.pressure = weatherData.getPressure();
        this.cloud = weatherData.getCloud();
        this.uv = weatherData.getUv();
        this.windSpeed = weatherData.getWindSpeed();
        this.visibility = weatherData.getVisibility();
        this.isDay = weatherData.getIsDay();
        display();
    }

    @Override
    public void display() {
        System.out.println("\n=== All Weather information Display ===");
        System.out.println("Location: " + city + ", " + region + ", " + country);
        System.out.println("Local Time: " + localTime + " | " + (isDay == 1 ? "Daytime" : "Nighttime"));
        System.out.println("Condition: " + condition);
        System.out.println("Temperature: " + temperature + "°C (Feels like " + feelsLike + "°C)");
        System.out.println("Wind: " + windSpeed + " kph [" + windDir + "]");
        System.out.println("Humidity: " + humidity + "% | Pressure: " + pressure + " hPa");
        System.out.println("Cloud Cover: " + cloud + "% | UV Index: " + uv);
        System.out.println("Visibility: " + visibility + " km");
        System.out.println("===============================\n");
    }
}