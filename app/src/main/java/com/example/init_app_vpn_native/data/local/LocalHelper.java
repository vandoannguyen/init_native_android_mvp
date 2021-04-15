package com.example.init_app_vpn_native.data.local;

import android.content.Context;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class LocalHelper implements ILocalHepler {
    NoteDAO dao;

    public LocalHelper(Context context) {
        dao = LocalDatabase.getDatabase(context).getDAO();
    }

    @Override
    public Observable<List<NoteModelEntity>> getNotes() {
        return Observable.create(emitter -> {
            emitter.onNext(dao.getAllNote());
            emitter.onComplete();
        });
    }

    @Override
    public Observable<NoteModelEntity> getNote(int id) {
//        return dao.getNote(id);
        return null;
    }

    @Override
    public Observable<Boolean> insertNote(NoteModelEntity... notes) {
        return Observable.create(emitter ->
        {
            dao.insertAll(notes);
            emitter.onNext(true);
            emitter.onComplete();
        });
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
