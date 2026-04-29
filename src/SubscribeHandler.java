import java.util.List;
import java.util.Map;

public class SubscribeHandler {
    private final Map<DataType, List<Observer>> mapObservers;
    private final Observer observer;
    private final boolean isSubscribe;

    public SubscribeHandler(Map<DataType, List<Observer>> mapObservers, Observer observer, boolean isSubscribe) {
        this.mapObservers = mapObservers;
        this.observer = observer;
        this.isSubscribe = isSubscribe;
    }

    private void process(DataType dataType) {
        if (isSubscribe) { // if true, add observer. if false, remove observer
            System.out.println("*** " + observer.getClass().getSimpleName() + " subscribed to " + dataType + " ***");
            //checks if the observer is inside the list of a given dataType, if NOT adds the observer.
            if (mapObservers.containsKey(dataType) && !mapObservers.get(dataType).contains(observer)) {
                mapObservers.get(dataType).add(observer);
            }
        } else {
            System.out.println("\n*** " + observer.getClass().getSimpleName() + " unsubscribed from " + dataType + " ***\n");
            if (mapObservers.containsKey(dataType)) {
                mapObservers.get(dataType).remove(observer);
            }
        }
    }

    public void condition() {
        process(DataType.CONDITION_TYPE);
    }

    public void temperature() {
        process(DataType.TEMPERATURE_TYPE);
    }

    public void humidity() {
        process(DataType.HUMIDITY_TYPE);
    }

    public void pressure() {
        process(DataType.PRESSURE_TYPE);
    }

    public void all() {
        process(DataType.CONDITION_TYPE);
        process(DataType.TEMPERATURE_TYPE);
        process(DataType.HUMIDITY_TYPE);
        process(DataType.PRESSURE_TYPE);
    }
}