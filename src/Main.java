public class Main {
    public static void main(String[] args) {
        WeatherAPI dataSource = new APIClient();
        WeatherStation weatherStation = new WeatherStation(dataSource);
        weatherStation.setWeatherAPI(new APITester()); // this is for testing it has random values


        PhoneDisplay phoneDisplay = new PhoneDisplay(weatherStation);
        WebDisplay webDisplay = new WebDisplay(weatherStation);

        weatherStation.on(5000); // the number is the refresh rate to fetch new data
        sleep(6000);
        weatherStation.unsubscribeObserver(webDisplay).temperature();
        sleep(6000);
        weatherStation.unsubscribeObserver(phoneDisplay).humidity();
        sleep(6000);
        weatherStation.subscribeObserver(webDisplay).temperature();
        sleep(6000);
        weatherStation.off();

    }
    public static void sleep(int milSec) {
        try {
            Thread.sleep(milSec);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("*** error sleep ***");
        }
    }

}