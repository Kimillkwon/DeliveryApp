package com.example.dbtest1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //데이터베이스 초기화
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        RestaurantDAO mRestaurantDAO = db.restaurantDAO();
        Restaurant restaurant = new Restaurant();
        restaurant.setName("라면집");
        restaurant.setFoodType("일식");
        mRestaurantDAO.insert(restaurant);

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setName("한식집");
        restaurant2.setFoodType("한식");
        mRestaurantDAO.insert(restaurant2);

        Restaurant restaurant3 = new Restaurant();
        restaurant3.setName("중국집");
        restaurant3.setFoodType("중식");
        mRestaurantDAO.insert(restaurant3);

        FoodDAO mFoodDAO = db.foodDAO();
        Food food = new Food();
        food.setFoodName("라면");
        food.setRestaurantName("라면집");
        food.setPrice(8000);
        mFoodDAO.insert(food);

        Food food2 = new Food();
        food2.setFoodName("떡국");
        food2.setRestaurantName("한식집");
        food2.setPrice(6000);
        mFoodDAO.insert(food2);

        Food food3 = new Food();
        food3.setFoodName("짜장면");
        food3.setRestaurantName("중국집");
        food3.setPrice(10000);
        mFoodDAO.insert(food3);



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //로딩화면 시작.
        Loadingstart();
    }
    private void Loadingstart(){
        Handler handler=new Handler();
        handler.postDelayed(new Runnable(){
            public void run(){
                Intent intent=new Intent(getApplicationContext(), com.example.dbtest1.LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
}