package com.farmwiseai.weather;

import com.google.gson.annotations.SerializedName;
import java.util.List;



public class WeatherForecast {
    @SerializedName("list")
    private List<WeatherData> list;

    public List<WeatherData> getList() {
        return list;
    }
}

