package com.ganargatul.moviecatalogue.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.ganargatul.moviecatalogue.database.DatabaseContract;

import static com.ganargatul.moviecatalogue.database.DatabaseContract.getColoumnString;

public class MovieTvItems implements Parcelable {
    String type,title,photo,overview;
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeString(this.title);
        dest.writeString(this.photo);
        dest.writeString(this.overview);
    }

    public MovieTvItems() {
    }

    public  final void setCursorMovie(Cursor cursor){
        this.title = getColoumnString(cursor, DatabaseContract.MovieColoumn.TITLE);
        this.photo = getColoumnString(cursor, DatabaseContract.MovieColoumn.IMAGE);
        this.overview = getColoumnString(cursor, DatabaseContract.MovieColoumn.OVERVIEW);
    }
    protected MovieTvItems(Parcel in) {
        this.type = in.readString();
        this.title = in.readString();
        this.photo = in.readString();
        this.overview = in.readString();
    }

    public static final Parcelable.Creator<MovieTvItems> CREATOR = new Parcelable.Creator<MovieTvItems>() {
        @Override
        public MovieTvItems createFromParcel(Parcel source) {
            return new MovieTvItems(source);
        }

        @Override
        public MovieTvItems[] newArray(int size) {
            return new MovieTvItems[size];
        }
    };
}
