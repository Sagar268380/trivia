package com.elysino.trivia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.elysino.trivia.model.DataModel;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    QuestionFragnment questionFragnment;
    private static final int CONTENT_VIEW_ID = 10101010;
     ArrayList<DataModel> dataModelArrayList;
    static int position=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       dataModelArrayList=new ArrayList<>();

       dataModelArrayList.add(new DataModel("Who is the best cricketer in the world","Sachin Tendulkar","Virat Kolli","Adam Gilchirst","Jacques Kallis","","single"));
       dataModelArrayList.add(new DataModel("What are the colors in the Indian national flag","White","Yellow","Orange","Green","","multi"));


        DataModel d=dataModelArrayList.get(position);
        String type=d.getQuestion_type();

        if (type.equals("single")){
                Fragment newFragment = new QuestionFragnment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.add(CONTENT_VIEW_ID, newFragment).commit();
        }else {
            Fragment newFragment = new MultipleFragnment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(CONTENT_VIEW_ID, newFragment).commit();
        }
    }
}