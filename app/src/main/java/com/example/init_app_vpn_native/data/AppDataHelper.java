package com.example.init_app_vpn_native.data;

import android.content.Context;

import com.example.init_app_vpn_native.data.api.ApiHepler;
import com.example.init_app_vpn_native.data.local.LocalHelper;
import com.example.init_app_vpn_native.data.local.NoteModelEntity;
import com.example.init_app_vpn_native.data.share_pref.SharePreferHelper;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;


public class AppDataHelper implements IAppDataHelper {
    private static AppDataHelper dataHelper;
    private ApiHepler apiHepler;
    private LocalHelper localHelper;
    private SharePreferHelper sharePreferHelper;

    public static AppDataHelper getInstance(Context context) {
        return dataHelper != null ? dataHelper : (dataHelper = new AppDataHelper(context));
    }

    private AppDataHelper(Context context) {
        apiHepler = new ApiHepler();
        localHelper = new LocalHelper(context);
        sharePreferHelper = new SharePreferHelper();
    }

    @Override
    public Observable<Integer> getData() {
        return apiHepler.getData();
    }

    @Override
    public Observable<List<NoteModelEntity>> getNotes() {
        return localHelper.getNotes();
    }

    @Override
    public Observable<NoteModelEntity> getNote(int id) {
        return null;
    }

    @Override
    public Observable<Boolean> insertNote(NoteModelEntity... notes) {
        return localHelper.insertNote(notes);
    }

    @Override
    public Observable<Boolean> update(NoteModelEntity note) {
        return null;
    }

    @Override
    public Observable<Boolean> delete(NoteModelEntity note) {
        return null;
    }
}
