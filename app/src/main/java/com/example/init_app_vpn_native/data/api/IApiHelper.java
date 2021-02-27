package com.example.init_app_vpn_native.data.api;

import io.reactivex.rxjava3.core.Flowable;

public interface IApiHelper {
    Flowable<Integer> getData();
}
