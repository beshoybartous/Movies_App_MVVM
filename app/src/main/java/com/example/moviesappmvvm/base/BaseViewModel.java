package com.example.moviesappmvvm.base;

import androidx.lifecycle.ViewModel;

import com.example.moviesappmvvm.network.NetworkManager;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseViewModel extends ViewModel {
    protected NetworkManager networkManager;
    protected CompositeDisposable disposable;
    public BaseViewModel(){
        networkManager=new NetworkManager();
        disposable=new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
        disposable.dispose();
    }
}
