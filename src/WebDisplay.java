public class WebDisplay implements Observer, DisplayElement {

    private Subject weatherStation;
    private WeatherData weatherData;

    public WebDisplay(Subject weatherStation) {
        this.weatherStation = weatherStation;
        weatherStation.registerObserver(this);
    }

    public void unsubscribe() {
        if (this.weatherStation != null) {
            weatherStation.removeObserver(this);
            System.out.println("---- WebDisplay unsubscribed from notifications ----");
            this.weatherStation = null;
        }
    }

    @Override
    public void update(WeatherData weatherData) {
        this.weatherData = weatherData;
        display();
    }

    @Override
    public void display() {
        if (weatherData != null) {
            System.out.println("Web -> " + weatherData.getCity() +
                               " | Temp: " + weatherData.getTemperature() + "°C" +
                               " | Humidity: " + weatherData.getHumidity() + "%" +
                               " | Pressure: " + weatherData.getPressure() + " hPa");
        }
    }
}