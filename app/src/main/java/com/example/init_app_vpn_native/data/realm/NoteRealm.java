package com.example.init_app_vpn_native.data.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class NoteRealm extends RealmObject {
    @PrimaryKey
    private String title;

    private String note;

    public NoteRealm() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
