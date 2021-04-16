package com.example.init_app_vpn_native.di.component;

import com.example.init_app_vpn_native.MyApplication;
import com.example.init_app_vpn_native.data.IAppDataHelper;
import com.example.init_app_vpn_native.di.module.ApplicationModule;

import javax.inject.Inject;

import dagger.Component;

@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject(MyApplication myApplication);

    IAppDataHelper getAppDataHelper();
}
