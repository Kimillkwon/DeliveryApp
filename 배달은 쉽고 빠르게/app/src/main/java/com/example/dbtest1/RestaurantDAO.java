package com.example.dbtest1;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface RestaurantDAO {
    @Query("SELECT * FROM Restaurant")
    List<Restaurant> getAll();
    @Insert
    void insert(Restaurant restaurant);
    @Update
    void update(Restaurant restaurant);
    @Delete
    void delete(Restaurant restaurant);
    @Query("SELECT name FROM Restaurant WHERE `index`= :index")
    String getNamebyIndex(Integer index);
    @Query("SELECT name FROM Restaurant WHERE count=(SELECT max(count) FROM Restaurant)")
    List<String> getNamebyCount();
    @Query("SELECT * FROM Restaurant WHERE `index`=:index")
    Restaurant getRestaurantbyIndex(Integer index);

}
