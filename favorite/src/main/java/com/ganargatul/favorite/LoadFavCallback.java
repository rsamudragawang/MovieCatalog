package com.ganargatul.favorite;

import android.database.Cursor;


public interface LoadFavCallback {
    void postExecute(Cursor mMovieTvItems);
}
