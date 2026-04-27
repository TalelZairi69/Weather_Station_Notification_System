public class PhoneDisplay implements Observer, DisplayElement {

    private WeatherRecord weatherRecord;

    public PhoneDisplay(Subject subject) {
        subject.addObserver(this);
    }

    @Override
    public void update(WeatherRecord weatherRecord) {
        this.weatherRecord = weatherRecord;
        display();
    }

    @Override
    public void display() {
        System.out.println("================= Phone Display =================");
        System.out.println("City: " + weatherRecord.city() + ", LocalTime:(" + weatherRecord.localTime() + ")\n" +
                           "Temperature: " + weatherRecord.temperature() + "°C, Condition: " + weatherRecord.condition() +
                           "\nHumidity: " + weatherRecord.humidity() + "%, Pressure: " + weatherRecord.pressure() + " hPa");
        System.out.println("=================================================");
    }


}
