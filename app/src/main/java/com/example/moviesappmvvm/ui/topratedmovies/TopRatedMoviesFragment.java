package com.example.moviesappmvvm.ui.topratedmovies;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviesappmvvm.R;
import com.example.moviesappmvvm.adapter.MovieAdapter;
import com.example.moviesappmvvm.base.BaseFragment;
import com.example.moviesappmvvm.databinding.FragmentPopularMoviesBinding;
import com.example.moviesappmvvm.databinding.FragmentTopRatedMoviesBinding;
import com.example.moviesappmvvm.model.MovieModel;
import com.example.moviesappmvvm.network.EndPoints;
import com.example.moviesappmvvm.response.MoviesPayLoad;
import com.example.moviesappmvvm.ui.detail.DetailActivity;
import com.example.moviesappmvvm.ui.popularmovies.PopularMoviesFragment;
import com.example.moviesappmvvm.ui.popularmovies.PopularMoviesViewModel;

public class TopRatedMoviesFragment extends BaseFragment<TopRatedMoviesViewModel, FragmentTopRatedMoviesBinding> implements MovieAdapter.MovieClickListener {
    MovieAdapter movieAdapter;
    int pageCounter=1;
    boolean isLoading=true;
    private int totalNumberOfPages=0;

    @Override
    protected FragmentTopRatedMoviesBinding setViewBinding() {
        if(viewBinding==null) {
            viewBinding = FragmentTopRatedMoviesBinding.inflate(getLayoutInflater());
        }
        return viewBinding;
    }

    @Override
    protected TopRatedMoviesViewModel setViewModel() {
        return new ViewModelProvider(this).get(TopRatedMoviesViewModel.class);
    }

    @Override
    protected void onPostCreated() {
        MoviesPayLoad moviesPayLoad=new MoviesPayLoad(EndPoints.API_KEY, pageCounter);
        viewModel.getData(moviesPayLoad);
        movieAdapter=new MovieAdapter(getContext(), TopRatedMoviesFragment.this);
        viewBinding.rvTopRatedMovies.setAdapter(movieAdapter);
        viewBinding.rvTopRatedMovies.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                int listSize=((LinearLayoutManager) recyclerView.getLayoutManager()).getItemCount()-2;
                if (lastVisibleItemPosition>=listSize&&isLoading&&pageCounter<totalNumberOfPages) {
                    isLoading=false;
                    pageCounter++;
                    MoviesPayLoad moviesPayLoad2=new MoviesPayLoad(EndPoints.API_KEY, pageCounter);
                    viewModel.getData(moviesPayLoad2);
                }
            }
        });
        viewModel.moviesSubject.subscribe(movies->{
            if(movies!=null){
                totalNumberOfPages=movies.getTotalPages();
                if(movies.getResults().size()>0){
                    movieAdapter.updateList(movies.getResults());
                    isLoading=true;
                }
            }
        });
    }

    @Override
    public void onCLick(MovieModel movie) {
        DetailActivity.startDetailActivity(getContext(), movie);

    }
}