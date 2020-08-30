package com.example.init_app_vpn_native.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;


import com.example.init_app_vpn_native.R;
import com.example.init_app_vpn_native.utils.Ads;
import com.example.init_app_vpn_native.utils.AdsUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialogInterNative extends Dialog {
    @BindView(R.id.frmAdsDemo)
    FrameLayout frmAdsDemo;
    @BindView(R.id.imgCancel)
    ImageView imgCancel;
    @BindView(R.id.progressLoading)
    ProgressBar progressLoading;
    InterNativeAdsCallBack interNativeAdsCallBack;
    Activity activity;
    public DialogInterNative(Context context) {
        super(context);
    }
    public DialogInterNative(Activity activity, InterNativeAdsCallBack callBack) {
        super(activity, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        this.activity = activity;
        this.interNativeAdsCallBack = callBack;
//        super(context);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inter_native);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        ButterKnife.bind(this);
        setCanceledOnTouchOutside(false);
        Ads.getInstance(activity).interWithNative(new Ads.CallBackAds() {
            @Override
            public void adClose() {
                super.adClose();
            }

            @Override
            public void adLoadFailed(int i) {
                super.adLoadFailed(i);
            }
        });
    }
    @Override
    public void onBackPressed() {
        interNativeAdsCallBack.onClose();
        super.onBackPressed();
    }
    private void showCancel() {
        imgCancel.setVisibility(View.VISIBLE);
        progressLoading.setVisibility(View.GONE);
    }
    public interface InterNativeAdsCallBack {
        void onError();
        void onSuccess();
        void onClose();
    }
    @OnClick(R.id.imgCancel)
    public void onViewClicked() {
        hide();
        interNativeAdsCallBack.onClose();
    }
}