public interface WeatherAPI {
    void fetchData();
    String condition();
    float temperature();
    float humidity();
    float pressure();
}
