package com.farmwiseai.weather;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText etCityName;
    RecyclerView recyclerView;
    WeatherAdapter weatherAdapter;
    String apikey = "8c196ee8a3d37a560ebc9ea8e3df1ee1"; // Replace with your OpenWeatherMap API key

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCityName = findViewById(R.id.city_et);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        weatherAdapter = new WeatherAdapter();
        recyclerView.setAdapter(weatherAdapter);
    }

    public void getWeatherForCity(View v) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherapi weatherApi = retrofit.create(weatherapi.class);

        String cityName = etCityName.getText().toString().trim();

        if (cityName.isEmpty()) {
            Toast.makeText(this, "Please enter a city name.", Toast.LENGTH_LONG).show();
            return;
        }

        Call<WeatherForecast> forecastCall = weatherApi.getWeatherForecast(cityName, 7, apikey); // Request data for 7 days

        forecastCall.enqueue(new Callback<WeatherForecast>() {
            @Override
            public void onResponse(Call<WeatherForecast> call, Response<WeatherForecast> response) {
                if (response.isSuccessful()) {
                    WeatherForecast forecast = response.body();
                    List<WeatherData> weatherDataList = forecast.getList();
                    weatherAdapter.setWeatherDataList(weatherDataList);
                } else {
                    Toast.makeText(MainActivity.this, "Error: " + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<WeatherForecast> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
