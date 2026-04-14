import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        WeatherStation station = new WeatherStation();

        PhoneDisplay phone = new PhoneDisplay(station);
        TVDisplay tv = new TVDisplay(station);
        WebDisplay web = new WebDisplay(station);

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        
        System.out.println("Starting Weather Notification System...");

        Runnable fetchTask = new Runnable() {
            int count = 0;

            @Override
            public void run() {
                System.out.println("\n[" + (count + 1) + "] Fetching real weather...");
                station.fetchWeatherData();
                count++;
                
                if (count == 3) {
                    System.out.println("\n*** TV decides it no longer wants updates ***");
                    tv.unsubscribe();
                }
                
                if (count == 5) {
                    System.out.println("\n*** Shutting down simulation ***");
                    phone.unsubscribe();
                    web.unsubscribe();
                    executor.shutdown();
                }
            }
        };

        executor.scheduleAtFixedRate(fetchTask, 0, 3, TimeUnit.SECONDS);
    }
}