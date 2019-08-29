package com.ganargatul.moviecatalogue.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Binder;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.ganargatul.moviecatalogue.R;
import com.ganargatul.moviecatalogue.model.MovieTvItems;
import com.ganargatul.moviecatalogue.model.MovieTvItemsWidget;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.concurrent.ExecutionException;

import static com.ganargatul.moviecatalogue.database.DatabaseContract.MovieColoumn.CONTENT_URI;

public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    Context context;
    Cursor cursor;
    int ID;

    public StackRemoteViewsFactory(Context context,Intent intent) {
        this.context = context;
        ID  =intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    @Override
    public void onCreate() {
        if (cursor!=null){
            cursor.close();
        }
        cursor = context.getContentResolver().query(CONTENT_URI,null,null,null,null);

    }

    @Override
    public void onDataSetChanged() {


    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }



    private MovieTvItemsWidget getItems(int position){
        if (!cursor.moveToPosition(position)){
            try {
                throw new IllegalAccessException("Error");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return new MovieTvItemsWidget(cursor);
    }
    @Override
    public RemoteViews getViewAt(int i) {
        final RemoteViews rv= new RemoteViews(context.getPackageName(), R.layout.widget_items);
        MovieTvItemsWidget movieTvItems = getItems(i);
        Bitmap bitmap = null;
        try {
            bitmap = Glide.with(context).asBitmap().load("https://image.tmdb.org/t/p/w500"+movieTvItems.getPhoto()).apply(new RequestOptions().fitCenter()).submit().get();
        }catch (InterruptedException|ExecutionException e  ){
            e.printStackTrace();
        }
        rv.setImageViewBitmap(R.id.image_widget,bitmap);

        Bundle extras = new Bundle();
        extras.putInt(FavoriteWidget.EXTRA_ITEM,i);
        Intent intent = new Intent();
        intent.putExtras(extras);
        rv.setOnClickFillInIntent(R.id.image_widget,intent);
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
