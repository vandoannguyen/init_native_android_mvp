package com.example.init_app_vpn_native.data.api;

import io.reactivex.rxjava3.core.Flowable;

public class ApiHepler implements IApiHelper {

    @Override
    public Flowable<Integer> getData() {
        return Flowable.range(1, 1000);
    }
}
