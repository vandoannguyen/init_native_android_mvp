package com.example.init_app_vpn_native.ui.main;

import android.app.Activity;
import android.util.Log;

import com.example.init_app_vpn_native.base.BasePresenter;
import com.example.init_app_vpn_native.data.AppDataHelper;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;

public class MainPresenter<V extends IMainActivity> extends BasePresenter<V> implements IMainPresenter<V> {
    Activity activity;
    private String TAG = "MainPresenter";

    public MainPresenter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void getExample() {
        view.showMessage("message");
        AppDataHelper.getInstance().getData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        Log.e(TAG, "onSubscribe: " );
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.e(TAG, "onNext: " + integer );
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.e(TAG, "onError: " );
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: "  );
                    }
                });
    }
}
