package com.example.init_app_vpn_native.ui.main;

import android.app.Activity;
import android.util.Log;

import com.example.init_app_vpn_native.base.BasePresenter;
import com.example.init_app_vpn_native.data.AppDataHelper;

import java.util.Calendar;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainPresenter<V extends IMainActivity> extends BasePresenter<V> implements IMainPresenter<V> {
    Activity activity;
    private String TAG = "MainPresenter";

    public MainPresenter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void insertNote() {
        AppDataHelper.getInstance(activity).insertNote(null)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.e(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(@NonNull Boolean aBoolean) {
                        Log.e(TAG, "onNext: " + Calendar.getInstance().getTimeInMillis() + " " + aBoolean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: ");

                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: ");

                    }
                });
    }

    @Override
    public void getExample() {
        view.showMessage("message");
        AppDataHelper.getInstance(activity).getData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getListNote() {

    }
}
