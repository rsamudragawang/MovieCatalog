package com.ganargatul.favorite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ganargatul.favorite.model.MovieTvFavItems;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import static com.ganargatul.favorite.db.DatabaseContract.MovieColoumn.CONTENT_URI;
import static com.ganargatul.favorite.db.DatabaseContract.TvColoumn.CONTENT_URI_TV;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_TV="extra_tv";
    public static final String EXTRA_MOVIE="extra_movie";
    MovieTvFavItems resultmovie,resulttv;
    TextView title,overview;
    ImageView poster;
    ProgressBar progressBar;
    FloatingActionButton fab;
    Boolean is_movie=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        fab = findViewById(R.id.floatingActionButton);
        progressBar = findViewById(R.id.progress_detail);
        title = findViewById(R.id.title_detail);
        overview = findViewById(R.id.desc_detail);
        poster = findViewById(R.id.image_detail);
        progressBar.setVisibility(View.VISIBLE);

        loadIntent();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (is_movie){
                    removefavmovie();
                }
                else {
                    removefavtv();
                }
            }
        });
    }

    private void removefavtv() {
        getContentResolver().delete(Uri.parse(CONTENT_URI_TV+"/"+resulttv.getId()),null,null);
        Toast.makeText(DetailActivity.this, R.string.add, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }

    private void removefavmovie() {
        getContentResolver().delete(Uri.parse(CONTENT_URI+"/"+resultmovie.getId()),null,null);
        Toast.makeText(DetailActivity.this, R.string.add, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }

    void loadIntent(){
        resultmovie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        resulttv = getIntent().getParcelableExtra(EXTRA_TV);
        if (resulttv != null){
            progressBar.setVisibility(View.GONE);
            is_movie=false;
            Log.d("tv", String.valueOf(resulttv.getId()));
            title.setText(resulttv.getTitle());
            overview.setText(resulttv.getOverview());
            Picasso.with(getApplicationContext()).load("https://image.tmdb.org/t/p/w500"+resulttv.getPhoto()).into(poster);
        }else {
            Log.d("movie", String.valueOf(resultmovie.getId()));
            progressBar.setVisibility(View.GONE);
            is_movie=true;
            Log.d("tv", String.valueOf(resultmovie.getId()));
            title.setText(resultmovie.getTitle());
            overview.setText(resultmovie.getOverview());
            Picasso.with(getApplicationContext()).load("https://image.tmdb.org/t/p/w500"+resultmovie.getPhoto()).into(poster);
        }
    }
}
