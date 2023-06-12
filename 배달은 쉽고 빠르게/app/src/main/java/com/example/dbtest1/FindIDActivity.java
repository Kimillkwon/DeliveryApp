package com.example.dbtest1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FindIDActivity extends AppCompatActivity {
    EditText email;
    EditText name;
    Button find;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_i_d);
        email=findViewById(R.id.btnID_email);
        name=findViewById(R.id.btnID_name);
        find=findViewById(R.id.find);
        button=findViewById(R.id.button);

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail=email.getText().toString();
                String userName=name.getText().toString();

                AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"db")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build();
                String userID=db.clientDAO().findID(userEmail,userName);
                Toast.makeText(getApplicationContext(), "고객님의 ID: "+userID, Toast.LENGTH_SHORT).show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindIDActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}