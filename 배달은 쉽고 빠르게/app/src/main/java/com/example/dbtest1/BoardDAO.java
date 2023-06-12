package com.example.dbtest1;



import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface BoardDAO {
    @Query("SELECT * FROM Board")
    List<Board> getAll();
    @Insert
    void insert(Board board);
    @Update
    void update(Board board);
    @Delete
    void delete(Board board);
    @Query("SELECT contents FROM Board WHERE `index`= :index")
    String getContent(Integer index);
    @Query("SELECT title FROM Board WHERE `index`= :index")
    String getTitle(Integer index);
    @Query("SELECT * FROM Board WHERE `index`=:index")
    Board getBoardbyIndex(Integer index);

}