import java.util.*;

public class WeatherStation implements Subject {

    private WeatherDataSource dataSource;
    public final Map<DataType, List<Observer>> mapObservers = new HashMap<>();

    private String condition;
    private float temperature;
    private float humidity;
    private float pressure;

    private volatile boolean isOnline;
    private Thread onlineThread;

    public WeatherStation(WeatherDataSource dataSource) {
        System.out.println("weather station initialized");
        this.dataSource = dataSource;
        mapObservers.put(DataType.CONDITION_TYPE, new ArrayList<>());
        mapObservers.put(DataType.TEMPERATURE_TYPE, new ArrayList<>());
        mapObservers.put(DataType.HUMIDITY_TYPE, new ArrayList<>());
        mapObservers.put(DataType.PRESSURE_TYPE, new ArrayList<>());
    }


    public void setDataSource(WeatherDataSource newDataSource) {
        this.dataSource = newDataSource;
    }

    @Override
    public SubscribeHandler subscribeObserver(Observer observer) {
        return new SubscribeHandler(this.mapObservers, observer, true);
    }

    @Override
    public SubscribeHandler unsubscribeObserver(Observer observer) {
        return new SubscribeHandler(this.mapObservers, observer, false);
    }

    @Override
    public void notifyObservers(DataType dataType) {
        Object data;        // Object data: non-primitive (reference) data type
        switch (dataType) {
            case CONDITION_TYPE:
                data = condition;
                break;
            case TEMPERATURE_TYPE:
                data = temperature;
                break;
            case HUMIDITY_TYPE:
                data = humidity;
                break;
            case PRESSURE_TYPE:
                data = pressure;
                break;
            default:
                data = null;
                break;
        }

        for (Observer observer : mapObservers.get(dataType)) {
            observer.update(dataType, data);
        }
    }

    private void DataChecker() {
        if (!Objects.equals(condition, dataSource.condition())) {
            this.condition = dataSource.condition();
            notifyObservers(DataType.CONDITION_TYPE);
        }
        if (temperature != dataSource.temperature()) {
            this.temperature = dataSource.temperature();
            notifyObservers(DataType.TEMPERATURE_TYPE);
        }
        if (humidity != dataSource.humidity()) {
            this.humidity = dataSource.humidity();
            notifyObservers(DataType.HUMIDITY_TYPE);
        }
        if (pressure != dataSource.pressure()) {
            this.pressure = dataSource.pressure();
            notifyObservers(DataType.PRESSURE_TYPE);
        }
    }

    private void caller() {
        System.out.println("=====================================");
        dataSource.fetchData();
        DataChecker();
    }

    private void setStationOnline(boolean online, int RefreshRateInMilSec) {
        if (online) {
            if (!isOnline) {
                isOnline = true;
                onlineThread = new Thread(() -> {
                    while (isOnline && !Thread.currentThread().isInterrupted()) {
                        caller();
                        try {
                            Thread.sleep(RefreshRateInMilSec);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            break;
                        }
                    }
                });
                onlineThread.start();
                System.out.println("*** Weather Station Is Online ***");
            }
        } else {
            this.isOnline = false;
            if (onlineThread != null) {
                onlineThread.interrupt();
            }
            System.out.println("*** Weather Station Going Offline... ***");
        }
    }

    public void start(int RefreshRateInMilSec) {
        setStationOnline(true, RefreshRateInMilSec);
    }

    public void off() {
        setStationOnline(false, 1000);
    }
}