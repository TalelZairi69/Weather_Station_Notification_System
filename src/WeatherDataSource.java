public interface WeatherDataSource {
    void fetchData();
    String condition();
    float temperature();
    float humidity();
    float pressure();
}