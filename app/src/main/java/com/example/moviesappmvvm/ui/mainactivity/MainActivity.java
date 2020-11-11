package com.example.moviesappmvvm.ui.mainactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.moviesappmvvm.R;
import com.example.moviesappmvvm.base.BaseActivity;
import com.example.moviesappmvvm.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity<MainViewModel, ActivityMainBinding> {

    @Override
    protected ActivityMainBinding setViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    protected MainViewModel setViewModel() {
        return new ViewModelProvider(this).get(MainViewModel.class);
    }

    @Override
    protected void onPostCreated() {
        NavController navController = Navigation.findNavController(this, R.id.myNavHostFragment);
        NavigationUI.setupWithNavController(viewBinding.bottomNav, navController);
        viewBinding.bottomNav.setBackground(null);
        viewBinding.bottomNav.getMenu().getItem(1).setEnabled(false);
        viewBinding.fabHome.setOnClickListener(view -> {
            navController.navigateUp();
            navController.navigate(R.id.home_fragment);
        });
    }

}