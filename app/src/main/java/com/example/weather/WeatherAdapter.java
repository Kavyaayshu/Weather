package com.example.weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private ArrayList<WeatherResponse> weatherResponses;
    Context context;
    LayoutInflater inflater;

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
    public void onBindViewHolder(@NonNull WeatherAdapter.ViewHolder holder, int position) {

        WeatherResponse weatherResponse = weatherResponses.get(position);
        holder.temp.setText( String.valueOf(weatherResponses.get(position).main.temp));
        holder.cityName.setText(weatherResponses.get(position).getName());
        holder.country.setText(weatherResponses.get(position).sys.country);



    }

    @Override
    public int getItemCount() {
        return weatherResponses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView temp , cityName,country;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            temp=itemView.findViewById(R.id.temperature);
            cityName=itemView.findViewById(R.id.city);
            country=itemView.findViewById(R.id.country);
        }
    }
}