public class TVDisplay implements Observer,DisplayElement {

    private float temperature;
    private float humidity;
    private float pressure;
    Subject subject;

    public TVDisplay(Subject subject){
        this.subject =subject;
        subject.registerObserver(this);
    }

    @Override
    public void update(String city, String localTime, String condition, float temperature, float feelsLike, float humidity, float pressure) {
        this.temperature=temperature;
        this.humidity=humidity;
        this.pressure=pressure;
        display();
    }

    @Override
    public void display() {
        System.out.printf(
                "+------------------------------+%n" +
                "|         TV Display           |%n" +
                "+------------------------------+%n" +
                "| %-13s : %6.2f %-4s  |%n" +
                "| %-13s : %6.2f %-4s  |%n" +
                "| %-13s : %6.2f %-4s |%n" +
                "+------------------------------+%n",
                "Temperature", temperature, "°C",
                "Humidity", humidity, "%",
                "Pressure", pressure, "hPa"
        );
    }


}
