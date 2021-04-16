package com.example.init_app_vpn_native.di.module;

import androidx.lifecycle.ViewModelProvider;

import com.example.init_app_vpn_native.base.BaseActivity;
import com.example.init_app_vpn_native.base.BaseViewModelFactory;
import com.example.init_app_vpn_native.data.IAppDataHelper;
import com.example.init_app_vpn_native.ui.main.MainViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    BaseActivity<?, ?> baseActivity;

    public ActivityModule(BaseActivity<?, ?> baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Provides
    MainViewModel providerMainViewModel(IAppDataHelper appDataHelper) {
        BaseViewModelFactory<MainViewModel> factory;
        factory = new BaseViewModelFactory(new MainViewModel(appDataHelper));
        return new ViewModelProvider(baseActivity, factory).get(MainViewModel.class);
    }
}
