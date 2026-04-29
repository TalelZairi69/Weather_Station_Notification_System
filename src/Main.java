public class Main {
    public static void main(String[] args) {
        WeatherDataSource dataSource = new APITester();
        WeatherStation weatherStation = new WeatherStation(dataSource);
        //weatherStation.setDataSource(new APIClient());


        WebDisplay webDisplay = new WebDisplay(weatherStation);
        PhoneDisplay phoneDisplay = new PhoneDisplay(weatherStation);

        weatherStation.start(5000);
        sleep(6000);
        weatherStation.unsubscribeObserver(webDisplay).temperature();
        sleep(6000);
        weatherStation.unsubscribeObserver(phoneDisplay).humidity();
        sleep(6000);
        weatherStation.subscribeObserver(webDisplay).temperature();
        sleep(60000);
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