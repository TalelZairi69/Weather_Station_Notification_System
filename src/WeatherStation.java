import java.util.ArrayList;
import java.util.Objects;

public class WeatherStation implements Subject {

    private String city;
    private String localTime;
    private String condition;
    private float temperature;
    private float humidity;
    private float pressure;

    private final ArrayList observers;
    private boolean isOnline;
    private Thread onlineThread;
    private WeatherProvider weatherProvider;

    public WeatherStation() {
        observers = new ArrayList<>();
        this.weatherProvider = new WeatherApiClient(this);
        System.out.println("*** WeatherStation Initialized ***");
    }

    public void setWeatherProvider(WeatherProvider provider) {
        this.weatherProvider = provider;
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
        System.out.println("*** (" + o.getClass().getSimpleName() + ") subscribed to Weather Station ***");
    }

    @Override
    public void removeObserver(Observer o) {
        System.out.println("*** (" + o.getClass().getSimpleName() + ") unsubscribe from Weather Station ***");
        int i = observers.indexOf(o);
        if (i >= 0) {
            observers.remove(i);
        }
    }

    @Override
    public void notifyObserver() {
        System.out.println("/// Notifying Observers ///");
        for (Object o : observers) {
            Observer observer = (Observer) o;
            observer.update(city, localTime, condition, temperature, humidity, pressure);
        }

    }

    public void updateData(String city, String localTime, String condition, float temperature, float humidity, float pressure) {
        if (this.temperature != temperature ||
            this.humidity != humidity ||
            this.pressure != pressure ||
            !Objects.equals(this.condition, condition)) {

            this.city = city;
            this.condition = condition;
            this.temperature = temperature;
            this.localTime = localTime;
            this.humidity = humidity;
            this.pressure = pressure;
            System.out.println("/// Updating Weather Data ///");
            notifyObserver();
        } else {
            System.out.println("*** No Change to the weather ***");
        }
    }

    public void setStationOnline(boolean online) {
        if (online) {
            this.isOnline = true;
            onlineThread = new Thread(() -> {
                while (isOnline) {
                    weatherProvider.fetchData();
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            });

            onlineThread.start();
            System.out.println("*** Weather Station Is Online ***");

        } else {
            System.out.println("*** Weather Station Going Offline... ***");
            this.isOnline = false;
            if (onlineThread != null) {
                onlineThread.interrupt();
            }
        }
    }
}
