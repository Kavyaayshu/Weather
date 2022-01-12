package com.example.weather;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {


    private ArrayList<WeatherResponse> weatherResponses;
    Context context;
    LayoutInflater inflater;
    String url = "https://openweathermap.org/img/wn/10d@2x.png";

    public WeatherAdapter(ArrayList<WeatherResponse> weatherResponses, Context context) {
       this.weatherResponses=weatherResponses;
        this.context = context;
        this.inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =inflater.inflate(R.layout.grid_layout,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        WeatherResponse weatherResponse = weatherResponses.get(position);
        holder.temp.setText(String.valueOf(weatherResponses.get(position).main.temp)+"°C");
        holder.cityName.setText(weatherResponses.get(position).getName());
        holder.country.setText(weatherResponses.get(position).sys.country);
        // url = weatherResponses.get(position).getImgUrl();

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.mipmap.ic_launcher_round);
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(holder.imageView);

        holder.layout.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){
                Intent intent = new Intent(context,SecondActivity.class);
                intent.putExtra("temp",weatherResponses.get(position).main.temp +"°C");
                intent.putExtra("cityName",weatherResponses.get(position).getName());
                context.startActivity(intent);
            }
        });




    }

    @Override
    public int getItemCount() {
        return weatherResponses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public View layout;
        TextView temp , cityName,country;
        ImageView imageView;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            temp=itemView.findViewById(R.id.temperature);
            cityName=itemView.findViewById(R.id.city);
            country=itemView.findViewById(R.id.country);
            imageView=itemView.findViewById(R.id.imageView);
            layout=itemView.findViewById(R.id.grid);
        }
    }
}