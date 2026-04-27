public class Main {
    public static void main(String[] args) {
        String city = "Istanbul";
        WeatherStation weatherStation = new WeatherStation();
        //weatherStation.setWeatherProvider(new APIClient(city));

        TVDisplay tv = new TVDisplay(weatherStation);
        PhoneDisplay phone = new PhoneDisplay(weatherStation);
        WebDisplay web = new WebDisplay(weatherStation);
        weatherStation.start();

        sleep(8000);
        weatherStation.removeObserver(web);
        sleep(8000);
        weatherStation.removeObserver(phone);
        sleep(8000);
        weatherStation.addObserver(web);
        sleep(8000);
        weatherStation.off();
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