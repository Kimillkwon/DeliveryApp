package com.example.dbtest1;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface FoodDAO {

    @Query("SELECT * FROM Food")
    List<Food> getAll();
    @Insert
    void insert(Food food);
    @Update
    void update(Food food);
    @Delete
    void delete(Food food);
    @Query("SELECT * FROM Food WHERE restaurantName= :restaurantName")
    List<Food> getFoodbyrestaurant(String restaurantName);
    @Query("SELECT * FROM Food WHERE restaurantName= :restaurantName")
    List<Food> getFood(String restaurantName);
    @Query("SELECT r.name FROM Food f, Restaurant r WHERE f.restaurantName=r.name and r.foodType=:foodType and f.price <= :price")
    List<String> getRestaurantNamebyPrice(String foodType, Integer price);
}
