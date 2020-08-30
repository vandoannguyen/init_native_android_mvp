package com.example.init_app_vpn_native.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.init_app_vpn_native.R;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdIconView;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.util.ArrayList;
import java.util.List;


public class AdsUtils {
    private static final String TAG ="AdsUtils" ;
    Activity context;
    private static AdsUtils adsUtils;

    public static AdsUtils getInstance(Activity context) {
        if (adsUtils != null) {
            adsUtils.context = context;
        }
        adsUtils = new AdsUtils(context);
        return adsUtils;
    }

    private AdsUtils(Activity context) {
        this.context = context;
    }

    public void bannerAdMob(ViewGroup frameAds, String idBanner, com.google.android.gms.ads.AdSize bannerSize) {
        com.google.android.gms.ads.AdView adView = new com.google.android.gms.ads.AdView(context);
        adView.setAdUnitId(idBanner);
        adView.setAdSize(bannerSize);
        adView.loadAd(new AdRequest.Builder().build());
        frameAds.removeAllViews();
        frameAds.addView(adView);
    }

    public void nativeAdmob(final FrameLayout frameLayout, final int layout, final com.google.android.gms.ads.AdListener adListener, String id_inter) {
        UnifiedNativeAd nativeAd;
        AdLoader.Builder builder = new AdLoader.Builder(context, id_inter).withAdListener(new com.google.android.gms.ads.AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                adListener.onAdClosed();
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
                adListener.onAdLeftApplication();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
                adListener.onAdOpened();
            }

            @Override
            public void onAdClicked() {
                super.onAdClicked();
                adListener.onAdClicked();
            }

            @Override
            public void onAdImpression() {
                super.onAdImpression();
                adListener.onAdImpression();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                adListener.onAdFailedToLoad(errorCode);
//                refresh.setEnabled(true);
                if (frameLayout != null) frameLayout.removeAllViews();
                Toast.makeText(context, "Failed to load native ad: "
                        + errorCode, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                adListener.onAdLoaded();
            }
        });

        builder.forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
            @Override
            public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
//                FrameLayout frameLayout =
//                        findViewById(idFrrame);
                UnifiedNativeAdView adView = (UnifiedNativeAdView) context.getLayoutInflater()
                        .inflate(layout, null);
                populateUnifiedNativeAdView(unifiedNativeAd, adView);
                frameLayout.removeAllViews();
                frameLayout.addView(adView);
            }

        });
        VideoOptions videoOptions = new VideoOptions.Builder()
//                .setStartMuted(startVideoAdsMuted.isChecked())
                .build();

        NativeAdOptions adOptions = new NativeAdOptions.Builder()
                .setVideoOptions(videoOptions)
                .build();

        builder.withNativeAdOptions(adOptions);
        AdLoader adLoader = builder.build();
        adLoader.loadAd(new PublisherAdRequest.Builder().build());
