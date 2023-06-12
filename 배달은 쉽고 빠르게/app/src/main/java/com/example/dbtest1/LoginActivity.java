package com.example.dbtest1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class LoginActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_MAIN=100;
    public static final int REQUEST_CODE_SIGNUP=101;
    static String userName;
    static String userID;
    static Admin manager=new Admin("manager","1111");
    Button findID;
    Button findPW;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button buttonSignup=findViewById(R.id.button2);
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),SignupActivity.class);
                startActivityForResult(intent,REQUEST_CODE_SIGNUP);

            }
        });

        findID=findViewById(R.id.findID);
        findID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, FindIDActivity.class);
                startActivity(intent);
            }
        });
        findPW=findViewById(R.id.findPW);
        findPW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, FindPWActivity.class);
                startActivity(intent);
            }
        });

    }


    public void login(View v){
        EditText id=findViewById(R.id.editText);
        EditText pw=findViewById(R.id.editText2);

        String userid=id.getText().toString();
        String userpw=pw.getText().toString();

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        if(userpw.equals(db.clientDAO().findPWbyID(userid))){
            userID=userid;
            Intent intent = new Intent(getApplicationContext(), com.example.dbtest1.CustomerMainActivity.class);
            intent.putExtra("userid", userid);
            Toast.makeText(this, db.clientDAO().findNamebyID(userid)+"님 안녕하세요.", Toast.LENGTH_SHORT).show();
            startActivityForResult(intent, REQUEST_CODE_MAIN);
        }
        else if(userid.equals(manager.getId())){
            if(userpw.equals(manager.getPw())){
                userName = manager.getId();
                Intent intent=new Intent(getApplicationContext(), com.example.dbtest1.ManagerMainActivity.class);
                Toast.makeText(this,"manager님 안녕하세요.",Toast.LENGTH_SHORT).show();
                startActivityForResult(intent,REQUEST_CODE_MAIN);
            }
            else{
                Toast.makeText(getApplicationContext(), "아이디 혹은 비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "아이디 혹은 비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
        }
    }
}