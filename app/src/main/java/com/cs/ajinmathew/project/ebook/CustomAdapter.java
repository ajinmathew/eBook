package com.cs.ajinmathew.project.ebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    List<Book> book_list;
    Context context;
    Picasso picasso;

    public CustomAdapter(List<Book> movie_list, Context context) {
        this.book_list = movie_list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from( parent.getContext() ).inflate( R.layout.cardallview,parent,false );
        return new MyViewHolder( view );
    }
    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        Book book=book_list.get( position );
        holder.tv1.setText( book.title );
        holder.tv2.setText( book.code );
        holder.tv3.setText( book.author );
        holder.tv4.setText( book.description );
        holder.tv5.setText( book.publisher );
        holder.tv6.setText( book.type );
        holder.tv7.setText( book.prize );
        picasso.with( this.context ).load( book.imagepath ).into( holder.imageView );
    }

    @Override
    public int getItemCount() {
        return book_list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super( itemView );
            tv1=itemView.findViewById( R.id.booknameview );
            tv2=itemView.findViewById( R.id.bookcodeview );
            tv3=itemView.findViewById( R.id.authorview );
            tv4=itemView.findViewById( R.id.descriptionview );
            tv5=itemView.findViewById( R.id.publisherview );
            tv6=itemView.findViewById( R.id.booktypeview );
            tv7=itemView.findViewById( R.id.prizeview );
            imageView=itemView.findViewById( R.id.bookpicview );
        }
    }
}
