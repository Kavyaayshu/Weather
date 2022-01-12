package com.example.weather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    String BaseUrl = "https://api.openweathermap.org/data/2.5/";
    public static String AppId = "84422c5f2b51c9fcde79fbd14842d291";


    RecyclerView recyclerView;
    private TextView cityName, countryName, temp;

    String city,name;
    private EditText userInput;
    Button addButton, changeButton, duplicateButton;
    double a;

    private ImageView imageView;

    private WeatherService service;

    ArrayList<WeatherResponse> weatherResponseArrayList;
    ArrayList<String> cityNameList = new ArrayList<String>();

    private WeatherAdapter weatherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        userInput = findViewById(R.id.input);
        addButton = findViewById(R.id.addButton);
        changeButton = findViewById(R.id.change_button);
        duplicateButton=findViewById(R.id.duplicate_button);


        weatherResponseArrayList = new ArrayList<>();
        createRecyclerView();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                city = userInput.getText().toString().trim();
                if (city.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter a string", Toast.LENGTH_LONG).show();
                } else {
                    getWeatherInfo(city);
                }


            }
        });


        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                degToFah();

            }
        });
        duplicateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeDuplicate();
            }
        });


    }

    public void getWeatherInfo(String city) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(WeatherService.class);


        Call<WeatherResponse> call = service.getCurrentWeatherData(city, AppId);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {



               // if(weatherResponseArrayList.size()==0) {
                    WeatherResponse details = response.body();
                    weatherResponseArrayList.add(details);
              //  }
               // else{
                //    for(int i=0;i<weatherResponseArrayList.size();i++){
                //        if(weatherResponseArrayList.get(i).getName().equals(city)){
                  //          Toast.makeText(getApplicationContext(), "duplicate", Toast.LENGTH_LONG).show();

                  //      }
                 //       else{
                  //          WeatherResponse details = response.body();
                 //           weatherResponseArrayList.add(details);
                 //       }
                 //   }
               // }
                        weatherAdapter.notifyDataSetChanged();

                    }


                    @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "failed to retrieve data\ncheck your internet connection", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void createRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        weatherAdapter = new WeatherAdapter(weatherResponseArrayList, this);
        recyclerView.setAdapter(weatherAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    private void degToFah() {

        for (int i = 0; i < weatherResponseArrayList.size(); i++) {
            a = Double.parseDouble(String.valueOf(weatherResponseArrayList.get(i).getMain().temp));
            double b = a - 32;
            double res = b * 5 / 9;
            //double res = ((c * 9) / 5 + 32);
            weatherResponseArrayList.get(i).main.setTemp((int) res );
        }
        weatherAdapter.notifyDataSetChanged();
    }
  private  void removeDuplicate() {
      for (int i = 0; i < weatherResponseArrayList.size(); i++) {
          for (int j = i+1; j < weatherResponseArrayList.size(); j++) {
              if (weatherResponseArrayList.get(i).getName().equals(weatherResponseArrayList.get(j).getName())) {
                  weatherResponseArrayList.remove(j);
              }
          }
          weatherAdapter.notifyDataSetChanged();
      }
  }


}

