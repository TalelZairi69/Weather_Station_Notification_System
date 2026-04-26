public interface WeatherProvider {
    void setProvider(WeatherRecordUpdater updater);
    void fetchData();
}