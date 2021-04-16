package com.example.init_app_vpn_native.di.component;

import android.app.Application;

import com.example.init_app_vpn_native.di.module.ActivityModule;
import com.example.init_app_vpn_native.di.scope.ActivityScope;
import com.example.init_app_vpn_native.ui.main.MainActivity;

import javax.inject.Scope;

import dagger.Component;

@Component(modules = {ActivityModule.class}, dependencies = {ApplicationComponent.class})
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
}
