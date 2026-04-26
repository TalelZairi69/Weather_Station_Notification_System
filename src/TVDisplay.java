public class TVDisplay implements Observer, DisplayElement {

    private WeatherRecord weatherRecord;

    public TVDisplay(Subject subject) {
        subject.addObserver(this);
    }

    @Override
    public void update(WeatherRecord weatherRecord) {
        this.weatherRecord = weatherRecord;
        display();
    }

    @Override
    public void display() {
        System.out.println("\n__________________ Tv Display ___________________");
        System.out.println("Temperature: " + weatherRecord.temperature() + "°C" +
                           "\nHumidity: " + weatherRecord.humidity() + "%, Pressure: " + weatherRecord.pressure() + " hPa");
        System.out.println("_________________________________________________\n");
    }


}
