package com.example.init_app_vpn_native.data.api;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public class ApiHepler implements IApiHelper {

    @Override
    public Observable<Integer> getData() {
        return Observable.range(1, 1000);
    }
}
