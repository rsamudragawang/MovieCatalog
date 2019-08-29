package com.ganargatul.moviecatalogue;

import com.ganargatul.moviecatalogue.model.MovieTvItems;

import java.util.ArrayList;

public interface LoadFavCallback {
    void preExecute();
    void postExecute(ArrayList<MovieTvItems> mMovieTvItems);
}
