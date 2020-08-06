package com.gloriakim.devoted;

public class ListViewDataModel {
    private String note;

    public ListViewDataModel(String date, String note) {
        this.note = note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }
}
