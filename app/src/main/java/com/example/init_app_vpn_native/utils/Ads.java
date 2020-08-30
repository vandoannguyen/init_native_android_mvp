package com.example.init_app_vpn_native.utils;

import android.app.Activity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.init_app_vpn_native.R;
import com.example.init_app_vpn_native.dialog.DialogInterNative;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAdExtendedListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import static com.facebook.ads.AdSettings.IntegrationErrorMode.INTEGRATION_ERROR_CRASH_DEBUG_MODE;


public class Ads {
    public static int is_random = 5;
    Activity activity;
    private static Ads ads;
    public static String banner_id_admob = "ca-app-pub-3940256099942544/6300978111";
    public static String inter_id_admob = "ca-app-pub-3940256099942544/1033173712";
    public static String native_id_admob = "ca-app-pub-3940256099942544/2247696110";
    public static String rewar_id_admob = "ca-app-pub-3940256099942544/5224354917";
    public static String banner_id_fan = "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID";
    public static String inter_id_fan = "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID";
    public static String native_id_fan = "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID";
    public static String rewar_id_fan = "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID";
    public static boolean is_show_banner = true;
    public static boolean is_show_inter = true;
    public static boolean is_show_native = true;
    public static boolean is_show_rewar = true;
    public static boolean is_show_admob = true;
    public static boolean is_load_failed = false;

//    private String rewar_id_admob;

    public void initAds() {
        MobileAds.initialize(activity, "ca-app-pub-3940256099942544~3347511713");
        AudienceNetworkAds.initialize(activity);
        AdSettings.setIntegrationErrorMode(INTEGRATION_ERROR_CRASH_DEBUG_MODE);
    }

    public static Ads getInstance(Activity activity) {
        if (ads != null) {
            ads.activity = activity;
        } else ads = new Ads(activity);
        return ads;
    }

    private Ads(Activity activity) {
        this.activity = activity;
    }

    public void banner(final ViewGroup frameAds, final AdsSize adsSize) {
        if (is_show_banner)
            if (is_show_admob) {
                AdsUtils.getInstance(activity).bannerAdMob(frameAds,
                        banner_id_admob,
                        adsSize == AdsSize.BANNER ? AdSize.BANNER
                                : adsSize == AdsSize.LARGE
                                ? AdSize.LARGE_BANNER
                                : AdSize.MEDIUM_RECTANGLE);
            } else {
                AdsUtils.getInstance(activity).bannerFan(frameAds, banner_id_fan, adsSize == AdsSize.BANNER ? com.facebook.ads.AdSize.BANNER_HEIGHT_50
                        : adsSize == AdsSize.LARGE
                        ? com.facebook.ads.AdSize.BANNER_HEIGHT_90
                        : com.facebook.ads.AdSize.RECTANGLE_HEIGHT_250, new AdsUtils.FanNativeCallBack() {
                    @Override
                    public void onError() {
                        if (is_load_failed)
                            AdsUtils.getInstance(activity).bannerAdMob(frameAds,
                                    banner_id_admob,
                                    adsSize == AdsSize.BANNER ? AdSize.BANNER
                                            : adsSize == AdsSize.LARGE
                                            ? AdSize.LARGE_BANNER
                                            : AdSize.MEDIUM_RECTANGLE);
                    }

                    @Override
                    public void onSuccess() {

                    }
                });
            }
    }

    public void bannerNative(final ViewGroup frameAds, final AdsSize adsSize) {
        if (is_show_banner)
            if (is_show_admob) {
                AdsUtils.getInstance(activity).bannerAdMob(frameAds,
                        banner_id_admob,
                        adsSize == AdsSize.BANNER ? AdSize.BANNER
                                : adsSize == AdsSize.LARGE
                                ? AdSize.LARGE_BANNER
                                : AdSize.MEDIUM_RECTANGLE);
            } else {
                AdsUtils.getInstance(activity).nativeFan(frameAds, banner_id_fan, adsSize == AdsSize.BANNER ? R.layout.fan_native_layout_medium_small
                        : adsSize == AdsSize.LARGE
                        ? R.layout.fan_native_layout_medium_small
                        : R.layout.fan_native_layout_medium, new AdsUtils.FanNativeCallBack() {
                    @Override
                    public void onError() {
                        if (is_load_failed) {
                            AdsUtils.getInstance(activity).bannerAdMob(frameAds,
                                    banner_id_admob,
                                    adsSize == AdsSize.BANNER ? AdSize.BANNER
                                            : adsSize == AdsSize.LARGE
                                            ? AdSize.LARGE_BANNER
                                            : AdSize.MEDIUM_RECTANGLE);
                        }
                    }

                    @Override
                    public void onSuccess() {

                    }
                });
            }
    }

