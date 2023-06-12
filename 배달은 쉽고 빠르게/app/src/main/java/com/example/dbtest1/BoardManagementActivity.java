package com.example.dbtest1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class BoardManagementActivity extends AppCompatActivity {


    private Context mContext= com.example.dbtest1.BoardManagementActivity.this;
    private ListView listView;
    private ArrayAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_management);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        listView=findViewById(R.id.listView);
        arrayAdapter=new ArrayAdapter(mContext, android.R.layout.simple_expandable_list_item_1,db.boardDAO().getAll());
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"db")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build();
                //Board board=db.boardDAO().getBoardbyIndex(position+1);
                List<Board>listBoard=db.boardDAO().getAll();
                db.boardDAO().delete(listBoard.get(position));
                Toast.makeText(getApplicationContext(), "클릭한 게시글 삭제 완료!", Toast.LENGTH_SHORT).show();
                arrayAdapter=new ArrayAdapter(mContext, android.R.layout.simple_expandable_list_item_1,db.boardDAO().getAll());
                listView.setAdapter(arrayAdapter);
            }
        });


    }
}