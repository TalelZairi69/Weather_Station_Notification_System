public class PhoneDisplay implements Observer {

    private Subject weatherStation;

    public PhoneDisplay(Subject weatherStation) {
        this.weatherStation = weatherStation;
        weatherStation.registerObserver(this);
    }

    public void unsubscribe() {
        if (this.weatherStation != null) {
            weatherStation.removeObserver(this);
            System.out.println("---- PhoneDisplay unsubscribed from notifications ----");
            this.weatherStation = null;
        }
    }

    @Override
    public void update(WeatherData weatherData) {
        System.out.println("Phone -> " + weatherData.getCity() +
                           " | Temp: " + weatherData.getTemperature() + "°C" +
                           " | Humidity: " + weatherData.getHumidity() + "%");
    }
}