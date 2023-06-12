package com.example.dbtest1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


public class RecommendActivity extends AppCompatActivity {
    private Context mContext= com.example.dbtest1.RecommendActivity.this;
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);

        Button btn_recommend=findViewById(R.id.btn_recommend);
        Button button=findViewById(R.id.button);

        ListView listView =findViewById(R.id.listView);
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        arrayAdapter=new ArrayAdapter(mContext, android.R.layout.simple_expandable_list_item_1,db.restaurantDAO().getAll());
        listView.setAdapter(arrayAdapter);


        btn_recommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(com.example.dbtest1.RecommendActivity.this, SubRecommendActivity.class);
                startActivity(intent);

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(com.example.dbtest1.RecommendActivity.this, CustomerMainActivity.class);
                startActivity(intent);

            }
        });



    }
}