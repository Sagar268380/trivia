package com.elysino.trivia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NamePageActivity extends AppCompatActivity {

    //Declared View
    EditText etName;
    Button btnNext;

    //Declared SharedPreferences
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_page);

        //Initialize View And SharedPreferences
        sharedPreferences=getSharedPreferences("DATA",MODE_PRIVATE);
        editor=sharedPreferences.edit();

        etName=findViewById(R.id.et_name);
        btnNext=findViewById(R.id.btnNext);

        //Click on button
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Put data in SharedPreferences for further use
                editor.putString("name",etName.getText().toString());
                editor.apply();

                //Use Intent for Change Activity
                Intent intent=new Intent(NamePageActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}