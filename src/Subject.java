public interface Subject {
    SubscribeHandler subscribeObserver(Observer o);
    SubscribeHandler unsubscribeObserver(Observer o);
    void notifyObservers(DataType dataType);
}