package com.example.pureplay;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.pureplay.GlobalMediaPlayer.*;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CardView cardView;
    ArrayList<songArray> song=new ArrayList<>();
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ImageView img_btn_drawer,btn_play,btn_next,btn_prev;
    TextView tv_currentSong;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        //////////////////////////////////  NAVIGATION DRAWER ////////////////////////////////////

        navigationView=findViewById(R.id.navigationView);
        drawerLayout=findViewById(R.id.drawerLayout);
        img_btn_drawer=findViewById(R.id.img_btn_drawer);

        img_btn_drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!drawerLayout.isDrawerOpen(navigationView)){
                    drawerLayout.openDrawer(navigationView);
                }
            }
        });
        Intent i=new Intent(getApplicationContext(),RegisterActivity.class);
        startActivity(i);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getTitle().equals("Register")) {
//                    Intent i=new Intent(getApplicationContext(),RegisterActivity.class);
//                    startActivity(i);
                }
                drawerLayout.closeDrawer(navigationView);
                return true;
            }
        });


        /////////////////////////////////////// MUSIC LIST ////////////////////////////////////

        recyclerView=findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        song.add(new songArray(R.drawable.musiclogo,"Dhun Laagi","android.resource://"+getPackageName()+"/"+R.raw.dhunlaagi));
        song.add(new songArray(R.drawable.musiclogo,"Dil Me Chhupaloonga","android.resource://"+getPackageName()+"/"+R.raw.dilmeinchhupaloonga));
        song.add(new songArray(R.drawable.musiclogo,"Enna Sona","android.resource://"+getPackageName()+"/"+R.raw.ennasona));
        song.add(new songArray(R.drawable.musiclogo,"Hathna Chhute Ranjha Ve","android.resource://"+getPackageName()+"/"+R.raw.hathnachuteranjhave));
        song.add(new songArray(R.drawable.musiclogo,"Nayan","android.resource://"+getPackageName()+"/"+R.raw.nayan));
        song.add(new songArray(R.drawable.musiclogo,"Maan Meri Jaan","android.resource://"+getPackageName()+"/"+R.raw.mannmerijaan));
        song.add(new songArray(R.drawable.musiclogo,"Apna Banale","android.resource://"+getPackageName()+"/"+R.raw.apnabanale));
        song.add(new songArray(R.drawable.musiclogo,"1 song","https://filesamples.com/samples/audio/mp3/sample3.mp3"));
        song.add(new songArray(R.drawable.musiclogo,"2 song","https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"));
        song.add(new songArray(R.drawable.musiclogo,"3 song","https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"));
        song.add(new songArray(R.drawable.musiclogo,"4 song","https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"));
        song.add(new songArray(R.drawable.musiclogo,"5 song","https://www.soundhelix.com/examples/mp3/SoundHelix-Song-2.mp3"));
        song.add(new songArray(R.drawable.musiclogo,"6 song","https://www.soundhelix.com/examples/mp3/SoundHelix-Song-3.mp3"));
        song.add(new songArray(R.drawable.musiclogo,"7 song","https://www.soundhelix.com/examples/mp3/SoundHelix-Song-4.mp3"));
        song.add(new songArray(R.drawable.musiclogo,"8 song","https://www.soundhelix.com/examples/mp3/SoundHelix-Song-5.mp3"));
        song.add(new songArray(R.drawable.musiclogo,"9 song","https://www.soundhelix.com/examples/mp3/SoundHelix-Song-6.mp3"));
        song.add(new songArray(R.drawable.musiclogo,"10 song","https://www.soundhelix.com/examples/mp3/SoundHelix-Song-7.mp3"));

        RecyclerAdapter adapter=new RecyclerAdapter(this,song);
        recyclerView.setAdapter(adapter);





        ////////////////////////////////////   MUSIC CONTROL //////////////////////////////////

        GlobalMediaPlayer.imageViewFromMain(this);

        btn_play=findViewById(R.id.btn_play);
        btn_next=findViewById(R.id.btn_next);
        btn_prev=findViewById(R.id.btn_prev);
        tv_currentSong=findViewById(R.id.tv_currentSong);


        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp.isPlaying()){
                    Toast.makeText(MainActivity.this, "Paused", Toast.LENGTH_SHORT).show();  btn_play.setBackgroundResource(R.drawable.play);mp.pause();}
                else {
                    Toast.makeText(MainActivity.this, "Played", Toast.LENGTH_SHORT).show();  btn_play.setBackgroundResource(R.drawable.pause);mp.start();}
            }
        });


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalMediaPlayer.playNext();
            }
        });


        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalMediaPlayer.playPrev();
            }
        });


        tv_currentSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Music_Details.class);
                startActivity(i);
            }
        });




    }

    @Override
    protected void onResume() {   //This method is called when back to activity
        super.onResume();
        Toast.makeText(this, "RESUME METHOD", Toast.LENGTH_SHORT).show();
        new GlobalMediaPlayer(tv_currentSong);
        if(mp!=null && mp.isPlaying() ){btn_play.setBackgroundResource(R.drawable.pause);}
        else {btn_play.setBackgroundResource(R.drawable.play);}
    }

}