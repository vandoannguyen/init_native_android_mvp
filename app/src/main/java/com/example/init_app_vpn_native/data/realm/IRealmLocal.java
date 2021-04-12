package com.example.init_app_vpn_native.data.realm;

import java.util.List;
import java.util.Observable;

public interface IRealmLocal {
    void insert(NoteRealm note);

    void delete(NoteRealm note);

    void update(NoteRealm note);

    List<NoteRealm> get();

    NoteRealm get(String title);
}
