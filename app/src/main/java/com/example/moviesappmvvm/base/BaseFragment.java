package com.example.moviesappmvvm.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

public abstract class BaseFragment<VM extends BaseViewModel,V extends ViewBinding> extends Fragment {
    protected VM viewModel;
    protected V viewBinding=null;
    protected abstract V setViewBinding();
    protected abstract VM setViewModel();
    protected abstract void onPostCreated();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(viewBinding==null) {
            viewBinding = setViewBinding();
            viewModel = setViewModel();
            onPostCreated();
        }
        return viewBinding.getRoot();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
