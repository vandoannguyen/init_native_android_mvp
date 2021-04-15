package com.example.init_app_vpn_native.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.init_app_vpn_native.R;
import com.example.init_app_vpn_native.base.BaseAdapterRecyclerView;
import com.example.init_app_vpn_native.data.api.model.User;
import com.example.init_app_vpn_native.data.local.NoteModelEntity;
import com.example.init_app_vpn_native.data.realm.NoteRealm;

import java.util.List;

public class NoteMainAdapter extends BaseAdapterRecyclerView<NoteMainAdapter.ViewHolder> {

    public NoteMainAdapter(List list) {
        super(list);
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false));
    }

    @Override
    public void onBindViewHolder(int position, ViewHolder holder) {
        holder.txtText.setText(((User) list.get(position)).getId().toString());
        holder.txtTitle.setText(((User) list.get(position)).getName() != null ? ((User) list.get(position)).getName() : "Loading....");
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        TextView txtText;

        public ViewHolder(@NonNull View view) {
            super(view);
            txtText = view.findViewById(R.id.txtContent);
            txtTitle = view.findViewById(R.id.txtTitle);
        }
    }
}
