package com.example.init_app_vpn_native.data.api;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public interface IApiHelper {
    Observable<Integer> getData();
}
