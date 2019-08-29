package com.ganargatul.favorite.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ganargatul.favorite.DetailActivity;
import com.ganargatul.favorite.R;
import com.ganargatul.favorite.model.MovieTvFavItems;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.ganargatul.favorite.DetailActivity.EXTRA_MOVIE;

public class MovieTvFavAdapter extends RecyclerView.Adapter<MovieTvFavAdapter.MovieTvFavAdapterViewHolder> {
    Context context;
    Cursor cursor;

    public MovieTvFavAdapter(Context context) {
        this.context = context;
    }
    public void setmMovieTvItems(Cursor movieTvFavItems) {

        this.cursor = movieTvFavItems;
    }
    @NonNull
    @Override
    public MovieTvFavAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.movietvfav_items,parent,false);

        return new MovieTvFavAdapterViewHolder(v);
    }
    private MovieTvFavItems getItems(int position){
        if (!cursor.moveToPosition(position)){
            throw new IllegalStateException("Invalid");
        }
        return new MovieTvFavItems(cursor);
    }
    @Override
    public void onBindViewHolder(@NonNull final MovieTvFavAdapterViewHolder holder, int position) {
        final MovieTvFavItems movieTvFavItems = getItems(position);
        Picasso.with(context).load("https://image.tmdb.org/t/p/w500"+movieTvFavItems.getPhoto()).into(holder.mCircleImageView);
        holder.mTitle.setText(movieTvFavItems.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(),DetailActivity.class);
                intent.putExtra(EXTRA_MOVIE,movieTvFavItems);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (cursor==null)return 0;
        return cursor.getCount();
    }

    public class MovieTvFavAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView mTitle;
        CircleImageView mCircleImageView;
        public MovieTvFavAdapterViewHolder(@NonNull final View itemView) {
            super(itemView);
            mCircleImageView = itemView.findViewById(R.id.image_items);
            mTitle = itemView.findViewById(R.id.title_items);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemView.getContext().startActivity(new Intent(itemView.getContext(), DetailActivity.class));
                }
            });
        }
    }
}
