package com.example.init_app_vpn_native.data.realm;

import java.util.List;
import java.util.Observable;

import io.reactivex.rxjava3.core.Single;

public interface IRealmLocal {
    Single<Boolean> realmInsert(NoteRealm note);

    Single<Void> realmDelete(NoteRealm note);

    Single<Void> realmUpdate(NoteRealm note);

    Single<List<NoteRealm>> realmGet();

    Single<NoteRealm> realmGet(String title);
}
