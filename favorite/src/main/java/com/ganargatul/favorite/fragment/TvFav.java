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
import com.ganargatul.favorite.adapter.TVFavAdapter;

import java.util.Objects;

import static com.ganargatul.favorite.db.DatabaseContract.TvColoumn.CONTENT_URI_TV;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvFav extends Fragment {
    TVFavAdapter tvFavAdapter;
    Cursor mList;
    RecyclerView mRecyclerView;

    public TvFav() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tv_fav, container, false);
        mRecyclerView = v.findViewById(R.id.tv_fav_container);

        tvFavAdapter = new TVFavAdapter(getContext());
        Log.d("tvcreate","tvcreate");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
        mRecyclerView.setAdapter(tvFavAdapter);
        new LoadTvFav().execute();
        return v;
    }

    @SuppressLint("StaticFieldLeak")
    private class LoadTvFav extends AsyncTask<Void,Void,Cursor> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected Cursor doInBackground(Void... voids) {
            Log.d("background", String.valueOf(CONTENT_URI_TV));
            return Objects.requireNonNull(getContext()).getContentResolver().query(CONTENT_URI_TV,null,null,null,null);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            mList = cursor;
            tvFavAdapter.setmMovieTvItems(mList);
            tvFavAdapter.notifyDataSetChanged();
        }


    }
}
