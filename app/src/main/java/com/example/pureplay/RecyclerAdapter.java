package com.example.pureplay;

import static com.example.pureplay.GlobalMediaPlayer.btn_play;
import static com.example.pureplay.GlobalMediaPlayer.mp;
import static com.example.pureplay.GlobalMediaPlayer.tempFlag;
import static com.example.pureplay.GlobalMediaPlayer.tv_SongTimeGlobal;

import android.content.Context;
import android.content.Intent;
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


        holder.cardView.setOnClickListener(v -> {
            try {
                if(!holder.tv_SongTime.getText().equals("00:00/00:00")){

                }

                GlobalMediaPlayer.playSong(context,position,songList,holder);
                if(tempFlag){
                    Intent i=new Intent(context,Music_Details.class);
                    context.startActivity(i);
                    tempFlag=false;
                }


                if (mp!=null && mp.isPlaying()) btn_play.setBackgroundResource(R.drawable.pause);
                else btn_play.setBackgroundResource(R.drawable.play);

            } catch (Exception e) {
                Toast.makeText(context, "Error In Recycler Adapter: "+e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_songName,tv_SongTime;
        ImageView img_song;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_songName = itemView.findViewById(R.id.tv_songName);
            img_song = itemView.findViewById(R.id.img_song);
            cardView = itemView.findViewById(R.id.cardView);

            tv_SongTime = itemView.findViewById(R.id.tv_songTime);

        }
    }
}
