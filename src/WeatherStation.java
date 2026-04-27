import java.util.ArrayList;
import java.util.List;

public class WeatherStation implements Subject, WeatherRecordUpdater {

    private final List<Observer> observers;
    private boolean isOnline;
    private Thread onlineThread;

    private WeatherRecord record;
    private WeatherProvider weatherProvider;

    public WeatherStation() {
        this.observers = new ArrayList<>();
        System.out.println("*** WeatherStation Initialized ***");
        setWeatherProvider(new APITester());
    }

    public void setWeatherProvider(WeatherProvider provider) {
        this.weatherProvider = provider;
        this.weatherProvider.setProvider(this);
        System.out.println("*** Weather Provider set to (" + provider.getClass().getSimpleName() + ").");
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
        System.out.println("*** (" + o.getClass().getSimpleName() + ") subscribed to Weather Station ***");
        //System.out.println("Total Number of Observers: " + observers.size());
    }

    @Override
    public void removeObserver(Observer o) {
        System.out.println("*** (" + o.getClass().getSimpleName() + ") unsubscribe from Weather Station ***");
        int i = observers.indexOf(o);
        if (i >= 0) {
            observers.remove(i);
        }else {
            System.err.println("("+o.getClass().getSimpleName()+") is not in the list," +
                               "\ntrying to remove already removed Subscriber");
        }
    }

    @Override
    public void notifyObservers() {
        System.out.println("/// Notifying Observers ///");
        for (Observer observer : observers){
            observer.update(record);
        }
    }

    public void updateRecord(WeatherRecord newRecord) {
        if (!newRecord.equals(record)) {
            System.out.println("/// Updating Weather ///");
            this.record = newRecord;
            notifyObservers();
        } else {
            System.out.println("*** No Change to the weather ***");
        }
    }

    private void setStationOnline(boolean online) {

        if (online) {
            if (!isOnline){
                isOnline = true;
                onlineThread = new Thread(() -> {
                    //System.out.println("creating new Thread");
                    while (isOnline) {
                        weatherProvider.fetchData();
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            break;
                        }
                    }
                });

                onlineThread.start();
                System.out.println("*** Weather Station Is Online ***");
            }else {
                System.out.println("+*+*+*+* Station Is Already Online! +*+*+*++*+");
            }


        } else {
            System.out.println("*** Weather Station Going Offline... ***");
            this.isOnline = false;
            if (onlineThread != null) {
                onlineThread.interrupt();
            }
        }
    }

    public void start() {
        setStationOnline(true);
    }

    public void off() {
        setStationOnline(false);
    }

}