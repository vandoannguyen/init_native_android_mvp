package com.example.init_app_vpn_native.base;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.example.init_app_vpn_native.BR;

public abstract class BaseActivity<VM extends BaseViewModel, BD extends ViewDataBinding> extends AppCompatActivity {
    private static final String TAG = "BaseActivity";
    public VM viewModel;
    protected BD binding;
    private int layout;

    public abstract VM createViewModel();

    public abstract int setContentView();

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layout = setContentView();
        this.viewModel = createViewModel();
        initViewBinding();
    }

    private void initViewBinding() {
        binding = DataBindingUtil.setContentView(this, layout);
        binding.setLifecycleOwner(this);
        binding.setVariable(BR.viewModel, this.viewModel);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
    }
}
