package com.example.pureplay;

import static com.example.pureplay.Music_Details.seekBar;
import static com.example.pureplay.RecyclerAdapter.setSongTime;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GlobalMediaPlayer {
    public static MediaPlayer mp;
    public static boolean flag_PlayPause=false; // it indicates that if mp is not null then true else false and also consider play img
    public static ImageView btn_play;
    public static int song_Index;
    public static Context mp_context;
    public static ArrayList<songArray>songsData;
    public static RecyclerAdapter.ViewHolder recyclerHolder;
    public static TextView curr_Song;
    public static songArray curr_Song_Global;
    public static int duration;


    GlobalMediaPlayer(TextView currentSong){
        curr_Song=currentSong;
        if(curr_Song_Global!=null)
          currentSong.setText(curr_Song_Global.getsName());
    }

    public static void imageViewFromMain(Activity activity){
        btn_play=activity.findViewById(R.id.btn_play);
    }

    public static void playSong(Context context, int postition, ArrayList<songArray> songList,RecyclerAdapter.ViewHolder holder){
        if(mp!=null){
            mp.reset();
            mp.release();
            mp=null;
        }

        song_Index=postition;
        mp_context=context;
        songsData=songList;
        recyclerHolder=holder;

        curr_Song_Global=songList.get(postition);

        mp=MediaPlayer.create(context, Uri.parse(curr_Song_Global.getsPath()));
        mp.start();
        curr_Song.setText(curr_Song_Global.getsName());
        duration=setSongTime();
    }

    public static void playNext(){
        if(mp!=null) {
            mp.reset();
            mp.release();
            mp=null;
            curr_Song_Global=songsData.get(++song_Index);
            mp = MediaPlayer.create(mp_context, Uri.parse(curr_Song_Global.getsPath()));
            mp.start();
            curr_Song.setText(curr_Song_Global.getsName());
            duration=setSongTime();
        }
        else Toast.makeText(mp_context, "Select Song First", Toast.LENGTH_LONG).show();
    }

    public static void playPrev(){
        if(mp!=null){
            mp.reset();
            mp.release();
            mp=null;
            curr_Song_Global=songsData.get(--song_Index);
            mp=MediaPlayer.create(mp_context,Uri.parse(curr_Song_Global.getsPath()));
            mp.start();
            curr_Song.setText(curr_Song_Global.getsName());
            duration=setSongTime();
        }
        else Toast.makeText(mp_context, "Select Song First", Toast.LENGTH_LONG).show();
    }


}
