package com.ganargatul.moviecatalogue.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.ganargatul.moviecatalogue.database.DatabaseContract;

import static com.ganargatul.moviecatalogue.database.DatabaseContract.getColoumnString;

public class MovieTvItemsWidget implements Parcelable {
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

    public MovieTvItemsWidget(Cursor cursor) {
        this.title = getColoumnString(cursor, DatabaseContract.MovieColoumn.TITLE);
        this.photo = getColoumnString(cursor, DatabaseContract.MovieColoumn.IMAGE);
        this.overview = getColoumnString(cursor, DatabaseContract.MovieColoumn.OVERVIEW);
    }

    protected MovieTvItemsWidget(Parcel in) {
        this.type = in.readString();
        this.title = in.readString();
        this.photo = in.readString();
        this.overview = in.readString();
        this.id = in.readInt();
    }

    public static final Parcelable.Creator<MovieTvItemsWidget> CREATOR = new Parcelable.Creator<MovieTvItemsWidget>() {
        @Override
        public MovieTvItemsWidget createFromParcel(Parcel source) {
            return new MovieTvItemsWidget(source);
        }

        @Override
        public MovieTvItemsWidget[] newArray(int size) {
            return new MovieTvItemsWidget[size];
        }
    };
}
