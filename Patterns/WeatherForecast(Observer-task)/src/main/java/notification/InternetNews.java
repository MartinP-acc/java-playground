package notification;

import forecast.WeatherForecast;

public class InternetNews implements Observer {

    @Override
    public void updateForecast(WeatherForecast weatherForecast) {
        System.out.println("[weather.pl] Pogoda na dzisiaj - temperatura : "+weatherForecast.getTemperature()
                +", ciśnienie : "+weatherForecast.getPressure());
    }
}
