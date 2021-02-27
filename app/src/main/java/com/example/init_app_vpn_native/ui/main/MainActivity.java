package com.example.init_app_vpn_native.ui.main;


import android.os.Bundle;

import com.example.init_app_vpn_native.R;
import com.example.init_app_vpn_native.base.BaseActivity;

public class MainActivity extends BaseActivity implements IMainActivity {
    MainPresenter<IMainActivity> presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenter<>(this);
        presenter.onAttact(this);
        presenter.getExample();
    }

    @Override
    public void showMessage(String mess) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDetact();
        }
    }
}