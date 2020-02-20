package com.cs.ajinmathew.project.movieapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    List<Movie> movie_list;
    Context context;

    public CustomAdapter(List<Movie> movie_list, Context context) {
        this.movie_list = movie_list;
        this.context = context;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from( parent.getContext() ).inflate( R.layout.cardall,parent,false );

        return new MyViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {


        Movie movie=movie_list.get( position );

        holder.tv1.setText( movie.movie_name );
        holder.tv2.setText( movie.year );
        holder.tv3.setText( movie.actor_name );


    }

    @Override
    public int getItemCount() {
        return movie_list.size();
    }




    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv1,tv2,tv3;
        public MyViewHolder(@NonNull View itemView) {

            super( itemView );

            tv1=itemView.findViewById( R.id.name );
            tv2=itemView.findViewById( R.id.year );
            tv3=itemView.findViewById( R.id.actor );

        }
    }
}
