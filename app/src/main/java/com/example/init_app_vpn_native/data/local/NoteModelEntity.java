package com.example.init_app_vpn_native.data.local;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "note")
public class NoteModelEntity {
    public NoteModelEntity(String title, String note, String createAt, String updateeAt) {
        this.title = title;
        this.note = note;
        this.createAt = createAt;
        this.updateeAt = updateeAt;
    }
    public NoteModelEntity() {
    }

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo
    public String title;
    @ColumnInfo
    public String note;
    @ColumnInfo
    public String createAt;
    @ColumnInfo
    public String updateeAt;

}
