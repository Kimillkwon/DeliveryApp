package com.example.dbtest1;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ClientDAO {
    @Query("SELECT * FROM Client")
    List<Client> getAll();
    @Insert
    void insert(Client client);
    @Update
    void update(Client client);
    @Delete
    void delete(Client client);
    @Query("SELECT pw FROM Client WHERE id= :id")
    String findPWbyID(String id);
    @Query("SELECT name FROM Client WHERE id= :id")
    String findNamebyID(String id);
    @Query("SELECT * FROM Client WHERE id= :id")
    Client getClientbyID(String id);
    @Query("SELECT id FROM Client WHERE mail= :email and name=:name")
    String findID(String email, String name);
    @Query("SELECT pw FROM Client WHERE id= :id and mail= :email and name=:name")
    String findPW(String id, String email, String name);

}
