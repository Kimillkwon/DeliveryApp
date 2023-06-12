package com.example.dbtest1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FindPWActivity extends AppCompatActivity {

    EditText id;
    EditText email;
    EditText name;
    Button find;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_p_w);
        id=findViewById(R.id.btn_id);
        email=findViewById(R.id.btn_email);
        name=findViewById(R.id.btn_name);
        find=findViewById(R.id.find);
        button=findViewById(R.id.button);


        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID=id.getText().toString();
                String userEmail=email.getText().toString();
                String userName=name.getText().toString();

                AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"db")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build();
                String userPW=db.clientDAO().findPW(userID,userEmail,userName);
                Toast.makeText(getApplicationContext(), "고객님의 PW: "+userPW, Toast.LENGTH_SHORT).show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindPWActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}