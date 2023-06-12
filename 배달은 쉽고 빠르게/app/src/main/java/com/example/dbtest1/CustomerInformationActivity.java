package com.example.dbtest1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class CustomerInformationActivity extends AppCompatActivity {

    private Context mContext= com.example.dbtest1.CustomerInformationActivity.this;
    private ListView listView;
    private ArrayAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_information);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        listView=findViewById(R.id.listView);
        arrayAdapter=new ArrayAdapter(mContext, android.R.layout.simple_expandable_list_item_1,db.clientDAO().getAll());
        listView.setAdapter(arrayAdapter);

    }
}