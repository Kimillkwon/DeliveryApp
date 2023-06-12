package com.example.dbtest1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NoInformDataActivity extends AppCompatActivity {

    EditText accountNum;
    EditText address;
    Button payment;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_inform_data);
        accountNum=findViewById(R.id.accountNum);
        address=findViewById(R.id.address);
        payment=findViewById(R.id.payment);
        button=findViewById(R.id.button);

        String totalPrice=getIntent().getStringExtra("totalPrice");

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "총"+totalPrice+"원 결재 완료하였습니다", Toast.LENGTH_SHORT).show();
                SearchFoodActivity.cart.clear();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NoInformDataActivity.this, PaymentActivity.class);
                startActivity(intent);
            }
        });


    }
}