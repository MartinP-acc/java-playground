package notification;

import forecast.WeatherForecast;

public class TvNews implements Observer{

    @Override
    public void updateForecast(WeatherForecast weatherForecast) {
        System.out.println("[Weather Chanel] Pogoda na dzisiaj - temperatura : "+weatherForecast.getTemperature()
                +", ciśnienie : "+weatherForecast.getPressure());
    }
}
