package com.example.weather;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private TextView temp,city_name;
    private String data1,data2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);


        temp=findViewById(R.id.temp_second);
        city_name=findViewById(R.id.city_second);
        getData();
        setData();

    }
    private void getData(){
        if( getIntent().hasExtra("temp") && getIntent().hasExtra("cityName")){

            data1= getIntent().getStringExtra("temp");
            data2= getIntent().getStringExtra("cityName");

        }
        else{
            Toast.makeText(this,"No Data",Toast.LENGTH_LONG).show();
        }
    }
    private void setData(){
        temp.setText(data1);
        city_name.setText(data2);
    }
}
