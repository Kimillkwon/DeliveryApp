package com.example.dbtest1;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Client.class,Board.class,Restaurant.class,Food.class,Comment.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ClientDAO clientDAO();
    public abstract BoardDAO boardDAO();
    public abstract RestaurantDAO restaurantDAO();
    public abstract FoodDAO foodDAO();
    public abstract CommentDAO commentDAO();
}
