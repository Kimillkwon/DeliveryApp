package com.example.dbtest1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Button button1=findViewById(R.id.button1);
        Button button2=findViewById(R.id.button2);
        String userid=getIntent().getStringExtra("userid");

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(SearchFoodActivity.cart.isEmpty()){
                    Toast.makeText(getApplicationContext(), "장바구니에 담은 주문 목록이 없습니다.", Toast.LENGTH_SHORT).show();
                }
                else{
                    int totalPrice=0;
                    List<String> list=new ArrayList<>();
                    for(int i=0;i<SearchFoodActivity.cart.size();i++){
                        String[] prices=SearchFoodActivity.cart.get(i).split("\n");
                        String[] price= prices[1].split(" ");
                        list.add(price[1]);
                    }
                    for(int i=0;i<list.size();i++){
                        totalPrice+=Integer.parseInt(list.get(i));
                    }
                    AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"db")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                    if(db.clientDAO().getClientbyID(userid).getAccNum().equals("") || db.clientDAO().getClientbyID(userid).getAddress().equals("")){
                        Toast.makeText(getApplicationContext(), "카드 번호와 주소를 입력하시오.", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(com.example.dbtest1.PaymentActivity.this, NoInformDataActivity.class);
                        intent.putExtra("totalPrice",Integer.toString(totalPrice));
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "총"+Integer.toString(totalPrice)+"원 결재 완료하였습니다", Toast.LENGTH_SHORT).show();
                        SearchFoodActivity.cart.clear();
                    }
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(com.example.dbtest1.PaymentActivity.this, OrderListActivity.class);
                startActivity(intent);
            }
        });

    }
}