//        videoStatus.setText("");
    }

    public void interAdmob(String idInter, final com.google.android.gms.ads.AdListener listener) {
        final com.google.android.gms.ads.InterstitialAd interstitialAd = new com.google.android.gms.ads.InterstitialAd(context);
        interstitialAd.setAdUnitId(idInter);
        interstitialAd.setAdListener(new com.google.android.gms.ads.AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                listener.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                listener.onAdFailedToLoad(i);
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
                listener.onAdLeftApplication();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
                listener.onAdOpened();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                listener.onAdLoaded();
                interstitialAd.show();
            }

            @Override
            public void onAdClicked() {
                super.onAdClicked();
                listener.onAdClicked();
            }

            @Override
            public void onAdImpression() {
                super.onAdImpression();
                listener.onAdImpression();
            }
        });
        interstitialAd.loadAd(new AdRequest.Builder().build());
    }

    /**
     * @param frameAds
     * @param idBanner
     * @param bannerSize
     * @param callError  Standard Banner
     *                   <p>
     *                   BANNER_50
     *                   <p>
     *                   320x50
     *                   <p>
     *                   This banner is best suited to phones
     *                   <p>
     *                   Large Banner
     *                   <p>
     *                   BANNER_90
     *                   <p>
     *                   320x90
     *                   <p>
     *                   This banner is best suited to tablets and larger devices
     *                   <p>
     *                   Medium Rectangle
     *                   <p>
     *                   RECTANGLE_HEIGHT_250
     *                   <p>
     *                   300x250
     *                   <p>
     *                   This format is best suited for scrollable feeds or end-of-level screens
     */
    public void bannerFan(ViewGroup frameAds, String idBanner, AdSize bannerSize, final FanNativeCallBack callError) {
        AdView adView = new AdView(context, idBanner, bannerSize);
        adView.setAdListener(new AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
                callError.onError();
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
        });
        adView.loadAd();
        frameAds.removeAllViews();
        frameAds.addView(adView);
    }

    public void nativeFan(final ViewGroup frameAds, String idNative, final int layout, final FanNativeCallBack callError) {
        final NativeAd nativeAd = new NativeAd(context, idNative);
        nativeAd.setAdListener(new NativeAdListener() {

            @Override
            public void onMediaDownloaded(Ad ad) {
                if (nativeAd == null || nativeAd != ad) {
                    return;
                }
                inflateAd(context, nativeAd, frameAds, layout);
                callError.onSuccess();
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                if (callError != null) {
                    callError.onError();
                }
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
        });
        nativeAd.loadAd();
    }

    public void interFan(String idInter, final InterstitialAdListener interstitialAdListener) {
        final InterstitialAd interstitialAd = new InterstitialAd(context, idInter);
        interstitialAd.setAdListener(new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                interstitialAdListener.onInterstitialDisplayed(ad);
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                interstitialAdListener.onInterstitialDismissed(ad);
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                interstitialAdListener.onError(ad, adError);
            }

            @Override
            public void onAdLoaded(Ad ad) {
                interstitialAd.show();
            }

            @Override
            public void onAdClicked(Ad ad) {
                interstitialAdListener.onAdClicked(ad);
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                interstitialAdListener.onLoggingImpression(ad);
            }
        });
        interstitialAd.loadAd();
    }

    private void inflateAd(Context context, NativeAd nativeAd, ViewGroup frmads, int layout) {
        nativeAd.unregisterView();
        NativeAdLayout nativeAdLayout = new NativeAdLayout(context);
        // Add the Ad view into the ad container.
        LayoutInflater inflater = LayoutInflater.from(context);
        // Inflate the Ad view.  The layout referenced should be the one you created in the last step.
        View adView = (LinearLayout) inflater.inflate(layout, nativeAdLayout, false);
        nativeAdLayout.addView(adView);

        // Add the AdOptionsView
        LinearLayout adChoicesContainer = adView.findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(context, nativeAd, nativeAdLayout);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);

        // Create native UI using the ad metadata.
        AdIconView nativeAdIcon = adView.findViewById(R.id.native_ad_icon);
        TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
        MediaView nativeAdMedia = adView.findViewById(R.id.native_ad_media);
        TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
        TextView nativeAdBody = adView.findViewById(R.id.native_ad_body);
        TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
        Button nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);

        // Set the Text.
        nativeAdTitle.setText(nativeAd.getAdvertiserName());
        nativeAdBody.setText(nativeAd.getAdBodyText());
        nativeAdSocialContext.setText(nativeAd.getAdSocialContext());
        nativeAdCallToAction.setVisibility(nativeAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdCallToAction.setText(nativeAd.getAdCallToAction());
        sponsoredLabel.setText(nativeAd.getSponsoredTranslation());

        // Create a list of clickable views
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);
        // Register the Title and CTA button to listen for clicks.
        nativeAd.registerViewForInteraction(
                adView,
                nativeAdMedia,
                nativeAdIcon,
                clickableViews);
        Log.e(TAG, "inflateAd: "  + frmads.getChildCount() );
        frmads.removeAllViews();
        frmads.addView(nativeAdLayout);
    }

    private void populateUnifiedNativeAdView(UnifiedNativeAd unifiedNativeAd, UnifiedNativeAdView adView) {
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setMediaView((com.google.android.gms.ads.formats.MediaView) adView.findViewById(R.id.ad_media));
//        adView.setMediaView(null);
        adView.setPriceView(adView.findViewById(R.id.ad_price));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));

        if (adView.getPriceView() != null) {
            if (unifiedNativeAd.getPrice() == null)
                adView.getPriceView().setVisibility(View.GONE);
            else {
                ((TextView) adView.getPriceView()).setText(unifiedNativeAd.getPrice());
            }
        }
        if (adView.getIconView() != null) {
            if (unifiedNativeAd.getIcon() == null)
                adView.getIconView().setVisibility(View.GONE);
            else {
                adView.getIconView().setVisibility(View.VISIBLE);
                ((ImageView) adView.getIconView()).setImageDrawable(unifiedNativeAd.getIcon().getDrawable());
            }
        }
        if (adView.getHeadlineView() != null) {
            if (unifiedNativeAd.getHeadline() == null)
                adView.getHeadlineView().setVisibility(View.GONE);
            else {
                adView.getHeadlineView().setVisibility(View.VISIBLE);
                ((TextView) adView.getHeadlineView()).setText(unifiedNativeAd.getHeadline());
            }
        }
        if (adView.getAdvertiserView() != null) {
            if (unifiedNativeAd.getAdvertiser() == null)
                adView.getAdvertiserView().setVisibility(View.GONE);
            else {
                adView.getAdvertiserView().setVisibility(View.VISIBLE);
                ((TextView) adView.getAdvertiserView()).setText(unifiedNativeAd.getAdvertiser());
            }
        }
        if (adView.getBodyView() != null) {
            if (unifiedNativeAd.getBody() == null)
                adView.getBodyView().setVisibility(View.GONE);
            else {
                adView.getBodyView().setVisibility(View.VISIBLE);
                ((TextView) adView.getBodyView()).setText(unifiedNativeAd.getBody());
            }
        }
        if (adView.getStarRatingView() != null) {
            if (unifiedNativeAd.getStarRating() == null)
                adView.getStarRatingView().setVisibility(View.GONE);
            else {
                adView.getStarRatingView().setVisibility(View.VISIBLE);
                ((RatingBar) adView.getStarRatingView()).setRating(unifiedNativeAd.getStarRating().floatValue());
            }
        }
        if (adView.getMediaView() != null) {
            if (unifiedNativeAd.getMediaContent() == null)
                adView.getMediaView().setVisibility(View.GONE);
            else {
                adView.getMediaView().setVisibility(View.VISIBLE);
                ((com.google.android.gms.ads.formats.MediaView) adView.getMediaView()).setMediaContent(unifiedNativeAd.getMediaContent());
            }
        }
        if (adView.getPriceView() != null) {
            if (unifiedNativeAd.getPrice() == null)
                adView.getPriceView().setVisibility(View.GONE);
            else {
                adView.getPriceView().setVisibility(View.VISIBLE);
                ((TextView) adView.getPriceView()).setText(unifiedNativeAd.getPrice());
                if (unifiedNativeAd.getPrice().equals("0"))
                    adView.getPriceView().setVisibility(View.GONE);

            }
        }
        if (adView.getCallToActionView() != null) {
            if (unifiedNativeAd.getCallToAction() == null)
                adView.getCallToActionView().setVisibility(View.GONE);
            else {
                adView.getCallToActionView().setVisibility(View.VISIBLE);
                ((TextView) adView.getCallToActionView()).setText(unifiedNativeAd.getCallToAction());
            }
        }
        adView.setNativeAd(unifiedNativeAd);
    }

    public void rewar_admob(final RewardedVideoAdListener listener) {
        final RewardedVideoAd rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(context);
        rewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
            @Override
            public void onRewardedVideoAdLoaded() {
                rewardedVideoAd.show();
                listener.onRewardedVideoAdLoaded();
            }

            @Override
            public void onRewardedVideoAdOpened() {
                listener.onRewardedVideoAdOpened();
            }

            @Override
            public void onRewardedVideoStarted() {
                listener.onRewardedVideoStarted();
            }

            @Override
            public void onRewardedVideoAdClosed() {
                listener.onRewardedVideoAdClosed();
            }

            @Override
            public void onRewarded(RewardItem rewardItem) {
                listener.onRewarded(rewardItem);
            }

            @Override
            public void onRewardedVideoAdLeftApplication() {
                listener.onRewardedVideoAdLeftApplication();
            }

            @Override
            public void onRewardedVideoAdFailedToLoad(int i) {
                listener.onRewardedVideoAdFailedToLoad(i);
            }

            @Override
            public void onRewardedVideoCompleted() {
                listener.onRewardedVideoCompleted();
            }
        });
        rewardedVideoAd.loadAd(Ads.rewar_id_admob,new AdRequest.Builder().build());
    }
    public void rewar_fan(final com.facebook.ads.RewardedVideoAdListener listener){
        final com.facebook.ads.RewardedVideoAd rewardedVideoAd = new com.facebook.ads.RewardedVideoAd(context,"YOUR_PLACEMENT_ID");
        rewardedVideoAd.setAdListener(new com.facebook.ads.RewardedVideoAdListener() {
            @Override
            public void onRewardedVideoCompleted() {
                listener.onRewardedVideoCompleted();
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                listener.onError(ad,adError);
            }

            @Override
            public void onAdLoaded(Ad ad) {
                rewardedVideoAd.show();
                listener.onAdLoaded(ad);
            }

            @Override
            public void onAdClicked(Ad ad) {
                listener.onAdLoaded(ad);
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                listener.onLoggingImpression(ad);
            }

            @Override
            public void onRewardedVideoClosed() {
                listener.onRewardedVideoClosed();
            }
        });
        rewardedVideoAd.loadAd();
    }

    public interface FanNativeCallBack {
        void onError();
        void onSuccess();
    }
}