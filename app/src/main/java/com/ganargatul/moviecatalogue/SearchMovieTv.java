package com.ganargatul.moviecatalogue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.ganargatul.moviecatalogue.adapter.MovieTvAdapter;
import com.ganargatul.moviecatalogue.model.MovieTvItems;
import com.ganargatul.moviecatalogue.viewmodel.MovieViewModel;
import com.ganargatul.moviecatalogue.viewmodel.TvViewModel;

import java.util.ArrayList;


public class SearchMovieTv extends AppCompatActivity implements MovieTvAdapter.OnItemClickListener {
    public static final String EXTRA_SEARCH="extra_detail";
    MovieTvAdapter mMovieTvAdapter;
    MovieViewModel mMovieViewModel;
    TvViewModel mTvViewModel;
    ProgressBar mProgressBar;
    RecyclerView mRecyclerView;
    MovieTvItems movieTvItems;
    String query,type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie_tv);
        settoolbar();
        setintentdetail();
        setsearchmovietv(query);
    }
    void settoolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_black_24dp);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    void setsearchmovietv(String title){
        mProgressBar = findViewById(R.id.progress_search);
        mProgressBar.setVisibility(View.VISIBLE);

        mMovieTvAdapter = new MovieTvAdapter(SearchMovieTv.this);
        mMovieTvAdapter.SetOnItemClickListener(SearchMovieTv.this);
        mMovieTvAdapter.notifyDataSetChanged();
        if (type.equals("MOVIE")){
            mMovieViewModel = ViewModelProviders.of(SearchMovieTv.this).get(MovieViewModel.class);
            mMovieViewModel.getmMovieTvItems().observe(SearchMovieTv.this,getmMovieTvItems);
            mMovieViewModel.searchMovie(title);
        }else if (type.equals("TV")){
            mTvViewModel = ViewModelProviders.of(SearchMovieTv.this).get(TvViewModel.class);
            mTvViewModel.getmTvItems().observe(SearchMovieTv.this,getmTvItems);
            mTvViewModel.searchtv(title);
        }


        mRecyclerView = findViewById(R.id.recycler_search);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(SearchMovieTv.this,RecyclerView.VERTICAL,false));
        mRecyclerView.setAdapter(mMovieTvAdapter);
    }
    void setintentdetail(){
        movieTvItems = new MovieTvItems();
        movieTvItems = getIntent().getParcelableExtra(EXTRA_SEARCH);
        query = movieTvItems.getTitle();
        type = movieTvItems.getType();
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

    private Observer<? super ArrayList<MovieTvItems>> getmTvItems = new Observer<ArrayList<MovieTvItems>>() {
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

    }
}
