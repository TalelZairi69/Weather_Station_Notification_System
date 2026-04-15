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

    private Subject weatherData;

    public AllDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(String city, String region, String country, String localTime,
                       String condition, String windDir, float temperature, float feelsLike,
                       float humidity, float pressure, float cloud, float uv,
                       float windSpeed, float visibility, int isDay) {
        this.city = city;
        this.region = region;
        this.country = country;
        this.localTime = localTime;
        this.condition = condition;
        this.windDir = windDir;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.humidity = humidity;
        this.pressure = pressure;
        this.cloud = cloud;
        this.uv = uv;
        this.windSpeed = windSpeed;
        this.visibility = visibility;
        this.isDay = isDay;
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