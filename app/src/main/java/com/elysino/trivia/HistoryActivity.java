package com.elysino.trivia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import com.elysino.trivia.adapter.HistoryAdapter;
import com.elysino.trivia.model.HistoryModel;
import com.elysino.trivia.sqllite.DatabaseHelper;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {


    //Declared views and variable
    RecyclerView recyclerView;
    DatabaseHelper myhelper;
    ArrayList<HistoryModel> historyModelArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //Initialize View
        recyclerView=findViewById(R.id.recycle_view);
        historyModelArrayList=new ArrayList<>();

        //create object for read data or write data
        myhelper = new DatabaseHelper(this);
        SQLiteDatabase database = myhelper.getReadableDatabase();
        String sql = "select * from  HISTORY";

        //read all data for show user history
        Cursor c = database.rawQuery(sql, null);
        Cursor d = database.rawQuery(sql, null);
        if (d.moveToFirst()) {
            while (c.moveToNext()) {
                String question1=c.getString(1);
                String ans1=c.getString(2);
                String question2=c.getString(3);
                String ans2=c.getString(4);
                String name=c.getString(5);
                String date=c.getString(6);

                //add data in model
                historyModelArrayList.add(new HistoryModel(question1,question2,ans1,ans2,name,date));
            }
        }

        //set data in adapter
        HistoryAdapter historyAdapter=new HistoryAdapter(this,historyModelArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(historyAdapter);

    }
}