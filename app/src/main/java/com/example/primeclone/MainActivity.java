package com.example.primeclone;

import static com.example.primeclone.R.color.amazon;
import static com.example.primeclone.R.color.white;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.primeclone.adapter.BannerPagerAdapter;
import com.example.primeclone.adapter.MainRecyclerAdapter;
import com.example.primeclone.model.AllCategory;
import com.example.primeclone.model.BannerMovies;
import com.example.primeclone.model.Categoryitem;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {
    BannerPagerAdapter bannerPagerAdapter;
    TabLayout IndicatorTab,categoryTab;
    ViewPager bannerViewPage;
    List<BannerMovies> homeBannerList;
    List<BannerMovies> tvShowBannerList;
    List<BannerMovies> movieBannerList;
    List<BannerMovies> kidsBannerList;
    NestedScrollView nestedScrollView;
    AppBarLayout appBarLayout;
    TabLayout tabLayout;
    Categoryitem categoryitem;
    BannerMovies bannerMovies;
    MainRecyclerAdapter mainRecyclerAdapter;
    RecyclerView mainRecycler;
    List<AllCategory> allCategoryList;
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    Toolbar tb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Sprite doubleBounce = new DoubleBounce();
        tabLayout= (TabLayout)findViewById(R.id.tabLayout);

        tb = (Toolbar)findViewById(R.id.Toolbar);
        setSupportActionBar(tb);

        nav=(NavigationView)findViewById(R.id.navMenu);
        drawerLayout =(DrawerLayout)findViewById(R.id.navDraw);

        toggle =new ActionBarDrawerToggle(this,drawerLayout,tb,R.string.Open,R.string.Close);
        drawerLayout.addDrawerListener(toggle);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            toggle.getDrawerArrowDrawable().setColor(getColor(white));
        }else {
            toggle.getDrawerArrowDrawable().setColor(getResources().getColor(white));
        }
        toggle.syncState();

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home1:
                        Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.search:
                        Toast.makeText(getApplicationContext(), "search", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.Download:
                        Toast.makeText(getApplicationContext(), "download", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.User:
                        Toast.makeText(getApplicationContext(), "My stuff", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                }
                return true;
            }
        });



        IndicatorTab = findViewById(R.id.tab_indicator);
        categoryTab = findViewById(R.id.tabLayout);
        nestedScrollView = findViewById(R.id.nested_scroll);
        appBarLayout = findViewById(R.id.appbar);





        homeBannerList = new ArrayList<>();
        homeBannerList.add(new BannerMovies(1,"The Tommorow War","https://cutt.ly/cQIvWNq","https://storage.googleapis.com/amazonclone2/THE%20TOMORROW%20WAR%20_%20Official%20Trailer%20_%20Prime%20Video.mp4"));
        homeBannerList.add(new BannerMovies(2,"The family Man2","https://cutt.ly/gQIvU6J","https://storage.googleapis.com/amazonclone2/The%20Family%20Man%20Season%202%20-%20Official%20Trailer%204K%20_%20Raj%20%26%20DK%20_%20Manoj%20Bajpayee%2C%20Samantha%20_Amazon%20Original.mp4"));
        homeBannerList.add(new BannerMovies(3,"The courier","https://cutt.ly/HQIzXhE","https://storage.googleapis.com/amazonclone2/THE%20COURIER%20Trailer%20(2021).mp4"));
        homeBannerList.add(new BannerMovies(4,"Cruel summer","https://cutt.ly/fQIzB2m","https://storage.googleapis.com/amazonclone2/CRUEL%20SUMMER%20Official%20Trailer%20(2021).mp4"));
        homeBannerList.add(new BannerMovies(5,"Without remorse","https://cutt.ly/fQIz2g8","https://storage.googleapis.com/amazonclone2/Without%20Remorse%20-%20Official%20Trailer%20_%20Prime%20Video.mp4"));

        tvShowBannerList = new ArrayList<>();
        tvShowBannerList.add(new BannerMovies(1,"The last hour","https://cutt.ly/LQOkeef","https://storage.googleapis.com/amazonclone2/The%20Last%20Hour%20-%20Official%20Trailer%20_%20Sanjay%20Kapoor%2C%20Shahana%20Goswami%2C%20Raima%20Sen%20_%20Amazon%20Original.mp4"));
        tvShowBannerList.add(new BannerMovies(2,"Hostel Daze Season: 2","https://upload.wikimedia.org/wikipedia/en/5/54/Hostel_Daze_Official_Poster.jpg","https://storage.googleapis.com/amazonclone2/Hostel%20Daze%20Season%202%20-%20Official%20Trailer%20_%20Amazon%20Original.mp4"));
        tvShowBannerList.add(new BannerMovies(3,"DC's legends of tomorrow","https://cutt.ly/JQIxcHA","https://storage.googleapis.com/amazonclone2/DC's%20LEGENDS%20OF%20TOMORROW%20Trailer%20(2016).mp4"));
        tvShowBannerList.add(new BannerMovies(4,"Kung fu panda: The paws of destiny","https://cutt.ly/NQIxWgh","https://storage.googleapis.com/amazonclone2/Kung%20Fu%20Panda_%20The%20Paws%20of%20Destiny%20Season%201%20-%20Official%20Trailer%20_%20Prime%20Video%20Kids.mp4"));
        tvShowBannerList.add(new BannerMovies(5,"Chacha vidhayak hain humare","https://cutt.ly/zQIxTwi","https://storage.googleapis.com/amazonclone2/Chacha%20Vidhayak%20Hain%20Humare%20Season%202%20-%20Official%20Trailer%20_%20Zakir%20Khan%20_%20Amazon%20Prime%20Video%20_%20March%2026.mp4"));

        movieBannerList = new ArrayList<>();
        movieBannerList.add(new BannerMovies(1,"Udaan","https://cutt.ly/EQIxXdY","https://storage.googleapis.com/amazonclone2/Udaan%20-%20Official%20Trailer%20_%20Suriya%2C%20Aparna%20_%20Sudha%20Kongara%20_%20GV%20Prakash%20_%20Amazon%20Prime%20Video_%20April%204.mp4"));
        movieBannerList.add(new BannerMovies(2,"Tenet","https://cutt.ly/pQIxBdZ","https://storage.googleapis.com/amazonclone2/TENET%20-%20Final%20Trailer.mp4"));
        movieBannerList.add(new BannerMovies(3,"Saina","https://cutt.ly/eQIxM3K","https://storage.googleapis.com/amazonclone2/Saina_%20Official%20Trailer%20_%20Parineeti%20Chopra%20_%20Bhushan%20Kumar%20_%20Releasing%2026%20March%202021.mp4"));
        movieBannerList.add(new BannerMovies(4,"The witches","https://cutt.ly/AQIx2OA","https://storage.googleapis.com/amazonclone2/The%20Witches%20-%20Official%20Trailer.mp4"));
        movieBannerList.add(new BannerMovies(5,"Wonder woman","https://cutt.ly/YQIx4kC","https://storage.googleapis.com/amazonclone2/Wonder%20Woman%201984%20_%20Young%20Diana%20Takes%20on%20The%20Amazon%20Games%20_%20Warner%20Bros.%20Entertainment.mp4"));

        kidsBannerList = new ArrayList<>();
        kidsBannerList.add(new BannerMovies(1,"Motu patlu kung fu king returns","https://cutt.ly/FQIciGi","https://storage.googleapis.com/amazonclone2/y2mate.com%20-%20Motu%20Patlu%20Cartoons%20In%20Hindi%20%20%20Animated%20movie%20%20Motu%20patlu%20Kungfu%20king%20returns%20%20Wow%20Kidz_720p.mp4"));
        kidsBannerList.add(new BannerMovies(2,"Inspector Chingum","https://rb.gy/gytt0u","https://storage.googleapis.com/amazonclone2/Inspector%20Chingum%20_%20Animated%20Kids%20Series%20_%20Official%20Trailer%20_%20Amazon%20Prime%20Video.mp4"));
        kidsBannerList.add(new BannerMovies(3,"Kung-Fu_panda","https://rb.gy/3mg1qq","https://storage.googleapis.com/amazonclone2/Kung%20Fu%20Panda%20(2008)%20-%20Fight%20for%20the%20Dragon%20Scroll%20Scene%20(9_10)%20_%20Movieclips.mp4"));
        kidsBannerList.add(new BannerMovies(4,"Ben-10","https://rb.gy/fxfnnt","https://storage.googleapis.com/amazonclone2/Ben%2010%20Reboot%20_%20Every%20Alien%20Transformation.mp4"));
        kidsBannerList.add(new BannerMovies(5,"Sonic the hedgehog","https://cutt.ly/0QIcsS2","https://storage.googleapis.com/amazonclone2/Sonic%20the%20Hedgehog%20-%20Tails'%20Happy%20Meal%202!.mp4"));


        setBannerPagerAdapter(homeBannerList);


        categoryTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
           @Override
           public void onTabSelected(TabLayout.Tab tab) {
               switch (tab.getPosition()){


                   case 1:
                       setScrollDefaultState();
                       setBannerPagerAdapter(tvShowBannerList);

                       return;

                   case 2:
                       setScrollDefaultState();
                       setBannerPagerAdapter(movieBannerList);
                       return;

                   case 3:
                       setScrollDefaultState();
                       setBannerPagerAdapter(kidsBannerList);
                       return;

                   default:
                       setScrollDefaultState();
                       setBannerPagerAdapter(homeBannerList);
               }


           }



           @Override
           public void onTabUnselected(TabLayout.Tab tab) {

           }

           @Override
           public void onTabReselected(TabLayout.Tab tab) {

           }
       });







        List<Categoryitem> homeCatListItem1 = new ArrayList<>();
        homeCatListItem1.add(new Categoryitem(1,"The vigil", "https://bit.ly/3s1UaAZ","https://storage.googleapis.com/amazonclone1/y2mate.com%20-%20THE%20VIGIL%202020%20Official%20Trailer%20HD%20SUPERNATURAL_1080p.mp4"));
        homeCatListItem1.add(new Categoryitem(2,"Jolt", "https://bit.ly/3s7V1jp","https://storage.googleapis.com/amazonclone1/y2mate.com%20-%20JOLT%20%20Official%20Trailer%20%20Prime%20Video_1080p.mp4"));
        homeCatListItem1.add(new Categoryitem(3,"V", "https://bit.ly/3s3CWTG","https://storage.googleapis.com/amazonclone1/y2mate.com%20-%20V%20%20Official%20Trailer%20Hindi%20%20Nani%20Sudheer%20Babu%20Aditi%20Rao%20Hydari%20Nivetha%20Thomas%20%20April%204_1080p.mp4"));
        homeCatListItem1.add(new Categoryitem(4,"Train to busan", "https://bit.ly/3yEOcZa","https://storage.googleapis.com/amazonclone1/y2mate.com%20-%20Train%20to%20Busan%20Official%20Trailer%201%202016%20Yoo%20Gong%20Korean%20Zombie%20Movie%20HD_1080p.mp4"));
        homeCatListItem1.add(new Categoryitem(5,"The Wretched", "https://bit.ly/3xulc4R","https://storage.googleapis.com/amazonclone1/y2mate.com%20-%20THE%20WRETCHED%20Trailer%202020_1080pFHR.mp4"));
        homeCatListItem1.add(new Categoryitem(6,"Jurassic park-III", "https://bit.ly/2VJwjtq","https://storage.googleapis.com/amazonclone1/Jurassic%20Park%203%20(1_10)%20Movie%20CLIP%20-%20Crash%20Landing%20(2001)%20HD.mp4"));
        homeCatListItem1.add(new Categoryitem(7,"Final destination-5", "https://bit.ly/37szsAL","https://storage.googleapis.com/amazonclone1/Final%20Destination%205%20Official%20Trailer%20%231%20-%20(2011)%20HD.mp4"));
        homeCatListItem1.add(new Categoryitem(9,"Cold skin", "https://bit.ly/2VEoXHZ","https://storage.googleapis.com/amazonclone1/y2mate.com%20-%20Cold%20Skin%20Trailer%202017%20Mystery%20Horror_1080p.mp4"));
        homeCatListItem1.add(new Categoryitem(10,"Shershaah", "https://bit.ly/3CwfN0S","https://storage.googleapis.com/amazonclone1/y2mate.com%20-%20Shershaah%20%20Official%20Trailer%20%20Vishnu%20Varadhan%20%20Sidharth%20Malhotra%20Kiara%20Advani%20%20Aug%2012_1080p.mp4"));
        homeCatListItem1.add(new Categoryitem(11,"Tumbbad", "https://bit.ly/3yzesEp","https://storage.googleapis.com/amazonclone1/y2mate.com%20-%20Tumbbad%20%20Official%20Trailer%202018%20%20Sohum%20Shah%20%20Aanand%20L%20Rai%20%20In%20Cinemas%20Now_1080p.mp4"));
        homeCatListItem1.add(new Categoryitem(12,"Asuran", "https://bit.ly/3xzGbDF","https://storage.googleapis.com/amazonclone1/y2mate.com%20-%20Asuran%20%20Official%20Trailer%20%20Dhanush%20%20Vetri%20Maaran%20%20G%20V%20Prakash%20Kumar%20%20Kalaippuli%20S%20Thanu_1080p.mp4"));
        homeCatListItem1.add(new Categoryitem(13,"Ouija", "https://bit.ly/3AuujEv","https://storage.googleapis.com/amazonclone1/Ouija%20Official%20Trailer%20%231%20(2014)%20-%20Olivia%20Cooke%20Horror%20Movie%20HD.mp4"));
        homeCatListItem1.add(new Categoryitem(14,"lights out", "https://bit.ly/3lOfEzV","https://storage.googleapis.com/amazonclone1/y2mate.com%20-%20Lights%20Out%20%20Official%20Trailer%20HD_1080p.mp4"));
        homeCatListItem1.add(new Categoryitem(15,"San andreas", "https://bit.ly/3ixtRiQ","https://storage.googleapis.com/amazonclone1/y2mate.com%20-%20San%20Andreas%20%20Official%20Trailer%202%20HD_1080p.mp4"));


        List<Categoryitem> homeCatListItem2 = new ArrayList<>();
        homeCatListItem2.add(new Categoryitem(1,"Toofan", "https://bit.ly/3xyDZfx","https://storage.googleapis.com/amazonclone3/Toofaan%20-%20Official%20Trailer%202021%20_%20Farhan%20Akhtar%2C%20Mrunal%20Thakur%2C%20Paresh%20Rawal%20_%20Amazon%20Prime%20Video.mp4"));
        homeCatListItem2.add(new Categoryitem(2,"Judas and the black messiah", "https://bit.ly/3xA7WvM","https://storage.googleapis.com/amazonclone3/Judas%20and%20the%20Black%20Messiah_%20Official%20Trailer%20(2020)%20-%20Daniel%20Kaluuya%2C%20LaKeith%20Stanfield.mp4"));
        homeCatListItem2.add(new Categoryitem(3,"Pride and prejudice and zombies", "https://bit.ly/3jxtGU0","https://storage.googleapis.com/amazonclone3/Pride%20and%20Prejudice%20and%20Zombies%20(2016)%20-%20Zombie%20Killers%20Scene%20(1_10)%20_%20Movieclips.mp4"));
        homeCatListItem2.add(new Categoryitem(4,"Sarpatta parambarai", "https://bit.ly/3Cvmtwr","https://storage.googleapis.com/amazonclone3/Sarpatta%20Parambarai%20-%20Official%20Trailer%20(Tamil)%20_%20Amazon%20Prime%20Video.mp4"));
        homeCatListItem2.add(new Categoryitem(5,"Narappa", "https://bit.ly/3iykFeb","https://storage.googleapis.com/amazonclone3/Narappa%20-%20Official%20Trailer%20_%20Venkatesh%2C%20Priyamani%2C%20Rao%20Ramesh%2C%20Nassar%20_%20Amazon%20Prime%20Video.mp4"));
        homeCatListItem2.add(new Categoryitem(6,"Kaashi in search of ganga", "https://bit.ly/3fMexgx","https://storage.googleapis.com/amazonclone3/y2mate.com%20-%20Kaashi%20%20Official%20Trailer%20%20Sharman%20Joshi%20%20Aishwarya%20Devan%20heart%20touching%20love%20story_480p.mp4"));
        homeCatListItem2.add(new Categoryitem(7,"Malik", "https://bit.ly/3jx1JeQ","https://storage.googleapis.com/amazonclone3/Malik%20-%20Official%20Trailer%20_%20Mahesh%20Narayanan%20_%20Fahadh%20Faasil%2C%20Nimisha%20Sajayan%20_%20Amazon%20Prime%20Video.mp4"));
        homeCatListItem2.add(new Categoryitem(8,"Teenage mutant ninja turtles", "https://bit.ly/2VyWlAe","https://storage.googleapis.com/amazonclone3/Teenage%20Mutant%20Ninja%20Turtles%20Official%20Teaser%20Trailer%20%231%20(2014)%20-%20Megan%20Fox%2C%20Will%20Arnett%20Movie%20HD.mp4"));
        homeCatListItem2.add(new Categoryitem(9,"Bumblebee", "https://bit.ly/3yz5O8P","https://storage.googleapis.com/amazonclone3/Bumblebee%20(2018)%20-%20New%20Official%20Trailer%20-%20Paramount%20Pictures.mp4"));
        homeCatListItem2.add(new Categoryitem(10,"Welcome", "https://bit.ly/3xuwN3T","https://storage.googleapis.com/amazonclone3/Welcome%20Back%20Official%20Trailer%20_%20Watch%20Full%20Movie%20On%20Eros%20Now.mp4"));
        homeCatListItem2.add(new Categoryitem(11,"Bhagam bhag", "https://bit.ly/2X6CKaJ","https://storage.googleapis.com/amazonclone3/y2mate.com%20-%20Bhagam%20Bhag%20Official%20Trailer%20%201080p%20HD_1080p.mp4"));
        homeCatListItem2.add(new Categoryitem(12,"Awara pagal diwana", "https://bit.ly/3s59uNh","https://storage.googleapis.com/amazonclone3/awara%20paagal%20dewana%20film%20trailer.mp4"));
        homeCatListItem2.add(new Categoryitem(13,"Ikkat", "https://bit.ly/2Ua2hio","https://storage.googleapis.com/amazonclone3/Ikkat%20-%20Official%20Trailer%20(Kannada)%20_%20Nagabhushana%2C%20Bhoomi%20Shetty%2C%20Sundar%20_%20Amazon%20Prime%20Video.mp4"));
        homeCatListItem2.add(new Categoryitem(14,"Golmaal fun unlimited", "https://bit.ly/3fNGmFg","https://storage.googleapis.com/amazonclone3/Golmaal_%20Fun%20Unlimited%20Official%20Trailer%202%20_%20Ajay%20D%20_%20Rimi%20S%20_%20Ashrad%20W%20_%20Sharman%20J%20_%20Tushar%20K.mp4"));
        homeCatListItem2.add(new Categoryitem(15,"Firaaq", "https://bit.ly/3fMQ2ja","https://storage.googleapis.com/amazonclone3/y2mate.com%20-%20Firaaq%20Trailer_480p.mp4"));

        List<Categoryitem> homeCatListItem3 = new ArrayList<>();
        homeCatListItem3.add(new Categoryitem(1,"Tom and jerry the movie", "https://bit.ly/3CD3n7M",""));
        homeCatListItem3.add(new Categoryitem(2,"Chhota bheem and the throne of bali", "https://bit.ly/3fOBAHC",""));
        homeCatListItem3.add(new Categoryitem(3,"Journey 2: the mysterious island", "https://bit.ly/37xa7Wk",""));
        homeCatListItem3.add(new Categoryitem(4,"Scoob!", "https://bit.ly/3fOIpZy",""));
        homeCatListItem3.add(new Categoryitem(5,"Beauty and the beast", "https://bit.ly/3fPvrea",""));
        homeCatListItem3.add(new Categoryitem(6,"Harry potter and the chamber of secrets", "https://bit.ly/37vLBot",""));
        homeCatListItem3.add(new Categoryitem(7,"Reign of the superman", "https://bit.ly/3izZDvv",""));
        homeCatListItem3.add(new Categoryitem(8,"How to train your dragon 2", "https://bit.ly/3yB1Nkn",""));
        homeCatListItem3.add(new Categoryitem(9,"Despicable Me 2", "https://bit.ly/2U8kdda",""));
        homeCatListItem3.add(new Categoryitem(10,"Alvin and the chipmunks: chipwrecked ", "https://bit.ly/3jHFral",""));
        homeCatListItem3.add(new Categoryitem(11,"The little vampire", "https://bit.ly/3fO3svA",""));
        homeCatListItem3.add(new Categoryitem(12,"The last airbender", "https://bit.ly/3yHzSzi",""));
        homeCatListItem3.add(new Categoryitem(13,"Mayanagari", "https://bit.ly/3CA2uwn",""));
        homeCatListItem3.add(new Categoryitem(14,"Shaun the sheep movie", "https://bit.ly/37zM5dj",""));
        homeCatListItem3.add(new Categoryitem(15,"Batman vs teenage mutant ninja turtles", "https://bit.ly/3ivIR0G",""));

        List<Categoryitem> homeCatListItem4 = new ArrayList<>();
        homeCatListItem4.add(new Categoryitem(1,"King kong", "https://bit.ly/3fODY12",""));
        homeCatListItem4.add(new Categoryitem(2,"Man of steel", "https://bit.ly/3fN3Bz8",""));
        homeCatListItem4.add(new Categoryitem(3,"Fantastic beasts and where to find them", "https://bit.ly/3AyhBVz",""));
        homeCatListItem4.add(new Categoryitem(4,"Inception", "https://bit.ly/3jIwr4S",""));
        homeCatListItem4.add(new Categoryitem(5,"300", "https://bit.ly/3iBQEu2",""));
        homeCatListItem4.add(new Categoryitem(6,"Van helsing", "https://bit.ly/3jIW3hV",""));
        homeCatListItem4.add(new Categoryitem(7,"The raid: redemption", "https://bit.ly/3CxTd8i",""));
        homeCatListItem4.add(new Categoryitem(8,"Bloodshot", "https://bit.ly/3lNsu1o",""));
        homeCatListItem4.add(new Categoryitem(9,"Annabelle creation", "https://bit.ly/37vN4et",""));
        homeCatListItem4.add(new Categoryitem(10,"The mummy", "https://bit.ly/2U3USkq",""));
        homeCatListItem4.add(new Categoryitem(11,"Host", "https://bit.ly/3jDVJkB",""));
        homeCatListItem4.add(new Categoryitem(12,"Dinoshark", "https://bit.ly/3yNqCcA",""));
        homeCatListItem4.add(new Categoryitem(13,"Texas chainsaw", "https://bit.ly/3fOSoON",""));
        homeCatListItem4.add(new Categoryitem(14,"Evil eye", "https://bit.ly/3lWAj4S",""));
        homeCatListItem4.add(new Categoryitem(15,"The taking of tiger mountain", "https://bit.ly/3xuEh7a",""));

        List<Categoryitem> homeCatListItem5 = new ArrayList<>();
        homeCatListItem5.add(new Categoryitem(1,"Friend request", "https://bit.ly/2VEdk3C",""));
        homeCatListItem5.add(new Categoryitem(2,"Kill the messenger", "https://bit.ly/3lQtIZU",""));
        homeCatListItem5.add(new Categoryitem(3,"Don2", "https://bit.ly/2VL285w",""));
        homeCatListItem5.add(new Categoryitem(4,"Mr.holmes", "https://bit.ly/3CBwZ5m",""));
        homeCatListItem5.add(new Categoryitem(5,"Before i wake", "https://bit.ly/3yAJoDX",""));
        homeCatListItem5.add(new Categoryitem(6,"Aankhen", "https://bit.ly/3xuJSdC",""));
        homeCatListItem5.add(new Categoryitem(7,"1920 London", "https://bit.ly/2U6pe61",""));
        homeCatListItem5.add(new Categoryitem(8,"211", "https://bit.ly/3iyVAQc",""));
        homeCatListItem5.add(new Categoryitem(9,"Ninja", "https://bit.ly/3izydGn",""));
        homeCatListItem5.add(new Categoryitem(10,"Zero dark thirty", "https://bit.ly/2VHj8t8",""));
        homeCatListItem5.add(new Categoryitem(11,"Captive state", "https://bit.ly/3CuY0rb",""));
        homeCatListItem5.add(new Categoryitem(12,"Jeepers creepers", "https://bit.ly/3iFJyop",""));
        homeCatListItem5.add(new Categoryitem(13,"The last exorcism", "https://bit.ly/2X5dJN8",""));
        homeCatListItem5.add(new Categoryitem(14,"I kill giants", "https://bit.ly/3CrLuIK",""));
        homeCatListItem5.add(new Categoryitem(15,"The hunter", "https://bit.ly/3fLTF9i",""));

        List<Categoryitem> homeCatListItem6 = new ArrayList<>();
        homeCatListItem6.add(new Categoryitem(1,"The last hour", "https://bit.ly/3iAqOq9",""));
        homeCatListItem6.add(new Categoryitem(2,"DOM", "https://bit.ly/3AqnkMY",""));
        homeCatListItem6.add(new Categoryitem(3,"Tandav", "https://bit.ly/3jGbS9d",""));
        homeCatListItem6.add(new Categoryitem(4,"Breathe into the shadows", "https://cutt.ly/kQIjS8F",""));
        homeCatListItem6.add(new Categoryitem(5,"Four more shots", "https://cutt.ly/8QIjJrO",""));
        homeCatListItem6.add(new Categoryitem(6,"The boys", "https://cutt.ly/EQIjBpk",""));
        homeCatListItem6.add(new Categoryitem(7,"Tom clancy's jack ryan", "https://cutt.ly/JQIjM6O",""));
        homeCatListItem6.add(new Categoryitem(8,"Inside edge", "https://cutt.ly/QQIj9uA",""));
        homeCatListItem6.add(new Categoryitem(9,"The expanse", "https://cutt.ly/aQIj4J3",""));
        homeCatListItem6.add(new Categoryitem(10,"The forgotten army", "https://cutt.ly/NQIkeYa",""));
        homeCatListItem6.add(new Categoryitem(11,"Comicstaan", "https://cutt.ly/TQIkuZn",""));
        homeCatListItem6.add(new Categoryitem(12,"Hanna", "https://cutt.ly/VQIkoBt",""));
        homeCatListItem6.add(new Categoryitem(13,"Good omens", "https://cutt.ly/NQIkfI1",""));
        homeCatListItem6.add(new Categoryitem(14,"You are wanted", "https://cutt.ly/XQIkkrF",""));
        homeCatListItem6.add(new Categoryitem(15,"Tokyo vampire hotel", "https://cutt.ly/uQIkxtV",""));

        List<Categoryitem> homeCatListItem7 = new ArrayList<>();
        homeCatListItem7.add(new Categoryitem(1,"Shershaah", "https://upload.wikimedia.org/wikipedia/en/9/91/Shershaah_film_poster.jpg",""));
        homeCatListItem7.add(new Categoryitem(2,"Modern love", "https://cutt.ly/VQIkUJa",""));
        homeCatListItem7.add(new Categoryitem(3,"Godzilla vs kong", "https://cutt.ly/QQIkGSG",""));
        homeCatListItem7.add(new Categoryitem(4,"Cinderella", "https://cutt.ly/OQIkKIj",""));


        List<Categoryitem> homeCatListItem8 = new ArrayList<>();
        homeCatListItem8.add(new Categoryitem(1,"Black box", "https://cutt.ly/JQIk1ri",""));
        homeCatListItem8.add(new Categoryitem(2,"6-5=2", "https://cutt.ly/SQIk2VK",""));
        homeCatListItem8.add(new Categoryitem(3,"The nun", "https://cutt.ly/zQIlwkE",""));
        homeCatListItem8.add(new Categoryitem(4,"The hole in the ground", "https://cutt.ly/UQIloyY",""));
        homeCatListItem8.add(new Categoryitem(5,"Don't be afraid of the dark", "https://cutt.ly/BQIlslB",""));
        homeCatListItem8.add(new Categoryitem(6,"At the devil's door", "https://cutt.ly/iQIllun",""));
        homeCatListItem8.add(new Categoryitem(7,"Don't look", "https://cutt.ly/DQIlxXj",""));
        homeCatListItem8.add(new Categoryitem(8,"The conjuring: 2", "https://cutt.ly/dQIlQQA",""));
        homeCatListItem8.add(new Categoryitem(9,"Boar", "https://cutt.ly/WQIlR1S",""));
        homeCatListItem8.add(new Categoryitem(10,"The grotto", "https://cutt.ly/pQIlIQS",""));


        List<Categoryitem> homeCatListItem9 = new ArrayList<>();
        homeCatListItem9.add(new Categoryitem(1,"ae dil hai mushkil", "",""));
        homeCatListItem9.add(new Categoryitem(2,"Padmaavat", "",""));
        homeCatListItem9.add(new Categoryitem(3,"sandeep aur pinky faraar", "",""));
        homeCatListItem9.add(new Categoryitem(4,"Kabhi khushi kabhi gham", "",""));
        homeCatListItem9.add(new Categoryitem(5,"Pati patni aur woh", "",""));
        homeCatListItem9.add(new Categoryitem(6,"Raabta", "",""));
        homeCatListItem9.add(new Categoryitem(7,"Student of the year", "",""));

        List<Categoryitem> homeCatListItem10 = new ArrayList<>();
        homeCatListItem10.add(new Categoryitem(1,"Panchayat", "",""));
        homeCatListItem10.add(new Categoryitem(2,"Sultan", "",""));
        homeCatListItem10.add(new Categoryitem(3,"Newton", "",""));
        homeCatListItem10.add(new Categoryitem(4,"Gangs of Wasseypur", "",""));
        homeCatListItem10.add(new Categoryitem(5,"Gulabo Sitabo", "",""));
        homeCatListItem10.add(new Categoryitem(6,"Sui Dhaaga", "",""));
        homeCatListItem10.add(new Categoryitem(7,"Shuddh Desi Romance", "",""));





        allCategoryList = new ArrayList<>();
        allCategoryList.add(new AllCategory(1,"Recommended Movies",homeCatListItem1));
        allCategoryList.add(new AllCategory(2,"Recently added movies",homeCatListItem2));
        allCategoryList.add(new AllCategory(3,"Kids and family movies",homeCatListItem3));
        allCategoryList.add(new AllCategory(4,"Movies dubbed in hindi",homeCatListItem4));
        allCategoryList.add(new AllCategory(5,"Thriller movies",homeCatListItem5));
        allCategoryList.add(new AllCategory(6,"Amazon original series",homeCatListItem6));
        allCategoryList.add(new AllCategory(7,"Coming soon",homeCatListItem7));
        allCategoryList.add(new AllCategory(8,"Horror movies",homeCatListItem8));
        allCategoryList.add(new AllCategory(9,"Romantic movies",homeCatListItem9));
        allCategoryList.add(new AllCategory(10,"Stories from the heartland",homeCatListItem10));


        setMainRecycler(allCategoryList);
    }






    class AutoSlider extends TimerTask{

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (bannerViewPage.getCurrentItem()< homeBannerList.size() -1 ){
                        bannerViewPage.setCurrentItem(bannerViewPage.getCurrentItem() +1);
                    }
                    else {
                        bannerViewPage.setCurrentItem(0);
                    }
                }
            });
        }

    }
    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }

    public void setMainRecycler(List<AllCategory> allCategoryList){
        mainRecycler = findViewById(R.id.main_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        mainRecycler.setLayoutManager(layoutManager);
        mainRecyclerAdapter = new MainRecyclerAdapter(this,allCategoryList);
        mainRecycler.setAdapter(mainRecyclerAdapter);
    }
   
    private void setScrollDefaultState(){
        nestedScrollView.fullScroll(View.FOCUS_UP);
        nestedScrollView.scrollTo(0,0);
        appBarLayout.setExpanded(true);
    }

    private void setBannerPagerAdapter(List<BannerMovies> bannerMoviesList){
        bannerViewPage = findViewById(R.id.banner_viewpage);
        bannerPagerAdapter = new BannerPagerAdapter(this,bannerMoviesList);
        bannerViewPage.setAdapter(bannerPagerAdapter);
        IndicatorTab.setupWithViewPager(bannerViewPage);

        Timer sliderTimer = new Timer();
        sliderTimer.scheduleAtFixedRate(new AutoSlider(),3000,6000);
        IndicatorTab.setupWithViewPager(bannerViewPage,true);
    }
}
