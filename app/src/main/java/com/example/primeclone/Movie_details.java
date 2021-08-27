package com.example.primeclone;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Movie_details extends AppCompatActivity {
    private boolean btnstar = false, btnheart = false, btnlike = false;
    ImageView movieImage;
    TextView movieName;
    Button playButton;
    ImageButton linkedinBtn,facebtn,twitterbtn,whatsappbtn;
    ImageButton imageButton;
    String mName, mImage, mId, mFileUrl;
    LottieAnimationView lottiestar, lottieheart, lottielike;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        movieImage = findViewById(R.id.movie_image);
        movieName = findViewById(R.id.movie_name);
        playButton = findViewById(R.id.play_button);
        imageButton = findViewById(R.id.sharePrimev);
        lottiestar = findViewById(R.id.star);
        lottieheart = findViewById(R.id.heart);
        lottielike = findViewById(R.id.like);
        linkedinBtn = findViewById(R.id.linkedin);
        facebtn = findViewById(R.id.Facebook);
        twitterbtn = findViewById(R.id.Twitter);
        whatsappbtn = findViewById(R.id.Whatsapp);


        lottiestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnstar) {
                    lottiestar.setMinAndMaxProgress(0.5f, 1.0f);
                    lottiestar.playAnimation();
                    btnstar = false;
                } else {
                    lottiestar.setMinAndMaxProgress(0.0f, 0.5f);
                    lottiestar.playAnimation();
                    btnstar = true;
                }
            }
        });
        lottieheart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lottieheart.playAnimation();
            }
        });

        lottielike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lottielike.playAnimation();
            }
        });


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                String shareB = "Download The application now:-https://play.google.com/store/apps/details?id=com.amazon.avod.thirdpartyclient&hl=en_IN&gl=US";
                String sharesub = "Amazon prime clone";

                i.putExtra(Intent.EXTRA_SUBJECT, sharesub);
                i.putExtra(Intent.EXTRA_TEXT, shareB);

                startActivity(Intent.createChooser(i, "Share using"));
            }
        });
        linkedinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent link = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.linkedin.com/in/gudwin-nayak-d4m3y2000/"));
                startActivity(Intent.createChooser(link,"Follow me on"));
            }
        });
        facebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent link = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/gudwin.nayak/"));
                startActivity(Intent.createChooser(link,"Follow me on"));
            }
        });
        twitterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent link = new Intent(Intent.ACTION_VIEW,Uri.parse("https://twitter.com/godwin40628383"));
                startActivity(Intent.createChooser(link,"Follow me on"));
            }
        });

        whatsappbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent link = new Intent(Intent.ACTION_VIEW,Uri.parse("http://api.whatsapp.com/send?phone=+919284713796"));
                startActivity(Intent.createChooser(link,"Chat with me on"));
            }
        });

        mId = getIntent().getStringExtra("movieId");
        mName = getIntent().getStringExtra("movieName");
        mImage = getIntent().getStringExtra("movieImageUrl");
        mFileUrl = getIntent().getStringExtra("movieFile");

        Glide.with(this).load(mImage).into(movieImage);
        movieName.setText(mName);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Movie_details.this, videoplayer.class);
                i.putExtra("url", mFileUrl);
                startActivity(i);

            }
        });

    }
}