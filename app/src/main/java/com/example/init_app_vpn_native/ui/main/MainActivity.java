package com.example.init_app_vpn_native.ui.main;


import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.init_app_vpn_native.R;
import com.example.init_app_vpn_native.base.BaseActivity;
import com.example.init_app_vpn_native.base.BaseViewModelFactory;
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
    }

    private void initRecyclerView() {
        adapter = new NoteMainAdapter(viewModel.getListNote().getValue() == null
                ? new ArrayList()
                : viewModel.getListNote().getValue());
        adapter.setItemClickListener((position -> {
            Toast.makeText(this, "" + position, Toast.LENGTH_SHORT).show();
        }));
        binding.recyclerview.setAdapter(adapter);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public MainViewModel createViewModel() {
        BaseViewModelFactory<MainViewModel> factory;
        factory = new BaseViewModelFactory(new MainViewModel(this));
        return new ViewModelProvider(this, factory).get(MainViewModel.class);
    }

    @Override
    public int setContentView() {
        return R.layout.activity_main;
    }
}