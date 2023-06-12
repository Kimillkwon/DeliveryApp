package com.example.dbtest1;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CommentDAO {
    @Query("SELECT * FROM Comment")
    List<Comment> getAll();
    @Insert
    void insert(Comment comment);
    @Update
    void update(Comment comment);
    @Delete
    void delete(Comment comment);
    @Query("SELECT * FROM Comment WHERE boardSeq= :boardSeq")
    List<Comment> getContent(String boardSeq);

}