public class WebDisplay implements Observer, DisplayElement {

    private WeatherRecord weatherRecord;
    Subject subject;

    public WebDisplay(Subject subject) {
        this.subject = subject;
        subject.addObserver(this);
    }

    public void unsubscribe(Subject subject) {
        subject.removeObserver(this);
    }

    @Override
    public void update(WeatherRecord weatherRecord) {
        this.weatherRecord = weatherRecord;
        display();
    }

    @Override
    public void display() {
        System.out.println("=================== Web Display =================");
        System.out.println("City: " + weatherRecord.city() + ", LocalTime:(" + weatherRecord.localTime() + ")\n" +
                           "Temperature: " + weatherRecord.temperature() + "°C" +
                           "\nHumidity: " + weatherRecord.humidity() + "%, Pressure: " + weatherRecord.pressure() + " hPa");
        System.out.println("=================================================");
    }
}
