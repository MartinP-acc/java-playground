package notification;

import forecast.WeatherForecast;

public class RadioNews implements Observer {

    @Override
    public void updateForecast(WeatherForecast weatherForecast) {
        System.out.println("[Rock Radio] Pogoda na dzisiaj - temperatura : "+weatherForecast.getTemperature()
                +", ciśnienie : "+weatherForecast.getPressure());
    }
}
