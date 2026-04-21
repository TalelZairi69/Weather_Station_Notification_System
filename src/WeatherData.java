import java.util.ArrayList;
public class WeatherData implements Subject {

    private ArrayList observers;
    private String city;
    private String region;
    private String country;
    private String localTime;
    private String condition;
    private String windDir;
    private float temperature;
    private float feelsLike;
    private float humidity;
    private float pressure;
    private float cloud;
    private float uv;
    private float windSpeed;
    private float visibility;
    private int isDay;





    public WeatherData(){
        observers = new ArrayList();
    }

    public void registerObserver (Observer o){
        observers.add(o);
    }

    public void removeObserver (Observer o){
        int i = observers.indexOf(o);
        if (i >= 0) {
            observers.remove(i);
        }

    }

    public void notifyObservers(){
        for(int i = 0; i < observers.size(); i++){
            Observer observer = (Observer) observers.get(i);
            observer.update();
        }
    }

    public void measurementChanged(){
        notifyObservers();
    }
    public void setExtendedMeasurements(String city, String region, String country, String localTime,
                                        String condition, String windDir, float temperature, float feelsLike,
                                        float humidity, float pressure, float cloud, float uv,
                                        float windSpeed, float visibility, int isDay) {
        this.city = city;
        this.region = region;
        this.country = country;
        this.localTime = localTime;
        this.condition = condition;
        this.windDir = windDir;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.humidity = humidity;
        this.pressure = pressure;
        this.cloud = cloud;
        this.uv = uv;
        this.windSpeed = windSpeed;
        this.visibility = visibility;
        this.isDay = isDay;
        measurementChanged();
    }

    public int getNumbersOfObservers(){
        return observers.size();
    }

    public String getCity() {
        return city;
    }

    public String getRegion() {
        return region;
    }

    public String getCountry() {
        return country;
    }

    public String getLocalTime() {
        return localTime;
    }

    public String getCondition() {
        return condition;
    }

    public String getWindDir() {
        return windDir;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getFeelsLike() {
        return feelsLike;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public float getCloud() {
        return cloud;
    }

    public float getUv() {
        return uv;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public float getVisibility() {
        return visibility;
    }

    public int getIsDay() {
        return isDay;
    }
}
