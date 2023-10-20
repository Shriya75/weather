package com.farmwiseai.weather;
import com.google.gson.annotations.SerializedName;

public class DailyWeather {
    @SerializedName("main")
    private Main main;
    @SerializedName("dt_txt")
    private String date; // Date and time in text format, you can parse this if needed

    public Main getMain() {
        return main;
    }

    public String getDate() {
        return date;
    }
}
