package com.ganargatul.moviecatalogue.database;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;

public class DatabaseContract {
    public static String TABLE_MOVIE = "MOVIE";
    public static final String AUTHORITY="com.ganargatul.moviecatalogue";
    static final String SCHEME = "content";
    public static final class MovieColoumn implements BaseColumns {
        public static String TITLE = "title";
        public static String OVERVIEW = "overview";
        public static String IMAGE = "IMAGE";
        public static final Uri CONTENT_URI = new Uri.Builder()
                .scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_MOVIE)
                .build();
    }

    public static String TABLE_TV="TV";

    public static final class TvColoumn implements BaseColumns{
        static String TITLE = "title";
        static String OVERVIEW = "overview";
        static String IMAGE = "IMAGE";
        public static final Uri CONTENT_URI_TV = new Uri.Builder()
                .scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_TV)
                .build();
        public static final String TV_TYPE="vnd.android.cursor.dir/" + CONTENT_URI_TV+"/"+TABLE_TV;
        public static final String TV_TYPE_ITEM="vnd.android.cursor.item/" + CONTENT_URI_TV+"/"+TABLE_TV;

    }

    public static String getColoumnString(Cursor cursor,String coloumn_name){
        return cursor.getString(cursor.getColumnIndex(coloumn_name));
    }
    public static int getColoumnInt(Cursor cursor,String coloumn_name){
        return cursor.getInt(cursor.getColumnIndex(coloumn_name));
    }
    public static Long getColoumnLOng(Cursor cursor,String coloumn_name){
        return cursor.getLong(cursor.getColumnIndex(coloumn_name));
    }
}
