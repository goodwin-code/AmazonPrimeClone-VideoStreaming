package com.example.primeclone.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.primeclone.Movie_details;
import com.example.primeclone.R;
import com.example.primeclone.model.BannerMovies;

import java.util.List;

public class BannerPagerAdapter extends PagerAdapter {

    Context context;
    List<BannerMovies> bannerofmovieList;

    public BannerPagerAdapter(Context context, List<BannerMovies> bannerofmovieList) {
        this.context = context;
        this.bannerofmovieList = bannerofmovieList;
    }

    @Override
    public int getCount() {
        return bannerofmovieList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.banner_movie_layout,null);
        ImageView bannerImage = view.findViewById(R.id.banner_image);
        Glide.with(context).load(bannerofmovieList.get(position).getImageUrl()).into(bannerImage);
        container.addView(view);

        bannerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(context, Movie_details.class);
                i.putExtra("movieId",bannerofmovieList.get(position).getId());
                i.putExtra("movieName",bannerofmovieList.get(position).getMovieName());
                i.putExtra("movieImageUrl",bannerofmovieList.get(position).getImageUrl());
                i.putExtra("movieFile",bannerofmovieList.get(position).getFileUrl());
                context.startActivity(i);
            }
        });
        return view;
    }
}