    public void banner(final ViewGroup frameAds, final AdsSize adsSize, boolean is_dmob) {
        if (is_show_banner)
            if (is_dmob) {
                AdsUtils.getInstance(activity).bannerAdMob(frameAds,
                        banner_id_admob,
                        adsSize == AdsSize.BANNER ? AdSize.BANNER
                                : adsSize == AdsSize.LARGE
                                ? AdSize.LARGE_BANNER
                                : AdSize.MEDIUM_RECTANGLE);
            } else {
                AdsUtils.getInstance(activity).bannerFan(frameAds, banner_id_fan, adsSize == AdsSize.BANNER ? com.facebook.ads.AdSize.BANNER_HEIGHT_50
                        : adsSize == AdsSize.LARGE
                        ? com.facebook.ads.AdSize.BANNER_HEIGHT_90
                        : com.facebook.ads.AdSize.RECTANGLE_HEIGHT_250, new AdsUtils.FanNativeCallBack() {
                    @Override
                    public void onError() {
                        if (is_load_failed)
                            AdsUtils.getInstance(activity).bannerAdMob(frameAds,
                                    banner_id_admob,
                                    adsSize == AdsSize.BANNER ? AdSize.BANNER
                                            : adsSize == AdsSize.LARGE
                                            ? AdSize.LARGE_BANNER
                                            : AdSize.MEDIUM_RECTANGLE);
                    }

                    @Override
                    public void onSuccess() {

                    }
                });
            }
    }

