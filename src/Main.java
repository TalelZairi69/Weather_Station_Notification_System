public class Main {
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();

        TVDisplay tv = new TVDisplay(weatherStation);
        PhoneDisplay phone = new PhoneDisplay(weatherStation);

        weatherStation.setStationOnline(true);

        try {
            System.out.println("... Waiting 5 seconds before shutting down ...");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Main thread was interrupted.");
        }

        weatherStation.setStationOnline(false);
    }
}