package com.example.dbtest1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class PostNoticeActivity extends AppCompatActivity {


    EditText title_et, content_et;
    Button reg_button;

    String userid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_notice);

        userid = LoginActivity.userID;

        title_et = findViewById(R.id.title_et);
        content_et = findViewById(R.id.content_et);
        reg_button = findViewById(R.id.reg_button);

        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"db")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build();
                Board board=new Board();
                board.setWriter(userid);
                board.setContents(content_et.getText().toString());
                board.setTitle(title_et.getText().toString());
                db.boardDAO().insert(board);
                Intent intent=new Intent(com.example.dbtest1.PostNoticeActivity.this, NoticeBoardActivity.class);
                startActivity(intent);
            }
        });

    }
}