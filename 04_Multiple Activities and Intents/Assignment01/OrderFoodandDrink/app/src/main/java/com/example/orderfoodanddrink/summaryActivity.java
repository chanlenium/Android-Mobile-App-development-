package com.example.orderfoodanddrink;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;

public class summaryActivity extends AppCompatActivity {
    private ArrayList<Menu> foodList;
    private ArrayList<Menu> drinkList;
    private ListView listView;
    private ArrayList<String> selectedMenu;
    private double total;
    EditText totalEt;
    Button editOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        init(); // function to initialize variables
        makeListItems(foodList);    // function to make a list composed of selected items(menus)
        makeListItems(drinkList);

        // make array adapter to display makeListItems on ListView
        ArrayAdapter<String> menuAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, selectedMenu);
        listView.setAdapter(menuAdapter);

        displayTotal(total);    // function to display order Total

        editOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestEditOrder();   // function to open selectFoodActivity
            }
        });

        Button submitBtn = findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity(); // function to open MainActivity
            }
        });
    }

    private void displayTotal(double total) {
        // Display total in terms of CAD
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        formatter.setMaximumFractionDigits(2);
        formatter.setCurrency(Currency.getInstance("CAD"));
        totalEt.setText(formatter.format(total));
    }

    private void init() {
        // get reference to the intent and get data from intent
        foodList = getIntent().getParcelableArrayListExtra("foodList");
        drinkList = getIntent().getParcelableArrayListExtra("drinkList");
        listView = findViewById(R.id.listView);
        selectedMenu = new ArrayList<String>();
        total = 0;
        totalEt = findViewById(R.id.totalEt);
        editOrder = findViewById(R.id.editOrderBtn);
    }

    private void makeListItems(ArrayList<Menu> menuList) {
        // add the menu checked and calculate total
        for(int i = 0; i < menuList.size(); i++){
            if(menuList.get(i).isChecked()) {
                selectedMenu.add(menuList.get(i).getName());
                total += menuList.get(i).getPrice();
            }
        }
    }

    private void openMainActivity() {   // Open MainActivity without passing parameters
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void requestEditOrder() { // Open selectedFoodActivity for editing order
        Intent intent = new Intent();
        //intent.putParcelableArrayListExtra("foodList", foodList);
        //intent.putParcelableArrayListExtra("drinkList", drinkList);
        setResult(RESULT_OK, intent); // Here, 100 is same as the REQUEST_CODE from selectFoodActivity
        finish();
    }
}