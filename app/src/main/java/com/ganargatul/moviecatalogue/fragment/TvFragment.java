package com.ganargatul.moviecatalogue.fragment;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.ganargatul.moviecatalogue.DetailActivity;
import com.ganargatul.moviecatalogue.R;
import com.ganargatul.moviecatalogue.SearchMovieTv;
import com.ganargatul.moviecatalogue.adapter.MovieTvAdapter;
import com.ganargatul.moviecatalogue.model.MovieTvItems;
import com.ganargatul.moviecatalogue.viewmodel.MovieViewModel;
import com.ganargatul.moviecatalogue.viewmodel.TvViewModel;

import java.util.ArrayList;


import static com.ganargatul.moviecatalogue.DetailActivity.EXTRA_DETAIL;
import static com.ganargatul.moviecatalogue.SearchMovieTv.EXTRA_SEARCH;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvFragment extends Fragment implements MovieTvAdapter.OnItemClickListener {

    ProgressBar mProgressBar;
    RecyclerView mRecyclerView;
    MovieTvAdapter mMovieTvAdapter;
    TvViewModel mTvViewModel;

    public TvFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tv, container, false);
        setHasOptionsMenu(true);
        mProgressBar = v.findViewById(R.id.progress_tv);
        mProgressBar.setVisibility(View.VISIBLE);

        mMovieTvAdapter = new MovieTvAdapter(getContext());
        mMovieTvAdapter.SetOnItemClickListener(TvFragment.this);
        mMovieTvAdapter.notifyDataSetChanged();

        mTvViewModel = ViewModelProviders.of(TvFragment.this).get(TvViewModel.class);
        mTvViewModel.getmTvItems().observe(TvFragment.this,getmMovieTvItems);
        mTvViewModel.getTV();

        mRecyclerView = v.findViewById(R.id.recycler_tv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        mRecyclerView.setAdapter(mMovieTvAdapter);
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.main,menu);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        if(searchManager != null){
            SearchView searchView = (SearchView) (menu.findItem(R.id.action_search)).getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
            searchView.setQueryHint(getResources().getString(R.string.search_movie));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Toast.makeText(getContext(), query, Toast.LENGTH_SHORT).show();
                    Log.d("tesstt",query);
                    //         mMovieViewModel.getmMovieTvItems().observe(MovieFragment.this,getseachMovieItems);
                    //       mMovieViewModel.searchMovie(query);
                    MovieTvItems movieTvItems = new MovieTvItems();
                    movieTvItems.setTitle(query);
                    movieTvItems.setType("TV");
                    Intent search = new Intent(getContext(), SearchMovieTv.class);
                    search.putExtra(EXTRA_SEARCH,movieTvItems);
                    startActivity(search);

                    return true;
                }
                @Override
                public boolean onQueryTextChange(String newText) {
                    //         Toast.makeText(getContext(), newText, Toast.LENGTH_SHORT).show();

                    return false;
                }


            });

        }
    }

    private Observer<? super ArrayList<MovieTvItems>> getmMovieTvItems = new Observer<ArrayList<MovieTvItems>>() {
        @Override
        public void onChanged(ArrayList<MovieTvItems> movieTvItems) {
            if (movieTvItems!=null){
                mMovieTvAdapter.setmMovieTvItems(movieTvItems);
                mProgressBar.setVisibility(View.GONE);
            }
        }
    };

    @Override
    public void onItemClick(int position) {
        String type = "TV";
        MovieTvItems movieTv_items = new MovieTvItems();
        movieTv_items.setPhoto(mTvViewModel.mListMovieItems.get(position).getPhoto());
        movieTv_items.setTitle(mTvViewModel.mListMovieItems.get(position).getTitle());
        movieTv_items.setOverview(mTvViewModel.mListMovieItems.get(position).getOverview());
        movieTv_items.setType(type);
        Intent detail = new Intent(getContext(), DetailActivity.class);
        detail.putExtra(EXTRA_DETAIL,movieTv_items);
        startActivity(detail);

    }
}
