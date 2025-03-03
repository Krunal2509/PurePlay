package com.example.pureplay;

import static com.example.pureplay.GlobalMediaPlayer.btn_play;
import static com.example.pureplay.GlobalMediaPlayer.mp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        songArray song = songList.get(position);
        holder.img_song.setImageResource(song.getsImg());
        holder.tv_songName.setText(song.getsName());

        this.holder=holder;

        holder.cardView.setOnClickListener(v -> {
            try {
                Toast.makeText(context, "Clicked : " + song.getsName(), Toast.LENGTH_LONG).show();
                GlobalMediaPlayer.playSong(context,position,songList,holder);

                if (mp!=null && mp.isPlaying()) btn_play.setBackgroundResource(R.drawable.pause);
                else btn_play.setBackgroundResource(R.drawable.play);

            Toast.makeText(context, ""+btn_play, Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(context, "Error : "+e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public static int setSongTime(){
        int duration=GlobalMediaPlayer.mp.getDuration();
        duration/=1000;
        holder.tv_songTime.setText(String.format("%02d:%02d/%d:%02d",0,0,duration/60,duration%60));
        return duration;
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
