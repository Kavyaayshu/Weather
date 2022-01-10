package com.example.weather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

   String BaseUrl = "https://api.openweathermap.org/data/2.5/";
    public static String AppId = "84422c5f2b51c9fcde79fbd14842d291";



    RecyclerView recyclerView;
    private TextView cityName , countryName;
    private TextView temp;
    String city;
    private EditText userInput;
    Button  addButton;
    private ImageView imageView;
    private WeatherService service;
    //private ArrayList<WeatherModel> weatherModelArrayList;
    private  ArrayList<WeatherResponse> weatherResponses =new ArrayList<>();
    private WeatherAdapter weatherAdapter;
    //ArrayList<String> temp;
   // ArrayList<String> cityName;
   // ArrayList<Integer> images;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // cityName = findViewById(R.id.city);
       //// temp = findViewById(R.id.temperature);
       // countryName = findViewById(R.id.country);
        userInput = findViewById(R.id.input);
        addButton = findViewById(R.id.addButton);
        //  weatherModelArrayList=new ArrayList<>();


         addButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 // Log.v("EditText",userInput.getText().toString());

                 getWeatherInfo(userInput.getText().toString().trim());
             }
         });
    }



     private void getWeatherInfo(String city){

         Retrofit retrofit = new Retrofit.Builder()
                 .baseUrl("https://api.openweathermap.org/data/2.5/")
                 .addConverterFactory(GsonConverterFactory.create())
                 .build();
         service = retrofit.create(WeatherService.class);

        // for(int i=0;i<weatherResponses.size()){
         Call<WeatherResponse> call = service.getCurrentWeatherData(city, AppId);
         call.enqueue(new Callback<WeatherResponse>() {
             @Override
             public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {

               //  temp.setText(response.body().main.getTemp()+" ℃");
                 if (response.code() == 200) {
                     //WeatherResponse weatherResponse= response.body();
                     // weatherModelArrayList=(ArrayList<W>)  response.body();
                     // weatherModelList.main.temp;

                     weatherResponses.add(response.body());


                     createRecyclerView();

                 }
             }

             @Override
             public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t) {
                 t.getLocalizedMessage();
                 Toast.makeText(getApplicationContext(), "failed to retrieve data\ncheck your internet connection", Toast.LENGTH_LONG).show();
             }
         });
     }

    private void createRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        WeatherAdapter adapter = new WeatherAdapter(weatherResponses, this);
        recyclerView.setAdapter( adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}

