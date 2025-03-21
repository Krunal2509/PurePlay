package com.example.pureplay;


import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GlobalMediaPlayer {
    public static MediaPlayer mp;
    public static ImageView btn_play;
    public static int song_Index;
    public static Context mp_context;
    public static ArrayList<songArray>songsData;
    public static RecyclerAdapter.ViewHolder recyclerHolder;
    public static TextView curr_Song,tv_SongTimeGlobal;
    public static songArray curr_Song_Global;
    public static SeekBar seekBar_Global;
    public static int time=0;
    public static Handler handler=new Handler();
    public static Runnable updateSeekbar;
    public static boolean tempFlag=true;



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
            recyclerHolder.tv_SongTime.setText(String.format("%02d:%02d/%02d:%02d",0,0,(mp.getDuration()/1000)/60,(mp.getDuration()/1000)%60));
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
        if(mp.isPlaying())btn_play.setBackgroundResource(R.drawable.pause);
        else btn_play.setBackgroundResource(R.drawable.play);

        curr_Song.setText(curr_Song_Global.getsName());
    }

    public static void playNext(){
        if(mp!=null) {
            mp.reset();
            mp.release();
            mp=null;
            curr_Song_Global=songsData.get(++song_Index);
            mp = MediaPlayer.create(mp_context, Uri.parse(curr_Song_Global.getsPath()));
            mp.start();
            if(mp.isPlaying())btn_play.setBackgroundResource(R.drawable.pause);
            else btn_play.setBackgroundResource(R.drawable.play);
            curr_Song.setText(curr_Song_Global.getsName());
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
            if(mp.isPlaying())btn_play.setBackgroundResource(R.drawable.pause);
            else btn_play.setBackgroundResource(R.drawable.play);
            curr_Song.setText(curr_Song_Global.getsName());
        }
        else Toast.makeText(mp_context, "Select Song First", Toast.LENGTH_LONG).show();
    }

    public static void startSeekbarThread(){
        seekBar_Global.setMax(mp.getDuration());
        updateSeekbar=new Runnable() {
            @Override
            public void run() {
                seekBar_Global.setProgress(mp.getCurrentPosition());
                tv_SongTimeGlobal.setText(String.format("%02d:%02d/%02d:%02d",(mp.getCurrentPosition()/1000)/60,(mp.getCurrentPosition()/1000)%60,(mp.getDuration()/1000)/60,(mp.getDuration()/1000)%60));
               if(recyclerHolder!=null){ //This is necessary for set song Time in main activity
                   recyclerHolder.tv_SongTime.setText(tv_SongTimeGlobal.getText());
               }
                handler.postDelayed(updateSeekbar,1000);//Update in every second
            }
        };
        handler.post(updateSeekbar);
    }


}
