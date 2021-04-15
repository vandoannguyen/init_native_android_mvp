package com.example.init_app_vpn_native.data.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
@Dao
public interface NoteDAO {
    static String NOTE_TABLE_NAME = "note";

    @Query("SELECT * FROM " + NOTE_TABLE_NAME)
    List<NoteModelEntity>getAllNote();

    @Query("SELECT * FROM " + NOTE_TABLE_NAME + " WHERE id LIKE (:id)")
    NoteModelEntity getNote(int id);

    @Insert
    void insertAll(NoteModelEntity... notes);

    @Delete
    void delete(NoteModelEntity note);
}
