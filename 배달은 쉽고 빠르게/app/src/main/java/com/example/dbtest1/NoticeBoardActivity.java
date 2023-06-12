package com.example.dbtest1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;

public class NoticeBoardActivity extends AppCompatActivity {
    private Context mContext= com.example.dbtest1.NoticeBoardActivity.this;
    private ArrayAdapter arrayAdapter;

    ListView listView;
    Button reg_button;
    Button button;
    String userid = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_board);

        userid = LoginActivity.userID;

        listView = findViewById(R.id.listView);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        arrayAdapter=new ArrayAdapter(mContext, android.R.layout.simple_expandable_list_item_1,db.boardDAO().getAll());
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(NoticeBoardActivity.this, adapterView.getItemAtPosition(i)+ " 클릭", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(NoticeBoardActivity.this, DetailActivity.class);
                intent.putExtra("board_seq", Integer.toString(i+1));
                intent.putExtra("userid", userid);
                List<Board> listBoard=db.boardDAO().getAll();
                listBoard.get(i).getTitle();
                intent.putExtra("title", listBoard.get(i).getTitle());
                intent.putExtra("content", listBoard.get(i).getContents());
                startActivity(intent);

            }
        });

        reg_button = findViewById(R.id.reg_button);

        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(NoticeBoardActivity.this, PostNoticeActivity.class);
                intent.putExtra("userid", userid);
                startActivity(intent);
            }
        });

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NoticeBoardActivity.this, CustomerMainActivity.class);
                startActivity(intent);
            }
        });
    }

}