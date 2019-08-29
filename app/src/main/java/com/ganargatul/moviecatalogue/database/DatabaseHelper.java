package com.ganargatul.moviecatalogue.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.PublicKey;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    public static String DATABASE_NAME="moviecatalog";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }
    private static final String SQL_CREATE_TABLE_MOVIE = String.format("CREATE TABLE %s"
                    +"(%s INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "%s TEXT NOT NULL UNIQUE,"+
                    "%s TEXT NOT NULL UNIQUE,"+
                    "%s TEXT NOT NULL UNIQUE)",
            DatabaseContract.TABLE_MOVIE,
            DatabaseContract.MovieColoumn._ID,
            DatabaseContract.MovieColoumn.TITLE,
            DatabaseContract.MovieColoumn.OVERVIEW,
            DatabaseContract.MovieColoumn.IMAGE
    );
    private static final String SQL_CREATE_TABLE_TV= String.format("CREATE TABLE %s"
                    +"(%s INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "%s TEXT NOT NULL UNIQUE,"+
                    "%s TEXT NOT NULL UNIQUE,"+
                    "%s TEXT NOT NULL UNIQUE)",
            DatabaseContract.TABLE_TV,
            DatabaseContract.TvColoumn._ID,
            DatabaseContract.TvColoumn.TITLE,
            DatabaseContract.TvColoumn.OVERVIEW,
            DatabaseContract.TvColoumn.IMAGE
    );
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_MOVIE);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_TV);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DatabaseContract.TABLE_MOVIE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DatabaseContract.TABLE_TV);

        onCreate(sqLiteDatabase);
    }
}
