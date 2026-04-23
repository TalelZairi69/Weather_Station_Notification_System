public class TVDisplay implements Observer, DisplayElement {

    private float temperature;
    private float humidity;
    private float pressure;
    Subject subject;

    public TVDisplay(Subject subject) {
        this.subject = subject;
        subject.registerObserver(this);
    }

    @Override
    public void update(String city, String localTime, String condition, float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

    @Override
    public void display() {
        System.out.println("\n__________________ Tv Display ___________________");
        System.out.println("Temperature: " + temperature + "°C" +
                           "\nHumidity: " + humidity + "%, Pressure: " + pressure + " hPa");
        System.out.println("_________________________________________________\n");
    }


}
