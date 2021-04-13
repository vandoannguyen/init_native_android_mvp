package com.example.init_app_vpn_native.base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * Created by Nguyen Van Doan 5/2/2021
 */
public abstract class BaseViewModel extends ViewModel {
    public BaseViewModel() {
        initViewModelData();
        this.disposabl = new CompositeDisposable();
    }

    MutableLiveData<Void> showLoading;
    MutableLiveData<Void> hideLoading;
    CompositeDisposable disposabl;

    public void initViewModelData() {
        showLoading = new MutableLiveData<>();
        hideLoading = new MutableLiveData<>();
    }

    void onDestroy() {
        if (disposabl != null) disposabl.clear();
    }

    protected void addDisposable(Disposable disposable) {
        this.disposabl.add(disposable);
    }
}
