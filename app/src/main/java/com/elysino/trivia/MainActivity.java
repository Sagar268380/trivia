package com.elysino.trivia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.TextView;

import com.elysino.trivia.model.DataModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Declared arrayList and variable
    static ArrayList<DataModel> dataModelArrayList;
    static int position=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //assign value
        position=0;

        //Initialize array list
        dataModelArrayList=new ArrayList<>();

        //Add data in array i have make a UI For dynamic if We have more question so it will work
        // I was given only two question but further more question so problem occur so i developed a app for dynamic

        //We have two type of question single choice or multi choice.
        dataModelArrayList.add(new DataModel("Who is the best cricketer in the world","Sachin Tendulkar","Virat Kolli","Adam Gilchirst","Jacques Kallis","","single"));
        dataModelArrayList.add(new DataModel("What are the colors in the Indian national flag","White","Yellow","Orange","Green","","multi"));

        //get position or question type
        DataModel d=dataModelArrayList.get(position);
        String type=d.getQuestion_type();

        //check which type of question then move
        if (type.equals("single")){
            Fragment fragment = new QuestionChoiceFragnment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, fragment);
            fragmentTransaction.commit();
        }else {
            Fragment fragment = new MultipleChoiceFragnment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, fragment);
            fragmentTransaction.commit();
        }
    }


}