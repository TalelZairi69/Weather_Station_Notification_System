public class WeatherData {
    private final String city;
    private final float temperature;
    private final float humidity;
    private final float pressure;

    public WeatherData(String city, float temperature, float humidity, float pressure) {
        this.city = city;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public String getCity() {
        return city;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}
