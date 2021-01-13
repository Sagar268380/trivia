package com.elysino.trivia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.elysino.trivia.model.DataModel;

import java.util.ArrayList;

public class ResultPageActivity extends AppCompatActivity {


    //Declared views and variable

    TextView txtQuestion1,txtQuestion2,txtAns1,txtAns2,txtName;
    Button btnHistory,btnFinish;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);

        //Initilize Views
        txtQuestion1=findViewById(R.id.question1);
        txtQuestion2=findViewById(R.id.question2);
        txtAns1=findViewById(R.id.ans1);
        txtAns2=findViewById(R.id.ans2);
        btnFinish=findViewById(R.id.btnFinish);
        btnHistory=findViewById(R.id.btnHistory);
        txtName=findViewById(R.id.txt_name);

        //Initialize SharedPreferences
        sharedPreferences=getSharedPreferences("DATA", Context.MODE_PRIVATE);


           //set data in text field
            txtName.setText("Hello "+ sharedPreferences.getString("name",""));
            DataModel dataModel=MainActivity.dataModelArrayList.get(0);

            String question1=dataModel.getQuestion();
            String answer1=dataModel.getAnswer();

            txtQuestion1.setText("Q. "+question1+" ? ");
            txtAns1.setText("Ans. "+answer1);

             DataModel dataModel1=MainActivity.dataModelArrayList.get(1);

              String question2=dataModel1.getQuestion();
              String answer2=dataModel1.getAnswer();

               txtQuestion2.setText("Q. "+question2+" ? ");
               txtAns2.setText("Ans. "+answer2);


          //click button finish and start text again
          btnFinish.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent intent=new Intent(ResultPageActivity.this,MainActivity.class);
                  startActivity(intent);
                  finish();
              }
          });

        //click button history and open history page
        btnHistory.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent intent=new Intent(ResultPageActivity.this,HistoryActivity.class);
                  startActivity(intent);
              }
          });

    }
}