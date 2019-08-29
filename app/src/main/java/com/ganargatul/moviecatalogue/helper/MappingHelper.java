package com.ganargatul.moviecatalogue.helper;

import android.database.Cursor;

import com.ganargatul.moviecatalogue.model.MovieTvItems;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.ganargatul.moviecatalogue.database.DatabaseContract.MovieColoumn.IMAGE;
import static com.ganargatul.moviecatalogue.database.DatabaseContract.MovieColoumn.OVERVIEW;
import static com.ganargatul.moviecatalogue.database.DatabaseContract.MovieColoumn.TITLE;

public class MappingHelper {
    public static ArrayList<MovieTvItems> movieTvItemsArrayList(Cursor cursor){
        ArrayList<MovieTvItems> movieTvItems= new ArrayList<>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(_ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(TITLE));
            String overview = cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW));
            String image = cursor.getString(cursor.getColumnIndexOrThrow(IMAGE));
            MovieTvItems movieTvItems1 = new MovieTvItems();
            movieTvItems1.setTitle(title);
            movieTvItems1.setOverview(overview);
            movieTvItems1.setPhoto(image);
            movieTvItems1.setId(id);
            movieTvItems.add(movieTvItems1);
        }
        return movieTvItems;

    }
}
