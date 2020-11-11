package com.example.moviesappmvvm.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

public abstract class BaseActivity <VM extends BaseViewModel,V extends ViewBinding> extends AppCompatActivity {
    protected VM viewModel;
    protected V viewBinding;
    protected abstract V setViewBinding();
    protected abstract VM setViewModel();
    protected abstract void onPostCreated();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding=setViewBinding();
        setContentView(viewBinding.getRoot());
        viewModel=setViewModel();
        onPostCreated();
    }
}
