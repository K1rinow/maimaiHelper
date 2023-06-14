package com.example.maihelper.adaoter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maihelper.R;
import com.example.maihelper.bean.Song;

import java.net.PortUnreachableException;
import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {

    Context context;
    ArrayList<Song> songlist;
    public void setSonglist(ArrayList<Song> songlist){
        this.songlist=songlist;
        notifyDataSetChanged();
    }
//    public SongAdapter(){}
    public SongAdapter(Context context, ArrayList<Song> songlist){
        this.context=context;
        this.songlist=songlist;
    }
    @NonNull
    @Override
    public SongAdapter.SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.recyclerview_item,parent,false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongAdapter.SongViewHolder holder, int position) {
        holder.textView.setText(songlist.get(position).getTitle());
        holder.imageView.setImageResource(songlist.get(position).getRankImage(songlist.get(position).getRate()));
    }

    @Override
    public int getItemCount() {
        return songlist != null ? songlist.size() : 0;
//        return this.songlist.size();
    }
    public static class SongViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        public SongViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.rankImage);
            textView=itemView.findViewById(R.id.songTitle);
        }
    }
}
