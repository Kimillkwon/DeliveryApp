package com.example.dbtest1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.util.List;

public class SubRecommendActivity extends AppCompatActivity {


    private Button btn_move;
    private TextView result;

    private RadioGroup foodType;
    private EditText price;
    private Button btn_Opt;
    private Button button;
    private String Opt1, Opt2;
    String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_recommend);

        getSupportActionBar().setTitle("Recommend");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        result=findViewById(R.id.result);

        btn_Opt = findViewById(R.id.btn_Opt);
        btn_move = findViewById(R.id.btn_move);
        button = findViewById(R.id.button);
        foodType=findViewById(R.id.foodType);
        price = findViewById(R.id.price);


        foodType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if(i==R.id.radioButton){
                    type="한식";
                }
                else if(i==R.id.radioButton2){
                    type="중식";
                }
                else if(i==R.id.radioButton3){
                    type="일식";
                }


            }
        });

        btn_Opt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String maxPrice= price.getText().toString();

                AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"db")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build();
                List<String> listResult=db.foodDAO().getRestaurantNamebyPrice(type,Integer.parseInt(maxPrice));

                result.setText("해당 옵션에 따른 식당 이름: \n"+listResult.toString());
            }
        });

        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"db")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build();
                List<String> listResult=db.restaurantDAO().getNamebyCount();
                result.setText("오늘의 추천(가장 많이 검색한) 식당명:\n"+listResult.toString());

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(com.example.dbtest1.SubRecommendActivity.this, RecommendActivity.class);
                startActivity(intent);
            }
        });
    }
}