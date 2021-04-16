package com.example.init_app_vpn_native.di.module;

import android.content.Context;

import com.example.init_app_vpn_native.MyApplication;
import com.example.init_app_vpn_native.data.AppDataHelper;
import com.example.init_app_vpn_native.data.IAppDataHelper;
import com.example.init_app_vpn_native.data.api.ApiHepler;
import com.example.init_app_vpn_native.data.api.IApiHelper;
import com.example.init_app_vpn_native.data.local.ILocalHepler;
import com.example.init_app_vpn_native.data.local.LocalHelper;
import com.example.init_app_vpn_native.data.realm.IRealmLocal;
import com.example.init_app_vpn_native.data.realm.RealmLocalImpl;
import com.example.init_app_vpn_native.data.share_pref.ISharePreferHelper;
import com.example.init_app_vpn_native.data.share_pref.SharePreferHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
@Module
public class ApplicationModule {
    MyApplication myApplication;

    public ApplicationModule(MyApplication myApplication) {
        this.myApplication = myApplication;
    }

    @Provides
    @Singleton
    Context providerApplicationContext() {
        return myApplication;
    }

    @Provides
    @Singleton
    IAppDataHelper providerAppDataHelper(IApiHelper apiHepler, ILocalHepler localHelper, ISharePreferHelper sharePreferHelper, IRealmLocal realmLocal) {
        return new AppDataHelper(apiHepler, localHelper, sharePreferHelper, realmLocal);
    }

    @Provides
    @Singleton
    IApiHelper providerApiHelper() {
        return new ApiHepler();
    }

    @Provides
    @Singleton
    ILocalHepler providerLocalHelper(Context context) {
        return new LocalHelper(context);
    }

    @Provides
    @Singleton
    ISharePreferHelper providerSharePref(Context context) {
        return new SharePreferHelper(context);
    }

    @Provides
    @Singleton
    IRealmLocal providerRealm() {
        return new RealmLocalImpl();
    }
}
