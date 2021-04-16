package com.example.init_app_vpn_native.ui.main;

import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.lifecycle.MutableLiveData;

import com.example.init_app_vpn_native.R;
import com.example.init_app_vpn_native.base.BaseViewModel;
import com.example.init_app_vpn_native.data.AppDataHelper;
import com.example.init_app_vpn_native.data.IAppDataHelper;
import com.example.init_app_vpn_native.data.api.model.User;
import com.example.init_app_vpn_native.data.local.NoteModelEntity;
import com.example.init_app_vpn_native.data.realm.NoteRealm;
import com.example.init_app_vpn_native.utils.noification.NotificationUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import dagger.Provides;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.realm.Realm;

public class MainViewModel extends BaseViewModel {
    Context context;
    private int dem = 0;
    private IAppDataHelper appDataHelper;

    @Inject
    public MainViewModel(IAppDataHelper appDataHelper) {
        this.appDataHelper = appDataHelper;
    }


    public MainViewModel(Context context) {
        this.context = context;
    }

    private static final String TAG = "MainViewModel";
    MutableLiveData<List<User>> listNote;
    MutableLiveData<Boolean> isLoading;
    MutableLiveData<Integer> dems;

    public MutableLiveData<Integer> getDems() {
        return dems;
    }

    public MutableLiveData<List<User>> getListNote() {
        return listNote;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    void insertNote(NoteModelEntity note) {
        List<User> list = new ArrayList<>();
        list.add(new User(1, "1"));
        list.add(new User(2, "2"));
        list.add(new User(3, "3"));
        list.add(new User(4, "4"));
        listNote.setValue(list);
        listNote.postValue(list);
        Log.e(TAG, "insertNote: ");
//        AppDataHelper.getInstance(context).getUsers()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .flatMap(new Function<List<User>, ObservableSource<User>>() {
//                    @Override
//                    public Observable<User> apply(List<User> users) throws Throwable {
//                        listNote.setValue(users);
//                        listNote.postValue(users);
//                        return Observable.fromIterable(users);
//                    }
//                })
//                .flatMap(new Function<User, Observable<User>>() {
//                    @Override
//                    public Observable<User> apply(User s) throws Throwable {
//                        return AppDataHelper.getInstance(MainViewModel.this.context).getData(s.getLogin());
//                    }
//                }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<User>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(@NonNull User o) {
//                        List<User> users = listNote.getValue();
//                        int index = users.indexOf(o);
//                        if (index != -1) {
//                            users.set(index, o);
//                        }
//                        listNote.setValue(users);
//                        listNote.postValue(users);
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        Log.e(TAG, "onError: " + e);
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }

    void insertNote(NoteRealm noteRealm) {
        AppDataHelper.getInstance(context)
                .realmInsert(new NoteRealm("demo title" + Calendar.getInstance().getTimeInMillis(), "demo content"))
                .flatMap(aVoid -> AppDataHelper.getInstance(MainViewModel.this.context).realmGet())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<NoteRealm>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onSuccess(@NonNull List<NoteRealm> noteRealms) {
                        Realm.getDefaultInstance().beginTransaction();
//                        listNote.postValue(noteRealms);
                        Realm.getDefaultInstance().commitTransaction();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: " + e);
                    }
                });
    }

    @Override
    public void initViewModelData() {
        super.initViewModelData();
        listNote = new MutableLiveData<>();
        dems = new MutableLiveData<>();
        isLoading = new MutableLiveData<>();
    }

    public void onClickAdd(View v) {
//        Bitmap contact_pic = BitmapFactory.decodeResource(
//                v.getContext().getResources(),
//                R.mipmap.ic_launcher
//        );
//        NotificationUtil.showNotification(context,
//                "demo title" + Calendar.getInstance().getTimeInMillis(),
//                "demo content", NotificationCompat.PRIORITY_DEFAULT, contact_pic, MainActivity.class);
        insertNote(new NoteModelEntity());
    }

    public void initData() {
        listNote = new MutableLiveData<>();
        isLoading = new MutableLiveData<>();
        dems = new MutableLiveData<>();
    }
}
