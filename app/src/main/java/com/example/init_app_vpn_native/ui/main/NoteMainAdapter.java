package com.example.init_app_vpn_native.ui.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.example.init_app_vpn_native.R;
import com.example.init_app_vpn_native.base.BaseViewHolder;
import com.example.init_app_vpn_native.data.local.NoteModelEntity;
import com.example.init_app_vpn_native.databinding.ItemListBinding;

import java.util.List;

public class NoteMainAdapter extends RecyclerView.Adapter<NoteMainAdapter.ViewHolder> {
    List list;

    public NoteMainAdapter(List list) {
        this.list = list;
    }

    public void setList(List list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setNote((NoteModelEntity) list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends BaseViewHolder<ItemListBinding> {
        public ViewHolder(@NonNull ViewBinding binding) {
            super((ItemListBinding) binding);
        }
    }
}
