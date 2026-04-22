public class PhoneDisplay implements Observer,DisplayElement {

    private String city;
    private String localTime;
    private String condition;
    private float temperature;
    private float humidity;
    private float pressure;

    Subject subject;

    public PhoneDisplay(Subject subject){
        this.subject =subject;
        subject.registerObserver(this);
    }

    @Override
    public void update(String city, String localTime, String condition, float temperature, float feelsLike, float humidity, float pressure) {
        this.city=city;
        this.localTime = localTime;
        this.condition=condition;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

    @Override
    public void display() {
        System.out.printf(
                "+------------------------------+%n" +
                "|         Phone Display        |%n" +
                "+------------------------------+%n" +
                "|%22s        |%n"+                  //localtime
                "| %-13s : %6s     |%n" +                 //city
                "| %-13s : %6.2f %-4s  |%n" +           //temp
                "| %-13s : %6s|%n" +                 //condition
                "| %-13s : %6.2f %-4s  |%n" +            //humidity
                "| %-13s : %6.2f %-4s |%n"+             //pressure
                "+------------------------------+%n",
                localTime,
                "City", city,
                "Temperature", temperature, "°C",
                "Condition",condition,
                "Humidity", humidity, "%",
                "Pressure", pressure, "hPa"
        );
    }


}
