package com.example.pureplay;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    Context context;
    ArrayList<songArray>song=new ArrayList<>();
    RecyclerAdapter(Context context, ArrayList<songArray> songName){
        this.context=context;
        this.song=songName;
    }
    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.songs_row,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        holder.img_song.setImageResource(song.get(position).img);
    }

    @Override
    public int getItemCount() {
        return song.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_songName,tv_songTime;
        ImageView img_song;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_songName=itemView.findViewById(R.id.tv_songName );
            tv_songTime=itemView.findViewById(R.id.tv_songTime);
            img_song=itemView.findViewById(R.id.img_song);

            cardView =itemView.findViewById(R.id.cardView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(context,OpenFileManager.class);
                    context.startActivity(i);
                }
            });
        }
    }
}
