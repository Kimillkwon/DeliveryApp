package com.example.dbtest1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ManageCustomerActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_write=101;
    private static final String TAG = "ManageCustomer_Activity";
    private Context mContext= com.example.dbtest1.ManageCustomerActivity.this;
    private ListView listView;
    private ArrayAdapter arrayAdapter;
    static ArrayList<String> customers = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_customer);
        listView=findViewById(R.id.listView);
        arrayAdapter=new ArrayAdapter(mContext, android.R.layout.simple_expandable_list_item_1,customers);
        listView.setAdapter(arrayAdapter);

        Button button1=findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), com.example.dbtest1.ManagerMainActivity.class);
                startActivityForResult(intent,REQUEST_CODE_write);

            }
        });

    }
}