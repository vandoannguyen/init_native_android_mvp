package com.example.init_app_vpn_native.data;

import android.content.Context;

import com.example.init_app_vpn_native.data.api.ApiHepler;
import com.example.init_app_vpn_native.data.api.IApiHelper;
import com.example.init_app_vpn_native.data.api.model.User;
import com.example.init_app_vpn_native.data.local.ILocalHepler;
import com.example.init_app_vpn_native.data.local.LocalHelper;
import com.example.init_app_vpn_native.data.local.NoteModelEntity;
import com.example.init_app_vpn_native.data.realm.IRealmLocal;
import com.example.init_app_vpn_native.data.realm.NoteRealm;
import com.example.init_app_vpn_native.data.realm.RealmLocalImpl;
import com.example.init_app_vpn_native.data.share_pref.ISharePreferHelper;
import com.example.init_app_vpn_native.data.share_pref.SharePreferHelper;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;


public class AppDataHelper implements IAppDataHelper {
    private static AppDataHelper dataHelper;
    private IApiHelper apiHepler;
    private ILocalHepler localHelper;
    private ISharePreferHelper sharePreferHelper;
    private IRealmLocal realmLocal;

    public static AppDataHelper getInstance(Context context) {
        return dataHelper != null ? dataHelper : (dataHelper = new AppDataHelper(context));
    }

    public AppDataHelper(IApiHelper apiHepler, ILocalHepler localHelper, ISharePreferHelper sharePreferHelper, IRealmLocal realmLocal) {
        this.apiHepler = apiHepler;
        this.localHelper = localHelper;
        this.sharePreferHelper = sharePreferHelper;
        this.realmLocal = realmLocal;
    }

    private AppDataHelper(Context context) {
        apiHepler = new ApiHepler();
        localHelper = new LocalHelper(context);
        sharePreferHelper = new SharePreferHelper();
        realmLocal = new RealmLocalImpl(context);
    }

    @Override
    public Observable<User> getData(String user) {
        return apiHepler.getData(user);
    }

    @Override
    public Observable<List<User>> getUsers() {
        return apiHepler.getUsers();
    }

    @Override
    public Observable<List<NoteModelEntity>> getNotes() {
        return localHelper.getNotes();
    }

    @Override
    public Observable<NoteModelEntity> getNote(int id) {
        return localHelper.getNote(id);
    }

    @Override
    public Observable<Boolean> insertNote(NoteModelEntity... notes) {
        return localHelper.insertNote(notes);
    }

    @Override
    public Observable<Boolean> update(NoteModelEntity note) {
        return localHelper.update(note);
    }

    @Override
    public Observable<Boolean> delete(NoteModelEntity note) {
        return localHelper.delete(note);
    }

    @Override
    public Single<Boolean> realmInsert(NoteRealm note) {
        return realmLocal.realmInsert(note);
    }

    @Override
    public Single<Void> realmDelete(NoteRealm note) {
        return realmLocal.realmDelete(note);
    }

    @Override
    public Single<Void> realmUpdate(NoteRealm note) {
        return realmLocal.realmUpdate(note);
    }

    @Override
    public Single<List<NoteRealm>> realmGet() {
        return realmLocal.realmGet();
    }

    @Override
    public Single<NoteRealm> realmGet(String title) {
        return realmLocal.realmGet(title);
    }
}
