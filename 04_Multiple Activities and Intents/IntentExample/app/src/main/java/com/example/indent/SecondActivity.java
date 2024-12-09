package com.example.indent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    public static final String NAME_KEY = "name_key";
    public static final String USER_KEY = "user_key";
    public static final String AGE_KEY = "age_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // 1. get the reference to the intent declared in MainActivity
        Intent intent = getIntent();

        // 2. get data from the intent using name tag
        if(intent != null){
            if (intent.hasExtra(NAME_KEY)) {
                String name = intent.getStringExtra(NAME_KEY);
                Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
            }

            if (intent.hasExtra(USER_KEY)) {
                User user = (User) intent.getParcelableExtra(USER_KEY);
                Log.d("User", user.getUserName());
                Log.d("User", String.valueOf(user.getAge()));
                Log.d("User", user.getEmail());
            }

            // 3. print message

        }
    }

    // The activity will return result once it done
    public void sendResult(View view) {
        EditText ageEt = findViewById(R.id.ageEt);
        String age = ageEt.getText().toString();

        // Generate intent object to feedback a result
        Intent intent = new Intent();
        // when the age exists, set 'resultCode' as RESULT_OK
        if(age.isEmpty()){
            setResult(RESULT_CANCELED);
        }else{
            intent.putExtra(AGE_KEY, age);
            setResult(RESULT_OK, intent);
        }
        finish();
    }
}