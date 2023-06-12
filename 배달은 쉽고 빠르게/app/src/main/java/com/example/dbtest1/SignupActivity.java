package com.example.dbtest1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import java.util.ArrayList;
import java.util.List;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        EditText id=findViewById(R.id.signup_id);
        EditText pw=findViewById(R.id.signup_pw);
        EditText name=findViewById(R.id.signup_name);
        EditText email=findViewById(R.id.signup_email);
        EditText accountNum=findViewById(R.id.signup_account);
        EditText address=findViewById(R.id.signup_address);


        Button buttonCancle=findViewById(R.id.button6);
        buttonCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        Button buttonSignup=findViewById(R.id.button7);
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userid=id.getText().toString();
                final String userpw=pw.getText().toString();
                final String useremail=email.getText().toString();
                final String username=name.getText().toString();
                final String useraccount=accountNum.getText().toString();
                final String useraddress=address.getText().toString();

                AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"db")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build();
                List<Client> listClient=db.clientDAO().getAll();

                List<String> listID=new ArrayList<>();
                for(int i=0;i<listClient.size();i++){
                    listID.add(listClient.get(i).getId());
                }
                if(listID.contains(userid)==true){
                    AlertDialog.Builder dialog = new AlertDialog.Builder(com.example.dbtest1.SignupActivity.this);
                    dialog.setIcon(R.mipmap.ic_launcher);
                    dialog.setTitle("알림");
                    dialog.setMessage("아이디가 중복입니다.");
                    dialog.setNegativeButton("확인", null);
                    dialog.show();
                }
                else if(userpw.length()<6){
                    AlertDialog.Builder dialog=new AlertDialog.Builder(com.example.dbtest1.SignupActivity.this);
                    dialog.setIcon(R.mipmap.ic_launcher);
                    dialog.setTitle("알림");
                    dialog.setMessage("비밀번호는 6자리 이상입니다. 다시 입력 바랍니다.");
                    dialog.setNegativeButton("확인",null);
                    dialog.show();
                }
                else if(userid.equals("")||userpw.equals("")||username.equals("")||useremail.equals("")){
                    AlertDialog.Builder dialog=new AlertDialog.Builder(com.example.dbtest1.SignupActivity.this);
                    dialog.setIcon(R.mipmap.ic_launcher);
                    dialog.setTitle("알림");
                    dialog.setMessage("필수 항목을 모두 입력하시오.");
                    dialog.setNegativeButton("확인",null);
                    dialog.show();
                }

                else{
                    Intent intent = new Intent(getApplicationContext(), com.example.dbtest1.LoginActivity.class);
                    Toast.makeText(getApplicationContext(), "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();

                    ClientDAO mClientDAO = db.clientDAO();
                    Client client = new Client();
                    client.setId(userid);
                    client.setPw(userpw);
                    client.setName(username);
                    client.setMail(useremail);
                    client.setAccNum(useraccount);
                    client.setAddress(useraddress);
                    client.setLogin(true);
                    client.setRole("client");
                    mClientDAO.insert(client);
                    startActivityForResult(intent, RESULT_OK);

                }


            }
        });
    }
}