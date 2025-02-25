package com.example.pureplay;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import static com.example.pureplay.GlobalMediaPlayer.*;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
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
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CardView cardView;
    ArrayList<songArray> song=new ArrayList<>();
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ImageView img_btn_drawer;

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

        recyclerView=findViewById(R.id.recyclerView);
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

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawer(navigationView);
                return true;
            }
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
        song.add(new songArray(R.drawable.musiclogo,"11 song","https://www.soundhelix.com/examples/mp3/SoundHelix-Song-8.mp"));

        RecyclerAdapter adapter=new RecyclerAdapter(this,song);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(navigationView))
            drawerLayout.closeDrawer(navigationView);
        else
        super.onBackPressed();
    }
}