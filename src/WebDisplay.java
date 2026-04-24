public class WebDisplay implements Observer, DisplayElement {

    private String city;
    private String localTime;
    private float temperature;
    private float humidity;
    private float pressure;

    Subject subject;

    public WebDisplay(Subject subject) {
        this.subject = subject;
        subject.registerObserver(this);
    }

    @Override
    public void update(String city, String localTime, String condition, float temperature, float humidity, float pressure) {
        this.city = city;
        this.localTime = localTime;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

    @Override
    public void display() {
        System.out.println("\n________________ Web Display ___________________");
        System.out.println("City: " + city + ", LocalTime:(" + localTime + ")\n" +
                           "Temperature: " + temperature + "°C" +
                           "\nHumidity: " + humidity + "%, Pressure: " + pressure + " hPa");
        System.out.println("_________________________________________________\n");
    }
}
