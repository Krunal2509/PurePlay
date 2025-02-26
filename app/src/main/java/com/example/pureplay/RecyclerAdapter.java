package com.example.pureplay;

import static com.example.pureplay.GlobalMediaPlayer.btn_play;
import static com.example.pureplay.GlobalMediaPlayer.flag_PlayPause;
import static com.example.pureplay.GlobalMediaPlayer.mp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context context;
    private ArrayList<songArray> songList;
    private static RecyclerAdapter.ViewHolder holder;

    RecyclerAdapter(Context context, ArrayList<songArray> songList) {
        this.context = context;
        this.songList = songList;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.songs_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        songArray song = songList.get(position);
        holder.img_song.setImageResource(song.getsImg());
        holder.tv_songName.setText(song.getsName());

        this.holder=holder;

        holder.cardView.setOnClickListener(v -> {
            try {

                if (GlobalMediaPlayer.mp != null) {
                    flag_PlayPause=true;
                }

                if(flag_PlayPause) {
                    btn_play.setBackgroundResource(R.drawable.pause);
                    flag_PlayPause=false;
                }
                else{
                    btn_play.setBackgroundResource(R.drawable.play);
                    flag_PlayPause=true;
                }

                Toast.makeText(context, "Clicked : " + song.getsName(), Toast.LENGTH_LONG).show();
                GlobalMediaPlayer.playSong(context,position,songList,holder);

            } catch (Exception e) {
                Toast.makeText(context, "Error : "+e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public static void setSongTime(){
        int duration=GlobalMediaPlayer.mp.getDuration();
        duration/=1000;
        holder.tv_songTime.setText(String.format("%d:%02d",duration/60,duration%60));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_songName, tv_songTime;
        ImageView img_song;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_songName = itemView.findViewById(R.id.tv_songName);
            tv_songTime = itemView.findViewById(R.id.tv_songTime);
            img_song = itemView.findViewById(R.id.img_song);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
