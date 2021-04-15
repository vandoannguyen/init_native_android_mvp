package com.example.init_app_vpn_native.base;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BaseAdapterRecyclerView<VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {
    protected List list;
    protected AdapterClickListener itemClickListener;

    public void setItemClickListener(AdapterClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public BaseAdapterRecyclerView(List list) {
        this.list = list;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.itemView.setOnClickListener((v) -> {
            onClick(position);
        });
        onBindViewHolder(position, holder);
    }

    public abstract void onBindViewHolder(int position, VH holder);

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public void setList(List list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setList(List list, int index) {
        this.list = list;
        notifyItemChanged(index);
    }

    public List getList() {
        return list;
    }

    public void onClick(int position) {
        if (itemClickListener != null) itemClickListener.onClick(position);
    }

    public interface AdapterClickListener {
        void onClick(int position);
    }
}
