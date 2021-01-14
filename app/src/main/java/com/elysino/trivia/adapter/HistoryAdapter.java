package com.elysino.trivia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elysino.trivia.R;
import com.elysino.trivia.model.HistoryModel;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    //Declared variable
    Context context;
    List<HistoryModel> historyModelList;

    public HistoryAdapter(Context context, List<HistoryModel> historyModelList) {
        this.context = context;
        this.historyModelList = historyModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_history, parent, false);
        return new HistoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        HistoryModel historyModel=historyModelList.get(position);


        //set data in text field

        holder.txtQuestion1.setText("Q. "+historyModel.getQuestion1());
        holder.txtQuestion2.setText("Q. "+historyModel.getQuestion2());

        holder.txtAns1.setText("Ans. "+historyModel.getAns1());
        holder.txtAns2.setText("Ans. "+historyModel.getAns2());

        holder.name.setText(historyModel.getName());
        holder.date.setText(historyModel.getDate());

    }

    @Override
    public int getItemCount() {
        return historyModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //Declared views

        TextView txtQuestion1,txtQuestion2,txtAns1,txtAns2,name,date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //Initilize Views

            txtQuestion1=itemView.findViewById(R.id.question1);
            txtQuestion2=itemView.findViewById(R.id.question2);

            txtAns1=itemView.findViewById(R.id.ans1);
            txtAns2=itemView.findViewById(R.id.ans2);

            name=itemView.findViewById(R.id.name);
            date=itemView.findViewById(R.id.date);

        }
    }
}
