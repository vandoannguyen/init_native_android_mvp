package com.example.init_app_vpn_native.ui.main;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MainViewModelFactory implements ViewModelProvider.Factory {
    Context context;

    public MainViewModelFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
//        if (modelClass.isAssignableFrom(MainViewModelFactory.class))
            return (T) new MainViewModel(context);
//        else throw new IllegalArgumentException("not instance of MainViewModelFactory");
    }
}

