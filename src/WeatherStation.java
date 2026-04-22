import java.util.ArrayList;
import java.util.Objects;

public class WeatherStation implements Subject {

    private String city;
    private String localTime;
    private String condition;
    private float temperature;
    private float feelsLike;
    private float humidity;
    private float pressure;

    private final ArrayList observers;
    private volatile boolean isOnline;
    private Thread pollingThread;
    private final WeatherApiClient api;

    public WeatherStation() {
        observers = new ArrayList<>();
        this.api = new WeatherApiClient(this);
        System.out.println("*** WeatherStation Initialized ***");
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
        System.out.println("*** Added: ("+o.getClass().getSimpleName()+") to the Observer ***");
    }

    @Override
    public void removeObserver(Observer o) {
        System.out.println("*** (" + o.getClass().getSimpleName() + ") was removed from Observer ***");
        int i = observers.indexOf(o);
        if (i >= 0) {
            observers.remove(i);
        }

    }

    @Override
    public void notifyObserver() {
        System.out.println("*** Notifying Observers ***");
        for (Object o : observers) {
            Observer observer = (Observer) o;
            observer.update(city, localTime,condition, temperature, feelsLike, humidity, pressure);
        }

    }

    public void updateData(String city, String localTime, String condition, float temperature, float feelsLike, float humidity, float pressure) {
        if (this.temperature != temperature ||
            this.humidity != humidity ||
            this.pressure != pressure ||
            this.feelsLike != feelsLike ||
            !Objects.equals(this.condition, condition)) {


            this.city = city;
            this.condition = condition;
            this.temperature = temperature;
            this.localTime = localTime;
            this.feelsLike = feelsLike;
            this.humidity = humidity;
            this.pressure = pressure;
            System.out.println("*** Updating Weather Data ***");
            notifyObserver();
        }else {
            System.out.println("*** No Change to the weather ***");
        }
    }

    public void setStationOnline(boolean online) {
        if (online) {
            this.isOnline = true;
            pollingThread = new Thread(() -> {
                while (isOnline) {
                    api.fetchWeatherData();
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            });

            pollingThread.start();
            System.out.println("*** Weather Station Is Online ***");

        } else {
            System.out.println("*** Weather Station Going Offline... ***");
            this.isOnline = false;
            if (pollingThread != null) {
                pollingThread.interrupt();
            }
        }
    }



    }
