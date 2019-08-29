package com.ganargatul.favorite.fragment;


import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ganargatul.favorite.R;
import com.ganargatul.favorite.adapter.MovieTvFavAdapter;

import java.util.Objects;

import static com.ganargatul.favorite.db.DatabaseContract.MovieColoumn.CONTENT_URI;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFav extends Fragment {

    MovieTvFavAdapter mMovieTvFavAdapter;
    Cursor mList;
    RecyclerView mRecyclerView;
    public MovieFav() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_movie_fav, container, false);
        mRecyclerView = v.findViewById(R.id.movie_fav_container);

        mMovieTvFavAdapter = new MovieTvFavAdapter(getContext());
        Log.d("creattemovie","oncreate");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
        mRecyclerView.setAdapter(mMovieTvFavAdapter);
        new LoadMovieFav().execute();
        return v;
    }

    @SuppressLint("StaticFieldLeak")
    public class LoadMovieFav extends AsyncTask<Void,Void,Cursor>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected Cursor doInBackground(Void... voids) {
            Log.d("loadmovie", String.valueOf(CONTENT_URI));
            return Objects.requireNonNull(getContext()).getContentResolver().query(CONTENT_URI,null,null,null,null);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            mList = cursor;
            mMovieTvFavAdapter.setmMovieTvItems(mList);
            mMovieTvFavAdapter.notifyDataSetChanged();

        }
    }

}
