package com.ganargatul.favorite.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.ganargatul.favorite.db.DatabaseContract;

import static com.ganargatul.favorite.db.DatabaseContract.getColoumnInt;
import static com.ganargatul.favorite.db.DatabaseContract.getColoumnString;

public class MovieTvFavItems implements Parcelable {
    String type,title,photo,overview;
    int id;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        dest.writeInt(this.id);
    }

    public MovieTvFavItems(Cursor cursor) {
        this.id = getColoumnInt(cursor, DatabaseContract.MovieColoumn._ID);
        this.title = getColoumnString(cursor, DatabaseContract.MovieColoumn.TITLE);
        this.photo = getColoumnString(cursor, DatabaseContract.MovieColoumn.IMAGE);
        this.overview = getColoumnString(cursor, DatabaseContract.MovieColoumn.OVERVIEW);

    }

    protected MovieTvFavItems(Parcel in) {
        this.type = in.readString();
        this.title = in.readString();
        this.photo = in.readString();
        this.overview = in.readString();
        this.id = in.readInt();
    }

    public static final Parcelable.Creator<MovieTvFavItems> CREATOR = new Parcelable.Creator<MovieTvFavItems>() {
        @Override
        public MovieTvFavItems createFromParcel(Parcel source) {
            return new MovieTvFavItems(source);
        }

        @Override
        public MovieTvFavItems[] newArray(int size) {
            return new MovieTvFavItems[size];
        }
    };
}