    public void inter(final CallBackAds callBackInter) {
        if (is_show_inter)
            if (is_show_admob) {
                AdsUtils.getInstance(activity).interAdmob(inter_id_admob, new AdListener() {
                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();
                        callBackInter.adClose();
                    }

                    @Override
                    public void onAdFailedToLoad(int i) {
                        super.onAdFailedToLoad(i);
                        callBackInter.adLoadFailed(i);
                    }
                });
            } else {
//            AdsUtils.getInstance()
                new DialogInterNative(activity, new DialogInterNative.InterNativeAdsCallBack() {
                    @Override
                    public void onError() {
                        if (is_load_failed) {
                            AdsUtils.getInstance(activity).interAdmob(inter_id_admob, new AdListener() {
                                @Override
                                public void onAdClosed() {
                                    super.onAdClosed();
                                    callBackInter.adClose();
                                }

                                @Override
                                public void onAdFailedToLoad(int i) {
                                    super.onAdFailedToLoad(i);
                                    callBackInter.adLoadFailed(i);
                                }
                            });
                        } else
                            callBackInter.adLoadFailed(-1);
                    }

                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onClose() {
                        callBackInter.adClose();
                    }
                }).show();
            }
        else {
            callBackInter.adClose();
        }
    }

    public void inter(final CallBackAds callBackInter, boolean is_dmob) {
        if (is_show_inter)
            if (is_dmob) {
                AdsUtils.getInstance(activity).interAdmob(inter_id_admob, new AdListener() {
                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();
                        callBackInter.adClose();
                    }

                    @Override
                    public void onAdFailedToLoad(int i) {
                        super.onAdFailedToLoad(i);
                        callBackInter.adLoadFailed(i);
                    }
                });
            } else {
                AdsUtils.getInstance(activity).interFan(inter_id_fan, new InterstitialAdExtendedListener() {
                    @Override
                    public void onInterstitialActivityDestroyed() {

                    }

                    @Override
                    public void onInterstitialDisplayed(Ad ad) {

                    }

                    @Override
                    public void onInterstitialDismissed(Ad ad) {
                        callBackInter.adClose();
                    }

                    @Override
                    public void onError(Ad ad, AdError adError) {
                        callBackInter.adLoadFailed(-1);
                    }

                    @Override
                    public void onAdLoaded(Ad ad) {

                    }

                    @Override
                    public void onAdClicked(Ad ad) {

                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {

                    }

                    @Override
                    public void onRewardedAdCompleted() {

                    }

                    @Override
                    public void onRewardedAdServerSucceeded() {

                    }

                    @Override
                    public void onRewardedAdServerFailed() {

                    }
                });
            }
        else {
            callBackInter.adClose();
        }
    }

    public void interWithNative(final CallBackAds callBackInter, boolean is_dmob) {
        if (is_show_inter)
            if (is_dmob) {
                AdsUtils.getInstance(activity).interAdmob(inter_id_admob, new AdListener() {
                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();
                        callBackInter.adClose();
                    }

                    @Override
                    public void onAdFailedToLoad(int i) {
                        super.onAdFailedToLoad(i);
                        callBackInter.adLoadFailed(i);
                    }
                });
            } else {
                new DialogInterNative(activity, new DialogInterNative.InterNativeAdsCallBack() {
                    @Override
                    public void onError() {
                        callBackInter.adLoadFailed(-1);
                    }

                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onClose() {
                        callBackInter.adClose();
                    }
                }).show();
            }
        else {
            callBackInter.adClose();
        }
    }

    public void interWithNative(final CallBackAds callBackInter) {
        if (is_show_inter)
            if (is_show_admob) {
                AdsUtils.getInstance(activity).interAdmob(inter_id_admob, new AdListener() {
                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();
                        callBackInter.adClose();
                    }

                    @Override
                    public void onAdFailedToLoad(int i) {
                        super.onAdFailedToLoad(i);
                        callBackInter.adLoadFailed(i);
                    }
                });
            } else {
                new DialogInterNative(activity, new DialogInterNative.InterNativeAdsCallBack() {
                    @Override
                    public void onError() {
                        callBackInter.adLoadFailed(-1);
                    }

                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onClose() {
                        callBackInter.adClose();
                    }
                }).show();
            }
        else {
            callBackInter.adClose();
        }
    }

    public void nativeAds(final ViewGroup frmAds, final NativeSize size) {
        if (is_show_native)
            if (is_show_admob) {
                AdsUtils.getInstance(activity).nativeAdmob((FrameLayout) frmAds, size == NativeSize.MEDIUM ? R.layout.ad_unified_draw_navigator : R.layout.ad_unified_draw_navigator_small, new AdListener() {

                }, native_id_admob);
            } else {
                AdsUtils.getInstance(activity).nativeFan(frmAds, native_id_fan, size == NativeSize.MEDIUM ? R.layout.fan_native_layout_medium : R.layout.fan_native_layout_medium_small, new AdsUtils.FanNativeCallBack() {
                    @Override
                    public void onError() {
                        frmAds.removeAllViews();
                        if (is_load_failed)
                            AdsUtils.getInstance(activity).nativeAdmob((FrameLayout) frmAds, size == NativeSize.MEDIUM ? R.layout.ad_unified_draw_navigator : R.layout.ad_unified_draw_navigator_small, new AdListener() {

                            }, native_id_admob);
                    }

                    @Override
                    public void onSuccess() {

                    }
                });
            }
    }

    public void nativeAds(final ViewGroup frmAds, final NativeSize size, boolean is_dmob) {
        if (is_show_native)
            if (is_dmob) {
                AdsUtils.getInstance(activity).nativeAdmob((FrameLayout) frmAds, size == NativeSize.MEDIUM ? R.layout.ad_unified_draw_navigator : R.layout.ad_unified_draw_navigator_small, new AdListener() {

                }, native_id_admob);
            } else {
                AdsUtils.getInstance(activity).nativeFan(frmAds, native_id_fan, size == NativeSize.MEDIUM ? R.layout.fan_native_layout_medium : R.layout.fan_native_layout_medium_small, new AdsUtils.FanNativeCallBack() {
                    @Override
                    public void onError() {
                        frmAds.removeAllViews();
                        AdsUtils.getInstance(activity).nativeAdmob((FrameLayout) frmAds, size == NativeSize.MEDIUM ? R.layout.ad_unified_draw_navigator : R.layout.ad_unified_draw_navigator_small, new AdListener() {

                        }, native_id_admob);
                    }

                    @Override
                    public void onSuccess() {

                    }
                });
            }
    }

    public void inter_nativeAds(ViewGroup frmAds, final AdsUtils.FanNativeCallBack callBack) {
//        if (is_show_admob) {
//            AdsUtils.getInstance(activity).nativeAdmob((FrameLayout) frmAds, size == NativeSize.MEDIUM ? R.layout.ad_unified_draw_navigator : R.layout.ad_unified_draw_navigator_small, new AdListener() {
//
//            }, native_id_admob);
//        } else {
//        if (is_show_admob)
        AdsUtils.getInstance(activity).nativeFan(frmAds, native_id_fan, R.layout.fan_native_layout_medium_inter, new AdsUtils.FanNativeCallBack() {
            @Override
            public void onError() {
                callBack.onError();
//                    frmAds.removeAllViews();
//                    AdsUtils.getInstance(activity).nativeAdmob((FrameLayout) frmAds, size == NativeSize.MEDIUM ? R.layout.ad_unified_draw_navigator : R.layout.ad_unified_draw_navigator_small, new AdListener(){
//
//                    }, native_id_admob);
            }

            @Override
            public void onSuccess() {
                callBack.onSuccess();
            }
        });
//        else callBack.onSuccess();
//        }
    }

    public void rewared(final CallBackAds rewared) {
        if (is_show_admob) {
            AdsUtils.getInstance(activity).rewar_admob(new RewardedVideoAdListener() {

                @Override
                public void onRewardedVideoAdLoaded() {

                }

                @Override
                public void onRewardedVideoAdOpened() {

                }

                @Override
                public void onRewardedVideoStarted() {

                }

                @Override
                public void onRewardedVideoAdClosed() {
                    rewared.adClose();

                }

                @Override
                public void onRewarded(RewardItem rewardItem) {
                    rewared.adRewared();
                }

                @Override
                public void onRewardedVideoAdLeftApplication() {

                }

                @Override
                public void onRewardedVideoAdFailedToLoad(int i) {
                    rewared.adLoadFailed(i);
                }

                @Override
                public void onRewardedVideoCompleted() {

                }
            });
        } else {
            AdsUtils.getInstance(activity).rewar_fan(new com.facebook.ads.RewardedVideoAdListener() {
                @Override
                public void onRewardedVideoCompleted() {
                    rewared.adRewared();
                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }

                @Override
                public void onRewardedVideoClosed() {
                    rewared.adClose();
                }

                @Override
                public void onError(Ad ad, AdError adError) {
                    Log.e("TAG", "onError: " + adError.getErrorMessage());
//                    rewared.adLoadFailed(0);
                    if (is_load_failed)
                        AdsUtils.getInstance(activity).rewar_admob(new RewardedVideoAdListener() {

                            @Override
                            public void onRewardedVideoAdLoaded() {
                            }

                            @Override
                            public void onRewardedVideoAdOpened() {

                            }

                            @Override
                            public void onRewardedVideoStarted() {

                            }

                            @Override
                            public void onRewardedVideoAdClosed() {
                                rewared.adClose();

                            }

                            @Override
                            public void onRewarded(RewardItem rewardItem) {
                                rewared.adRewared();
                            }

                            @Override
                            public void onRewardedVideoAdLeftApplication() {

                            }

                            @Override
                            public void onRewardedVideoAdFailedToLoad(int i) {
                                rewared.adLoadFailed(i);
                            }

                            @Override
                            public void onRewardedVideoCompleted() {

                            }
                        });
                    else {
                        rewared.adLoadFailed(-1);
                    }

                }

                @Override
                public void onAdLoaded(Ad ad) {

                }

                @Override
                public void onAdClicked(Ad ad) {

                }
            });
        }
    }

    public enum AdsSize {
        BANNER,
        MEDIUM,
        LARGE
    }

    public enum NativeSize {
        SMALL,
        MEDIUM
    }

    public abstract static class CallBackAds {
        public void adClose() {
        }

        ;

        public void adLoadFailed(int i) {
        }

        ;

        public void adRewared() {
        }

        ;
    }

    public interface CallBackInter {

        void adClose();

        void adLoadFailed(int i);
    }

    public interface CallBackRewared {

        void adClose();

        void adLoadFailed(int i);

        void adRewared();
    }
}