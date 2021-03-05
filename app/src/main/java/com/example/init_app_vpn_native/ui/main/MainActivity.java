package com.example.init_app_vpn_native.ui.main;


import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.init_app_vpn_native.R;
import com.example.init_app_vpn_native.base.BaseActivity;
import com.example.init_app_vpn_native.data.local.NoteModelEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainViewModel> {
    List<NoteModelEntity> notes;
    NoteMainAdapter adapter;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.btnAdd)
    Button btnAdd;
    int dem = 0;
    @BindView(R.id.txtDem)
    TextView txtDem;
    private String TAG = "MainActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.e(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initRecyclerView();
        initData();
    }

    private void initData() {
        viewModel.initData();
        viewModel.getListNote().observe(this, new Observer<List<NoteModelEntity>>() {
            @Override
            public void onChanged(List<NoteModelEntity> noteModelEntities) {
                if (adapter != null) adapter.setList(noteModelEntities);
            }
        });
        viewModel.getDems().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                txtDem.setText(integer + "");
            }
        });
    }

    private void initRecyclerView() {
        notes = new ArrayList<>();
        adapter = new NoteMainAdapter(notes);
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
    }

    @OnClick(R.id.btnAdd)
    public void onViewClicked() {
        viewModel.insertNote(null);
    }

    @Override
    public MainViewModel createViewModel() {
        Log.e(TAG, "createViewModel: ");
        MainViewModelFactory factory = new MainViewModelFactory(this);
        return new ViewModelProvider(this, factory).get(MainViewModel.class);
    }
}