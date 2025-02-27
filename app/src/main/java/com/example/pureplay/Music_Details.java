package com.example.pureplay;

import static com.example.pureplay.GlobalMediaPlayer.duration;
import static com.example.pureplay.GlobalMediaPlayer.flag_PlayPause;
import static com.example.pureplay.GlobalMediaPlayer.mp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class Music_Details extends AppCompatActivity {
    public static SeekBar seekBar;
    public static int songCurrTime=0;
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

        seekBar=findViewById(R.id.seekbar);

        GlobalMediaPlayer.imageViewFromMain(this);

        btn_play=findViewById(R.id.btn_play);
        btn_next=findViewById(R.id.btn_next);
        btn_prev=findViewById(R.id.btn_prev);
        tv_currentSongMusic=findViewById(R.id.tv_currentSongMusic);


        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp!=null && flag_PlayPause){btn_play.setBackgroundResource(R.drawable.pause);flag_PlayPause=false;mp.pause();}
                else {btn_play.setBackgroundResource(R.drawable.play);flag_PlayPause=true;mp.start();}
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


    }

    public static void seekBar(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                    while(songCurrTime<=duration) {
                        songCurrTime=mp.getDuration()/1000;
                        seekBar.setProgress(songCurrTime);
//                        tv_curr_Song_Global.setText(String.format("%02d : %02d / %02d : %02d",(songCurrTime/60),(songCurrTime%60),(duration/60),(duration%60)));
                    }
                    songCurrTime=0;
                    mp=null;
//                    tv_curr_Song_Global.setText("00:00/00:00");
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int value=seekBar.getProgress();
                value*=1000;
                mp.seekTo(value);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        new GlobalMediaPlayer(tv_currentSongMusic);
        if(mp!=null && flag_PlayPause){btn_play.setBackgroundResource(R.drawable.pause);flag_PlayPause=false;}
        else{ btn_play.setBackgroundResource(R.drawable.play);flag_PlayPause=true;}
    }
}