package com.example.dbtest1;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Board {
    @PrimaryKey(autoGenerate = true)
    private int index;
    private String writer;
    private String title;
    private String contents;
    private int count;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "writer name: "+writer+"\n"+
                "title: "+title;
    }
}
