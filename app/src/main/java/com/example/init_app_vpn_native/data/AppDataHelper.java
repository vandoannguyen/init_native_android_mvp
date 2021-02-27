package com.example.init_app_vpn_native.data;

import com.example.init_app_vpn_native.data.api.ApiHepler;
import com.example.init_app_vpn_native.data.local.LocalHelper;
import com.example.init_app_vpn_native.data.share_pref.SharePreferHelper;

import io.reactivex.rxjava3.core.Flowable;

public class AppDataHelper implements IAppDataHelper {
    private static AppDataHelper dataHelper;
    private ApiHepler apiHepler;
    private LocalHelper localHelper;
    private SharePreferHelper sharePreferHelper;

    public static AppDataHelper getInstance() {
        return dataHelper != null ? dataHelper : (dataHelper = new AppDataHelper());
    }

    private AppDataHelper() {
        apiHepler = new ApiHepler();
        localHelper = new LocalHelper();
        sharePreferHelper = new SharePreferHelper();
    }

    @Override
    public Flowable<Integer> getData() {
        return apiHepler.getData();
    }
}
