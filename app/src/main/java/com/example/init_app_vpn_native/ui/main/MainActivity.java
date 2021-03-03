package com.example.init_app_vpn_native.ui.main;


import android.os.Bundle;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.init_app_vpn_native.R;
import com.example.init_app_vpn_native.base.BaseActivity;
import com.example.init_app_vpn_native.data.AppDataHelper;
import com.example.init_app_vpn_native.data.local.NoteModelEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends BaseActivity implements IMainActivity {
    MainPresenter<IMainActivity> presenter;
    List<NoteModelEntity> notes;
    NoteMainAdapter adapter;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.btnAdd)
    Button btnAdd;
    int dem = 0;
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainPresenter<>(this);
        presenter.onAttact(this);
        initRecyclerView();
        presenter.getListNote();
    }

    private void initRecyclerView() {
        notes = new ArrayList<>();
        adapter = new NoteMainAdapter(notes);
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
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

    @OnClick(R.id.btnAdd)
    public void onViewClicked() {
        String title = "demo";
        String content = "demo content";
        AppDataHelper.getInstance(MainActivity.this)
                .insertNote(new NoteModelEntity(title + dem, content + dem, "", ""))
                .flatMap(result -> AppDataHelper.getInstance(MainActivity.this).getNotes())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<NoteModelEntity>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<NoteModelEntity> noteModelEntities) {
                        notes = noteModelEntities;
                        adapter.setList(notes);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}