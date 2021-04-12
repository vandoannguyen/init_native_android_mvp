package com.example.init_app_vpn_native.data.realm;

import android.content.Context;

import java.util.List;

import io.realm.Realm;

public class RealmLocalImpl implements IRealmLocal {
    Realm realm;

    public RealmLocalImpl(Context context) {
        realm = Realm
                .getInstance(context);
    }

    @Override
    public void insert(NoteRealm note) {
        realm.beginTransaction();
        NoteRealm notes = realm.createObject(NoteRealm.class);
        notes.setNote(note.getNote());
        notes.setTitle(note.getTitle());
        realm.commitTransaction();
    }

    @Override
    public void delete(NoteRealm note) {

    }

    @Override
    public void update(NoteRealm note) {

    }

    @Override
    public List<NoteRealm> get() {
        return null;
    }

    @Override
    public NoteRealm get(String title) {
        return null;
    }
}
