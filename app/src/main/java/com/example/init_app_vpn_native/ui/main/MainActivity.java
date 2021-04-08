package com.example.init_app_vpn_native.ui.main;


import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.init_app_vpn_native.R;
import com.example.init_app_vpn_native.base.BaseActivity;
import com.example.init_app_vpn_native.databinding.ActivityMainBinding;

import java.util.ArrayList;

import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainViewModel, ActivityMainBinding> {
    private static final String TAG = "MainActivity";
    private NoteMainAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: ");
        initRecyclerView();
        initData();
    }

    private void initData() {
        viewModel.initData();
        viewModel.getListNote().observe(this, (noteModelEntities) -> {
            if (adapter != null) adapter.setList(noteModelEntities);
        });
//        viewModel.getDems().observe(this, new Observer<Integer>() {
//            @Override
//            public void onChanged(Integer integer) {
//                txtDem.setText(integer + "");
//            }
//        });
    }

    private void initRecyclerView() {
        adapter = new NoteMainAdapter(viewModel.getListNote().getValue() == null ? new ArrayList() : viewModel.getListNote().getValue());
        binding.recyclerview.setAdapter(adapter);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
    }

//    @OnClick(R.id.btnAdd)
//    public void onViewClicked() {
//        viewModel.insertNote(null);
//    }

    @Override
    public MainViewModel createViewModel() {
        Log.e(TAG, "createViewModel: ");
        MainViewModelFactory factory = new MainViewModelFactory(this);
        return new ViewModelProvider(this, factory).get(MainViewModel.class);
    }

    @Override
    public int setContentView() {
        return R.layout.activity_main;
    }
}