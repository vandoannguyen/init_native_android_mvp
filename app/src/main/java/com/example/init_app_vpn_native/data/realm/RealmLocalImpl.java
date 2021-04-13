package com.example.init_app_vpn_native.data.realm;

import android.content.Context;

import java.util.List;

import io.reactivex.rxjava3.core.Emitter;
import io.reactivex.rxjava3.core.Single;
import io.realm.Realm;
import io.realm.RealmResults;

public class RealmLocalImpl implements IRealmLocal {
    Realm realm;

    public RealmLocalImpl(Context context) {
    }

    @Override
    public Single<Boolean> realmInsert(NoteRealm note) {
        return Single.create(emitter -> {
            realm = Realm
                    .getDefaultInstance();
            if (!realm.isInTransaction()) {
                realm.beginTransaction();
                realm.copyToRealm(note);
                realm.commitTransaction();
                emitter.onSuccess(true);
            }
            realm.close();
        });
    }

    @Override
    public Single<Void> realmDelete(NoteRealm note) {
        return Single.create((emitter -> {
            realm = Realm
                    .getDefaultInstance();
            NoteRealm n = realm.where(NoteRealm.class).equalTo("title", note.getTitle()).findFirst();
            if (n != null) {
                n.deleteFromRealm();
            }
            emitter.onSuccess(null);
            realm.close();
        }));
    }

    @Override
    public Single<Void> realmUpdate(NoteRealm note) {
        return Single.create(emitter -> {
            realm = Realm
                    .getDefaultInstance();
            NoteRealm n = realm.where(NoteRealm.class).equalTo("title", note.getTitle()).findFirst();
            if (n != null) {
                n.setTitle(note.getTitle());
                n.setNote(note.getNote());
            }
            emitter.onSuccess(null);
            realm.close();
        });
    }

    @Override
    public Single<List<NoteRealm>> realmGet() {
        return Single.create(emitter -> {
            realm = Realm
                    .getDefaultInstance();
            realm.beginTransaction();
            RealmResults<NoteRealm> notes = realm.where(NoteRealm.class).findAll();
            List<NoteRealm> list = realm.copyToRealm(notes);
            realm.commitTransaction();
            emitter.onSuccess(list);
            realm.close();
        });
    }

    @Override
    public Single<NoteRealm> realmGet(String title) {
        return Single.create(emitter -> {
            realm = Realm
                    .getDefaultInstance();
            NoteRealm note = realm.where(NoteRealm.class).equalTo("title", title).findFirst();
            if (note != null) {
                emitter.onSuccess(note);
            } else
                emitter.onSuccess(null);
            realm.close();
        });
    }

//    @Override
//    public void insert(NoteRealm note) {
//        realm.beginTransaction();
//        NoteRealm notes = realm.createObject(NoteRealm.class);
//        notes.setNote(note.getNote());
//        notes.setTitle(note.getTitle());
//        realm.commitTransaction();
//    }
//
//    @Override
//    public void delete(NoteRealm note) {
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                NoteRealm not = realm.where(NoteRealm.class).equalTo("title", note.getTitle()).findFirst();
//                if (not != null) {
//                    not.removeFromRealm();
//                }
//            }
//        });
//    }
//
//    @Override
//    public void update(NoteRealm note) {
//        realm.executeTransaction(realm -> {
//            NoteRealm n = realm.where(NoteRealm.class).equalTo("title", note.getTitle()).findFirst();
//            n.setTitle(note.getTitle());
//            n.setNote(note.getNote());
//        });
//    }
//
//    @Override
//    public List<NoteRealm> get() {
//        return ;
//    }
//
//    @Override
//    public NoteRealm get(String title) {
//        return null;
//    }
}
