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

    private int numbersOfObservers;



    public WeatherData(){
        observers = new ArrayList();
    }

    public void registerObserver (Observer o){
        observers.add(o);
        numbersOfObservers++;
    }

    public void removeObserver (Observer o){
        int i = observers.indexOf(o);
        if (i >= 0) {
            observers.remove(i);
            numbersOfObservers--;
        }

    }

    public void notifyObservers(){
        for(int i = 0; i < observers.size(); i++){
            Observer observer = (Observer) observers.get(i);
            observer.update(city, region, country, localTime, condition, windDir,
                            temperature, feelsLike, humidity, pressure, cloud, uv, windSpeed, visibility, isDay);
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
        return numbersOfObservers;
    }

}
