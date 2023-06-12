package com.example.dbtest1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class SearchFoodActivity extends AppCompatActivity {
    private TextView textView;
    private Button button;
    private RadioGroup radiogroup;
    ArrayList<CheckBox> list = new ArrayList<>();
    List<Food> listFood = new ArrayList<>();
    public static List<String> cart =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_food);
        textView= findViewById(R.id.textView);
        button=findViewById(R.id.button);

        Intent intent = getIntent();
        String name;

        String index = intent.getStringExtra("index");
        AppDatabase db =Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        name=db.restaurantDAO().getNamebyIndex(Integer.parseInt(index)+1);
        textView.setText(name);

        DisplayRadioButton(name);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "장바구니에 담았습니다.", Toast.LENGTH_SHORT).show();
                String test=Integer.toString(listFood.get(0).getPrice());

                for(int i=0;i<list.size();i++){
                    if(list.get(i).isChecked()==true){
                        cart.add((String) list.get(i).getText());
                    }
                }

            }
        });



    }
    public void DisplayRadioButton(String name) {
        AppDatabase db =Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        int num=db.foodDAO().getFoodbyrestaurant(name).size();
        listFood=db.foodDAO().getFood(name);

        for (int i = 0; i < num; i++) {
            radiogroup = (RadioGroup) findViewById(R.id.RadioGroup);
            CheckBox checkBox = new CheckBox(this);
            checkBox.setId(i);
            checkBox.setText(db.foodDAO().getFoodbyrestaurant(name).get(i).toString());
            list.add(checkBox);
            radiogroup.addView(checkBox);
        }

    }

}