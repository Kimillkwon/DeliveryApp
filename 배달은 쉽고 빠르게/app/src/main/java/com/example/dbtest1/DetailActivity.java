package com.example.dbtest1;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;



public class DetailActivity extends AppCompatActivity {

    private Context mContext= com.example.dbtest1.DetailActivity.this;
    private ArrayAdapter arrayAdapter;

    TextView titleV, contentV;
    ListView listView;
    EditText comment_et;
    Button reg_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String board_seq = getIntent().getStringExtra("board_seq");
        String userid = getIntent().getStringExtra("userid");
        String content=getIntent().getStringExtra("content");
        String title=getIntent().getStringExtra("title");


        titleV = findViewById(R.id.title);
        contentV = findViewById(R.id.content);

        listView=findViewById(R.id.listView);
        comment_et = findViewById(R.id.comment_et);
        reg_button = findViewById(R.id.reg_button);

        titleV.setText(title);
        contentV.setText(content);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        arrayAdapter=new ArrayAdapter(mContext, android.R.layout.simple_expandable_list_item_1,db.commentDAO().getContent(board_seq));
        listView.setAdapter(arrayAdapter);

        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"db")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build();
                Comment comment= new Comment();
                comment.setWriter(userid);
                comment.setBoardSeq(board_seq);
                comment.setComment(comment_et.getText().toString());
                db.commentDAO().insert(comment);

                arrayAdapter=new ArrayAdapter(mContext, android.R.layout.simple_expandable_list_item_1,db.commentDAO().getContent(board_seq));
                listView.setAdapter(arrayAdapter);

            }
        });

    }


}