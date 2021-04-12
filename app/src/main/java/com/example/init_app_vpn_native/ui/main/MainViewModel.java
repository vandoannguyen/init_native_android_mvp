package com.example.init_app_vpn_native.ui.main;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.init_app_vpn_native.base.BaseViewModel;
import com.example.init_app_vpn_native.data.AppDataHelper;
import com.example.init_app_vpn_native.data.api.model.Repo;
import com.example.init_app_vpn_native.data.api.model.User;
import com.example.init_app_vpn_native.data.local.NoteModelEntity;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainViewModel extends BaseViewModel {
    Context context;
    private int dem = 0;

    public MainViewModel(Context context) {
        this.context = context;
    }

    private static final String TAG = "MainViewModel";
    MutableLiveData<List<NoteModelEntity>> listNote;
    MutableLiveData<Boolean> isLoading;
    MutableLiveData<Integer> dems;

    public MutableLiveData<Integer> getDems() {
        return dems;
    }

    public MutableLiveData<List<NoteModelEntity>> getListNote() {
        return listNote;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    void insertNote(NoteModelEntity note) {
        Log.e(TAG, "insertNote: ");
        AppDataHelper.getInstance(context).getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<List<User>, ObservableSource<User>>() {
                    @Override
                    public Observable<User> apply(List<User> users) throws Throwable {
                        for (User u : users) {
                            Log.e(TAG, "apply: " + u.getLogin());
                            Toast.makeText(context, "okokok", Toast.LENGTH_SHORT).show();
                        }
                        return Observable.fromIterable(users);
                    }
                })
                .flatMap(new Function<User, Observable<Object>>() {
                    @Override
                    public Observable<Object> apply(User s) throws Throwable {
                        return AppDataHelper.getInstance(MainViewModel.this.context).getData(s.getLogin());
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Object o) {
                        Toast.makeText(context, "okokokokokokok", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "onNext: " + o.toString());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: " + e);
                    }

                    @Override
                    public void onComplete() {

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
        insertNote(null);
    }

    public void initData() {
        listNote = new MutableLiveData<>();
        isLoading = new MutableLiveData<>();
        dems = new MutableLiveData<>();
    }
}
