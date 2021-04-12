package com.example.init_app_vpn_native.ui.main;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.example.init_app_vpn_native.base.BaseViewModel;
import com.example.init_app_vpn_native.data.AppDataHelper;
import com.example.init_app_vpn_native.data.api.model.Repo;
import com.example.init_app_vpn_native.data.local.NoteModelEntity;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
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
        AppDataHelper.getInstance(context).getData("vandoannguyen")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Repo>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<Repo> repos) {
                        Log.e(TAG, "onNext: " + repos.size() );
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        dem++;
        AppDataHelper.getInstance(context)
                .insertNote(new NoteModelEntity("title" + dem, "content" + dem, "", ""))
                .flatMap(result -> AppDataHelper.getInstance(context).getNotes())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<NoteModelEntity>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<NoteModelEntity> noteModelEntities) {
                        listNote.postValue(noteModelEntities);
                        dems.postValue(dem);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

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
