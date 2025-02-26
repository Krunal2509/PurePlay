package com.example.pureplay;

import static com.example.pureplay.RecyclerAdapter.setSongTime;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class GlobalMediaPlayer {
    public static MediaPlayer mp;
    public static boolean flag_PlayPause=true;
    public static ImageView btn_play;
    public static int song_Index;
    public static Context mp_context;
    public static ArrayList<songArray>songsData;
    public static RecyclerAdapter.ViewHolder recyclerHolder;
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

        songArray song=songList.get(postition);

        mp=MediaPlayer.create(context, Uri.parse(song.getsPath()));
        mp.start();
        setSongTime();
    }

    public static void playNext(){
        if(mp!=null) {
            mp.reset();
            mp.release();
            mp=null;
            mp = MediaPlayer.create(mp_context, Uri.parse((songsData.get(++song_Index)).getsPath()));
            mp.start();
            setSongTime();
        }
        else Toast.makeText(mp_context, "Select Song First", Toast.LENGTH_LONG).show();
    }

    public static void playPrev(){
        if(mp!=null){
            mp.reset();
            mp.release();
            mp=null;
            mp=MediaPlayer.create(mp_context,Uri.parse((songsData.get(--song_Index)).getsPath()));
            mp.start();
            setSongTime();
        }
        else Toast.makeText(mp_context, "Select Song First", Toast.LENGTH_LONG).show();
    }

}
