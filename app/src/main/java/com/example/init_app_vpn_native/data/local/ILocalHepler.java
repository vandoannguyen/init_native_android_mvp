package com.example.init_app_vpn_native.data.local;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;


public interface ILocalHepler {
    Observable<List<NoteModelEntity>> getNotes();

    Observable<NoteModelEntity> getNote(int id);

    Observable<Boolean> insertNote(NoteModelEntity... notes);

    Observable<Boolean> update(NoteModelEntity note);

    Observable<Boolean> delete(NoteModelEntity note);
}
