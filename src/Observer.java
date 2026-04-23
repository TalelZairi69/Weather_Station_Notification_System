public interface Observer {
    void update(String city, String localTime, String condition, float temperature, float humidity, float pressure);
}
