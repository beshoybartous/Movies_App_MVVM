package com.example.moviesappmvvm.ui.popularmovies;

import android.util.Log;

import com.example.moviesappmvvm.base.BaseViewModel;
import com.example.moviesappmvvm.model.MovieModel;
import com.example.moviesappmvvm.network.EndPoints;
import com.example.moviesappmvvm.response.MoviesPayLoad;
import com.example.moviesappmvvm.response.MoviesResponse;

import io.reactivex.observers.DisposableObserver;
import io.reactivex.subjects.BehaviorSubject;
public class PopularMoviesViewModel extends BaseViewModel {

    public BehaviorSubject<MoviesResponse> moviesSubject =BehaviorSubject.create();

    public void getData(MoviesPayLoad payLoad ){
        disposable.add(networkManager.getData(EndPoints.POPULAR_MOVIES, payLoad.toMap(), MoviesResponse.class).
                subscribeWith(new DisposableObserver<MoviesResponse>() {
            @Override
            public void onNext(MoviesResponse moviesResponse) {
                if(moviesResponse!=null){
                    if(moviesResponse.getResults().size()>0) {
                        moviesSubject.onNext(moviesResponse);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
                dispose();
            }
        }));
    }

}
