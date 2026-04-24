public class Main {
    public static void main(String[] args) {

        WeatherStation weatherStation = new WeatherStation();
        weatherStation.setWeatherProvider(new WeatherApiTester(weatherStation));
        TVDisplay tv = new TVDisplay(weatherStation);
        PhoneDisplay phone = new PhoneDisplay(weatherStation);
        WebDisplay web = new WebDisplay(weatherStation);
        weatherStation.setStationOnline(true);

        sleep(10000);
        weatherStation.removeObserver(web);
        sleep(10000);
        weatherStation.removeObserver(phone);
        sleep(10000);
        weatherStation.registerObserver(web);
        sleep(10000);
        weatherStation.setStationOnline(false);
    }

    public static void sleep(int sec) {
        try {
            Thread.sleep(sec);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("*** error sleep ***");
        }
    }
}