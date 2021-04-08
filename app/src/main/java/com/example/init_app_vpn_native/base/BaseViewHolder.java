package com.example.init_app_vpn_native.base;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

public abstract class BaseViewHolder<BD extends ViewBinding> extends RecyclerView.ViewHolder {
    public BD binding;

    public BaseViewHolder(@NonNull BD binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
