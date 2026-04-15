public class Main {
    public static void main(String[] args) throws InterruptedException {
        WeatherData weatherData = new WeatherData();
        WeatherApiClient apiClient = new WeatherApiClient();

        PhoneDisplay phone = new PhoneDisplay(weatherData);
        TVDisplay tv = new TVDisplay(weatherData);
        WebDisplay web = new WebDisplay(weatherData);

        System.out.println("\n *** All displays registered ***");
        System.out.println("--- Number of Subscribers:(" + weatherData.getNumbersOfObservers() + ")---");
        apiClient.fetchWeatherData(weatherData);
        Thread.sleep(3000);


        weatherData.removeObserver(phone);
        System.out.println("\n*** PhoneDisplay Unsubscribed ***");
        System.out.println("--- Number of Subscribers:(" + weatherData.getNumbersOfObservers() + ")---");
        apiClient.fetchWeatherData(weatherData);
        Thread.sleep(3000);

        weatherData.removeObserver(tv);
        System.out.println("\n*** TVDisplay Unsubscribed ***");
        System.out.println("--- Number of Subscribers:(" + weatherData.getNumbersOfObservers() + ")---");
        apiClient.fetchWeatherData(weatherData);
        Thread.sleep(3000);

        weatherData.removeObserver(web);
        weatherData.registerObserver(tv);
        System.out.println("\n*** WebDisplay Unsubscribed & TVDisplay Subscribed ***");
        System.out.println("--- Number of Subscribers:(" + weatherData.getNumbersOfObservers() + ")---");
        apiClient.fetchWeatherData(weatherData);
    }
}