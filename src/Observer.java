public interface Observer {
    void update(String city, String region, String country, String localTime,
                String condition, String windDir,
                float temperature, float feelsLike,
                float humidity, float pressure, float cloud, float uv,
                float windSpeed, float visibility, int isDay);
}