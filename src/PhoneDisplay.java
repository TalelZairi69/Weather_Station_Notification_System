public class PhoneDisplay implements Observer {

    private String condition;
    private float temperature;
    private float humidity;
    private float pressure;

    private int update;

    public PhoneDisplay(Subject subject) {
        subject.subscribeObserver(this).all();
    }

    @Override
    public void update(DataType dataType, Object data) {

        switch (dataType) {
            case CONDITION_TYPE:
                this.condition = (String) data;
                update = 1;
                break;
            case TEMPERATURE_TYPE:
                this.temperature = (Float) data;
                update = 2;
                break;
            case HUMIDITY_TYPE:
                this.humidity = (Float) data;
                update = 3;
                break;
            case PRESSURE_TYPE:
                this.pressure = (Float) data;
                update = 4;
                break;
        }
        display();
    }

    public void display() {

        if (update == 1) {
            System.out.println("Phone Display received notification -> Condition: " + condition);
        } else if (update == 2) {
            System.out.println("Phone Display received notification -> Temperature: " + temperature);
        } else if (update == 3) {
            System.out.println("Phone Display received notification -> Humidity: " + humidity);
        } else if (update == 4) {
            System.out.println("Phone Display received notification -> Pressure: " + pressure);
        }

    }
}