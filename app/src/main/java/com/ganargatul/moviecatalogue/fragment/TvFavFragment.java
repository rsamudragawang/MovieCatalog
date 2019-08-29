package com.ganargatul.moviecatalogue.fragment;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.ganargatul.moviecatalogue.DetailActivity;
import com.ganargatul.moviecatalogue.LoadFavCallback;
import com.ganargatul.moviecatalogue.R;
import com.ganargatul.moviecatalogue.adapter.MovieTvAdapter;
import com.ganargatul.moviecatalogue.database.MovieHelper;
import com.ganargatul.moviecatalogue.database.TvHelper;
import com.ganargatul.moviecatalogue.model.MovieTvItems;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import static com.ganargatul.moviecatalogue.DetailActivity.EXTRA_DETAIL;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvFavFragment extends Fragment implements MovieTvAdapter.OnItemClickListener, LoadFavCallback {
    MovieTvAdapter mMovieTvAdapter;
    ProgressBar mProgressBar;
    TvHelper mTvHelper;
    ArrayList<MovieTvItems>mListFav = new ArrayList<>();

    public TvFavFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_tv_fav, container, false);
        mProgressBar = v.findViewById(R.id.progress_tv_fav);
        RecyclerView mRecyclerView = v.findViewById(R.id.tv_fav_container);

        mTvHelper = TvHelper.getINSTANCE(getContext());

        mMovieTvAdapter = new MovieTvAdapter(getContext());
        mMovieTvAdapter.SetOnItemClickListener(TvFavFragment.this);


        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        mRecyclerView.setAdapter(mMovieTvAdapter);

        new LoadAsyncTask(mTvHelper,this).execute();
        return v;
    }

    @Override
    public void onItemClick(int position) {
        String type = "TV";
        MovieTvItems movieTv_items = new MovieTvItems();
        movieTv_items.setId(mListFav.get(position).getId());
        Log.d("id", String.valueOf(mListFav.get(position).getId()));
        movieTv_items.setPhoto(mListFav.get(position).getPhoto());
        movieTv_items.setTitle(mListFav.get(position).getTitle());
        movieTv_items.setOverview(mListFav.get(position).getOverview());
        movieTv_items.setType(type);
        Intent detail = new Intent(getContext(), DetailActivity.class);
        detail.putExtra(EXTRA_DETAIL,movieTv_items);
        startActivity(detail);
    }

    @Override
    public void preExecute() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void postExecute(ArrayList<MovieTvItems> mMovieTvItems) {
        mProgressBar.setVisibility(View.GONE);
        mMovieTvAdapter.setmMovieTvItems(mMovieTvItems);
        mListFav.addAll(mMovieTvItems);
    }

    private class LoadAsyncTask extends AsyncTask<Void, Void, ArrayList<MovieTvItems>> {
        WeakReference<TvHelper> movieTvItemsWeakReference;
        WeakReference<LoadFavCallback> loadFavCallbackWeakReference;
        public LoadAsyncTask(TvHelper mTvHelper, LoadFavCallback context) {
            movieTvItemsWeakReference = new WeakReference<>(mTvHelper);
            loadFavCallbackWeakReference = new WeakReference<>(context);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loadFavCallbackWeakReference.get().preExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<MovieTvItems> movieTvItems) {
            super.onPostExecute(movieTvItems);
            loadFavCallbackWeakReference.get().postExecute(movieTvItems);
        }

        @Override
        protected ArrayList<MovieTvItems> doInBackground(Void... voids) {
            return movieTvItemsWeakReference.get().getTvItems();
        }
    }
}
