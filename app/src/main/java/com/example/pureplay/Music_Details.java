package com.example.pureplay;

import static com.example.pureplay.GlobalMediaPlayer.mp;
import static com.example.pureplay.GlobalMediaPlayer.seekBar_Global;
import static com.example.pureplay.GlobalMediaPlayer.startSeekbarThread;
import static com.example.pureplay.GlobalMediaPlayer.time;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class Music_Details extends AppCompatActivity {

    ImageView cardViewMusic,btn_play,btn_next,btn_prev;
    TextView tv_currentSongMusic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_music_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

         cardViewMusic=findViewById(R.id.cardViewMusic);

        Glide.with(this)
                .asGif()
                .load(R.raw.musicvideo)
                .into(cardViewMusic);


        GlobalMediaPlayer.imageViewFromMain(this);


        btn_play=findViewById(R.id.btn_play);
        btn_next=findViewById(R.id.btn_next);
        btn_prev=findViewById(R.id.btn_prev);
        tv_currentSongMusic=findViewById(R.id.tv_currentSongMusic);
        seekBar_Global=findViewById(R.id.seekbar);

        startSeekbarThread();

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp.isPlaying() ){btn_play.setBackgroundResource(R.drawable.play);mp.pause();}
                else {btn_play.setBackgroundResource(R.drawable.pause);mp.start();}
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

        seekBar_Global.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser && mp!=null)   mp.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }



    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "In RESUME", Toast.LENGTH_SHORT).show();
        new GlobalMediaPlayer(tv_currentSongMusic);
        if(mp!=null && mp.isPlaying()){btn_play.setBackgroundResource(R.drawable.pause);}
        else{ btn_play.setBackgroundResource(R.drawable.play);}
    }

}