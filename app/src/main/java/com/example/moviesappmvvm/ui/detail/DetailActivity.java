package com.example.moviesappmvvm.ui.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.moviesappmvvm.R;
import com.example.moviesappmvvm.base.BaseActivity;
import com.example.moviesappmvvm.databinding.ActivityDetailBinding;
import com.example.moviesappmvvm.model.MovieModel;
import com.example.moviesappmvvm.ui.mainactivity.MainViewModel;
import com.squareup.picasso.Picasso;

public class DetailActivity extends BaseActivity<DetailViewModel, ActivityDetailBinding> {

    private static final String KEY_MOVIE_DETAIL="movie detail";
    public static void startDetailActivity(Context context, MovieModel movie){
        Intent detailActivityIntent=new Intent(context, DetailActivity.class);
        detailActivityIntent.putExtra(KEY_MOVIE_DETAIL, movie);
        context.startActivity(detailActivityIntent);
    }

    @Override
    protected ActivityDetailBinding setViewBinding() {
        return ActivityDetailBinding.inflate(getLayoutInflater());
    }

    @Override
    protected DetailViewModel setViewModel() {
        return new ViewModelProvider(this).get(DetailViewModel.class);
    }


    @Override
    protected void onPostCreated() {
        if(getIntent().getSerializableExtra(KEY_MOVIE_DETAIL)!=null){
            MovieModel movie= (MovieModel) getIntent().getSerializableExtra(KEY_MOVIE_DETAIL);
            viewBinding.tvMovieTitle.setText(movie.getTitle());
            viewBinding.tvOverview.setText(movie.getOverview());
            viewBinding.tvReleaseDate.setText(movie.getReleaseDate());
            viewBinding.rbRating.setRating((float)movie.getVoteAverage()/2);
            Picasso.with(this).load( movie.getPosterPath()).into(viewBinding.ivMoviePoster);
        }
    }
}