package com.example.init_app_vpn_native.base;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BaseAdapterRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List list;

    public BaseAdapterRecyclerView(List list) {
        this.list = list;
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public void setList(List list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public List getList() {
        return list;
    }

    public abstract void onClick(int position);
}
