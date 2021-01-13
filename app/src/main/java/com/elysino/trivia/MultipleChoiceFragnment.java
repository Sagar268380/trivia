package com.elysino.trivia;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.elysino.trivia.model.DataModel;
import com.elysino.trivia.sqllite.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MultipleChoiceFragnment extends Fragment {

    //Declared views and variable

    Button btnNext,btnSubmit;
    CheckBox ch, ch1, ch2, ch3;
    DataModel dataModel;
    String msg="";
    TextView question2;
    SQLiteDatabase database;
    DatabaseHelper myhelper;
    SharedPreferences sharedPreferences;

    public MultipleChoiceFragnment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_multiple_choice_fragnment, container, false);

        //Initilize Views
        btnNext=view.findViewById(R.id.btnNext);
        btnSubmit=view.findViewById(R.id.btnSubmit);
        ch=view.findViewById(R.id.ch1);
        ch1=view.findViewById(R.id.ch2);
        ch2=view.findViewById(R.id.ch3);
        ch3=view.findViewById(R.id.ch4);
        question2=view.findViewById(R.id.question2);


        //Initialize SharedPreferences
        sharedPreferences=getActivity().getSharedPreferences("DATA", Context.MODE_PRIVATE);

        //create object for read data or write data
        myhelper=new DatabaseHelper(getActivity());
        database=myhelper.getWritableDatabase();
        final SQLiteDatabase db=myhelper.getReadableDatabase();

        //Add question and option

        question2.setText(MainActivity.dataModelArrayList.get(MainActivity.position).getQuestion());
        ch.setText(MainActivity.dataModelArrayList.get(MainActivity.position).getOption1());
        ch1.setText(MainActivity.dataModelArrayList.get(MainActivity.position).getOption2());
        ch2.setText(MainActivity.dataModelArrayList.get(MainActivity.position).getOption3());
        ch3.setText(MainActivity.dataModelArrayList.get(MainActivity.position).getOption4());


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataModel = MainActivity.dataModelArrayList.get(MainActivity.position);

                MainActivity.position++;
                if (MainActivity.dataModelArrayList.get(MainActivity.position).getQuestion_type().equals("single")){
                    Fragment fragment = new QuestionChoiceFragnment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content_frame, fragment);
                    fragmentTransaction.commit();
                }else {
                    //Add question and option

                    question2.setText(MainActivity.dataModelArrayList.get(MainActivity.position).getQuestion());
                    ch.setText(MainActivity.dataModelArrayList.get(MainActivity.position).getOption1());
                    ch1.setText(MainActivity.dataModelArrayList.get(MainActivity.position).getOption2());
                    ch2.setText(MainActivity.dataModelArrayList.get(MainActivity.position).getOption3());
                    ch3.setText(MainActivity.dataModelArrayList.get(MainActivity.position).getOption4());
                }
              /*  if (type.equals("single")){
                    Fragment fragment = new QuestionFragnment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content_frame, fragment);
                    fragmentTransaction.commit();
                }else {
                    if(ch.isChecked())
                        msg = ch.getText().toString();
                    if(ch1.isChecked())
                        msg = ch1.getText().toString();
                    if(ch2.isChecked())
                        msg = ch2.getText().toString();
                    if(ch3.isChecked())
                        msg = ch3.getText().toString();

                    dataModel.setAnswer(msg);
                }*/
            }


        });
        //check arrayList reach the last position or not if reach then visible submit button

        if (MainActivity.position==MainActivity.dataModelArrayList.size()-1){
            btnSubmit.setVisibility(View.VISIBLE);
            btnNext.setVisibility(View.GONE);
        }
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //check checkbox check or not then if checkbox check more then one then concatenation
                if(ch.isChecked())
                    msg = ch.getText().toString();

                if(ch1.isChecked() && msg.equals(""))
                    msg = ch1.getText().toString();
                else if (ch1.isChecked())
                    msg+=" , " +ch1.getText().toString();

                if(ch2.isChecked() && msg.equals(""))
                    msg = ch2.getText().toString();
                else if (ch2.isChecked())
                    msg+=" , " +ch2.getText().toString();

                if(ch3.isChecked() && msg.equals(""))
                    msg = ch3.getText().toString();
                else if (ch3.isChecked())
                    msg+=" , " +ch3.getText().toString();

                //set answerr

                MainActivity.dataModelArrayList.get(MainActivity.position).setAnswer(msg);
                Date date = new Date();
                Calendar cal=Calendar.getInstance();
                cal.setTime(date);
                String dayNumberSuffix = getDayNumberSuffix(cal.get(Calendar.DAY_OF_MONTH));

                //make date with suffix
                SimpleDateFormat sdf8 = new SimpleDateFormat("d'"+dayNumberSuffix+"' MMM hh:mm aaa");
                String currentDateandTime = sdf8.format(new Date());


                //put data in content values
                ContentValues values = new ContentValues();
                values.put("QUESTION1", MainActivity.dataModelArrayList.get(0).getQuestion());
                values.put("ANSWER1", MainActivity.dataModelArrayList.get(0).getAnswer());
                values.put("QUESTION2", MainActivity.dataModelArrayList.get(MainActivity.position).getQuestion());
                values.put("ANSWER2", msg);
                values.put("NAME",sharedPreferences.getString("name",""));
                values.put("DATE",currentDateandTime);

                // Inserting Row
                db.insert("HISTORY", null, values);
                //2nd argument is String containing nullColumnHack
                db.close();
                // Closing databas

                Intent intent=new Intent(getActivity(),ResultPageActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return view;
    }

    //this function for return date with suffix
    private String getDayNumberSuffix(int day) {
        if (day >= 11 && day <= 13) {
            return "th";
        }
        switch (day % 10) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }
}