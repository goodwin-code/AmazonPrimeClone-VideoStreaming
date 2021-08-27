package com.example.primeclone.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.primeclone.Movie_details;
import com.example.primeclone.R;
import com.example.primeclone.model.Categoryitem;

import java.util.List;

public class itemRecyclerAdapter extends RecyclerView.Adapter<itemRecyclerAdapter.ItemViewHolder> {

    Context context;
    List<Categoryitem> categoryitemList;

    public itemRecyclerAdapter(Context context, List<Categoryitem> categoryitemList) {
        this.context = context;
        this.categoryitemList = categoryitemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.cat_row_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder,final int position) {
        Glide.with(context).load(categoryitemList.get(position).getImageUrl()).into(holder.itemImage);
        holder.itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i =new Intent(context, Movie_details.class);
                i.putExtra("movieId",categoryitemList.get(position).getId());
                i.putExtra("movieName",categoryitemList.get(position).getMovieName());
                i.putExtra("movieImageUrl",categoryitemList.get(position).getImageUrl());
                i.putExtra("movieFile",categoryitemList.get(position).getFileUrl());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryitemList.size();
    }

    public static final class ItemViewHolder extends RecyclerView.ViewHolder{

        ImageView itemImage;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.item_image);
        }
    }
}
