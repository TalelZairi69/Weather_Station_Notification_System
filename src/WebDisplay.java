public class WebDisplay implements Observer, DisplayElement {

    private float humidity;
    private float pressure;
    private float cloud;
    private float uv;
    private float windSpeed;
    private float visibility;
    private int isDay;
    String localTime;

    private WeatherData weatherData;

    public WebDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update() {
        this.humidity = weatherData.getHumidity();
        this.pressure = weatherData.getPressure();
        this.cloud = weatherData.getCloud();
        this.uv = weatherData.getUv();
        this.windSpeed = weatherData.getWindSpeed();
        this.visibility = weatherData.getVisibility();
        this.isDay = weatherData.getIsDay();
        this.localTime = weatherData.getLocalTime();
        display();
    }

    @Override
    public void display() {
        System.out.println("\n=== Web Display ===");
        System.out.println("Local Time: " + localTime + " | " + (isDay == 1 ? "Daytime" : "Nighttime"));
        System.out.println("Wind: " + windSpeed + " kph");
        System.out.println("Humidity: " + humidity + "% | Pressure: " + pressure + " hPa");
        System.out.println("Cloud Cover: " + cloud + "% | UV Index: " + uv);
        System.out.println("Visibility: " + visibility + " km");
        System.out.println("===============================\n");
    }
}