package com.example.init_app_vpn_native.base;

public interface MVPPresenter<V> {
    void onAttact(V view);
    void onDetact();
    boolean isAttacted();
}
