public class TVDisplay implements Observer, DisplayElement {

    private Subject weatherStation;
    private WeatherData weatherData;

    public TVDisplay(Subject weatherStation) {
        this.weatherStation = weatherStation;
        weatherStation.registerObserver(this);
    }

    public void unsubscribe() {
        if (this.weatherStation != null) {
            weatherStation.removeObserver(this);
            System.out.println("---- TVDisplay unsubscribed from notifications ----");
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
            System.out.println("TV -> " + weatherData.getCity() +
                               " | Temp: " + weatherData.getTemperature() + "°C" +
                               " | Pressure: " + weatherData.getPressure() + " hPa");
        }
    }
}