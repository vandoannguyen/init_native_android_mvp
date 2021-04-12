package com.example.init_app_vpn_native.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.init_app_vpn_native.R;
import com.example.init_app_vpn_native.data.local.NoteModelEntity;

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
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtText.setText(((NoteModelEntity) list.get(position)).title);
        holder.txtTitle.setText(((NoteModelEntity) list.get(position)).note);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        TextView txtText;

        public ViewHolder(@NonNull View view) {
            super(view);
            txtText = view.findViewById(R.id.txtTitle);
            txtTitle = view.findViewById(R.id.txtTitle);
        }
    }
}
