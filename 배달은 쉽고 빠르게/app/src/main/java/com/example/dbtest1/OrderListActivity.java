package com.example.dbtest1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class OrderListActivity extends AppCompatActivity {

    private Context mContext= com.example.dbtest1.OrderListActivity.this;
    private ListView listView;
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        listView=findViewById(R.id.listView);
        arrayAdapter=new ArrayAdapter(mContext, android.R.layout.simple_expandable_list_item_1,SearchFoodActivity.cart);
        listView.setAdapter(arrayAdapter);
    }
}