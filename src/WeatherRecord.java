public record WeatherRecord(String city, String localTime, String condition,
                            float temperature, float humidity, float pressure)
{}
/*
        This is a record class that store data based on its parameter, and it is final. can not be rest its values.
        It has:
                1- a get method for each of its parameter E.g. [recordName.dataName()] with no (getDataName).
                2- an equal(dataName). checks if data == data.
                3- to set new data you must set a new record the old one will be lost.
 */