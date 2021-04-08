package com.example.init_app_vpn_native.base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Created by Nguyen Van Doan 5/2/2021
 */
public abstract class BaseViewModel extends ViewModel {
    public BaseViewModel() {
        initViewModelData();
    }

    MutableLiveData<Void> showLoading;
    MutableLiveData<Void> hideLoading;

    public void initViewModelData() {
        showLoading = new MutableLiveData<>();
        hideLoading = new MutableLiveData<>();
    }
}
