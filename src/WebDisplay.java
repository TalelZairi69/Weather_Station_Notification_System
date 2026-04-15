public class WebDisplay implements Observer, DisplayElement {

    private float humidity;
    private float pressure;
    private float cloud;
    private float uv;
    private float windSpeed;
    private float visibility;
    private int isDay;
    String localTime;

    private Subject weatherData;

    public WebDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(String city, String region, String country, String localTime,
                       String condition, String windDir, float temperature, float feelsLike,
                       float humidity, float pressure, float cloud, float uv,
                       float windSpeed, float visibility, int isDay) {
        this.humidity = humidity;
        this.pressure= pressure;
        this.cloud = cloud;
        this.uv = uv;
        this.windSpeed  =windSpeed ;
        this.visibility=visibility ;
        this.isDay=isDay;
        this.localTime=localTime;
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