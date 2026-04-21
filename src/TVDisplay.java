public class TVDisplay implements Observer, DisplayElement {

    private String localTime;
    private float temperature;
    private float humidity;
    private float pressure;

    private WeatherData weatherData;

    public TVDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update() {
        this.localTime = weatherData.getLocalTime();
        this.temperature = weatherData.getTemperature();
        this.humidity = weatherData.getHumidity();
        this.pressure = weatherData.getPressure();
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