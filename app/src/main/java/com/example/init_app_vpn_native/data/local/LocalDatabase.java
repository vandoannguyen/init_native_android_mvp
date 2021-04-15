package com.example.init_app_vpn_native.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {NoteModelEntity.class}, version = 1, exportSchema = true)
public abstract class LocalDatabase extends RoomDatabase {
    private static LocalDatabase database;

    abstract NoteDAO getDAO();

    public static LocalDatabase getDatabase(Context context) {
        if (database != null) return database;
        database = Room.databaseBuilder(context, LocalDatabase.class, "NoteDatabase")
                .fallbackToDestructiveMigration()
                .build();
        return database;
    }
}
