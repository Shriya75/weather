package com.farmwiseai.weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {
    private List<WeatherData> weatherDataList;

    public WeatherAdapter() {
        this.weatherDataList = new ArrayList<>();
    }

    public void setWeatherDataList(List<WeatherData> weatherDataList) {
        this.weatherDataList = weatherDataList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View weatherItemView = inflater.inflate(R.layout.daily_weather, parent, false);
        return new ViewHolder(weatherItemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WeatherData weatherData = weatherDataList.get(position);

        holder.temperatureTextView.setText(String.format("Temperature: %.2f °C", weatherData.getMain().getTemp()));
        holder.humidityTextView.setText("Humidity: " + weatherData.getMain().getHumidity() + "%");
        holder.pressureTextView.setText("Pressure: " + weatherData.getMain().getPressure() + " hPa");

        // Add a separator after each set of readings except the last one

    }

    @Override
    public int getItemCount() {
        return weatherDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView temperatureTextView;
        TextView humidityTextView;
        TextView pressureTextView;


        public ViewHolder(View itemView) {
            super(itemView);
            temperatureTextView = itemView.findViewById(R.id.temperatureTextView);
            humidityTextView = itemView.findViewById(R.id.humidityTextView);
            pressureTextView = itemView.findViewById(R.id.pressureTextView);

        }
    }
}
