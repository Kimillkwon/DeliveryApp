package com.example.dbtest1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;


public class SearchRestaurantActivity extends AppCompatActivity {


    private Context mContext= com.example.dbtest1.SearchRestaurantActivity.this;
    private ListView listView;
    private ArrayAdapter arrayAdapter;
    private EditText editText;
    private Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_restaurant);
        editText=findViewById(R.id.editText);
        button=findViewById(R.id.button);
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        listView=findViewById(R.id.listView);
        arrayAdapter=new ArrayAdapter(mContext, android.R.layout.simple_expandable_list_item_1,db.restaurantDAO().getAll());
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"db")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build();
                Restaurant restaurant=db.restaurantDAO().getRestaurantbyIndex(position+1);
                restaurant.setCount(restaurant.getCount()+1);
                db.restaurantDAO().update(restaurant);
                Intent intent=new Intent(com.example.dbtest1.SearchRestaurantActivity.this, SearchFoodActivity.class);
                intent.putExtra("index",Integer.toString(position));
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=editText.getText().toString();
                Intent intent=new Intent(com.example.dbtest1.SearchRestaurantActivity.this, SearchFoodActivity.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });


    }
}