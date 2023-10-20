package com.farmwiseai.weather;


import com.google.gson.annotations.SerializedName;

public class WeatherData {
    @SerializedName("main")
    private Main main;

    public Main getMain() {
        return main;
    }
}
