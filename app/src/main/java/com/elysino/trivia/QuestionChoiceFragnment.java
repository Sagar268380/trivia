package com.elysino.trivia;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.elysino.trivia.model.DataModel;

public class QuestionChoiceFragnment extends Fragment {

    //Declared views and variable

    Button btnNext,btnSubmit;
    RadioGroup radioGroup;
    String ans;
    DataModel dataModel;
    TextView question1;
    RadioButton option1,option2,option3,option4;
    public QuestionChoiceFragnment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_single_choice_fragnment, container, false);


        //Initilize Views
        btnNext=view.findViewById(R.id.btnNext);
        btnSubmit=view.findViewById(R.id.btnSubmit);
        option1=view.findViewById(R.id.option1);
        option2=view.findViewById(R.id.option2);
        option3=view.findViewById(R.id.option3);
        option4=view.findViewById(R.id.option4);
        question1=view.findViewById(R.id.question1);
        radioGroup=view.findViewById(R.id.radioGender);



        //Add question and option
        question1.setText(MainActivity.dataModelArrayList.get(MainActivity.position).getQuestion());
        option1.setText(MainActivity.dataModelArrayList.get(MainActivity.position).getOption1());
        option2.setText(MainActivity.dataModelArrayList.get(MainActivity.position).getOption2());
        option3.setText(MainActivity.dataModelArrayList.get(MainActivity.position).getOption3());
        option4.setText(MainActivity.dataModelArrayList.get(MainActivity.position).getOption4());

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //find radioGroup
                 RadioButton rb = (RadioButton) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
                 int selectedId = radioGroup.getCheckedRadioButtonId();
                 dataModel= MainActivity.dataModelArrayList.get(MainActivity.position);

                 //select readioButton means select option it's dynamic depend on question position question tpye
                if(selectedId==-1){
                    Toast.makeText(getActivity(),"Nothing selected", Toast.LENGTH_SHORT).show();
                }
                else{
                    ans=rb.getText().toString();
                    dataModel.setAnswer(ans);
                    MainActivity.position++;
                    //if question single type then show UI single choice
                    if (MainActivity.dataModelArrayList.get(MainActivity.position).getQuestion_type().equals("multi")){
                        Fragment fragment = new MultipleChoiceFragnment();
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.content_frame, fragment);
                        fragmentTransaction.commit();
                    }else {
                        //Add question and option

                        question1.setText(MainActivity.dataModelArrayList.get(MainActivity.position).getQuestion());
                        option1.setText(MainActivity.dataModelArrayList.get(MainActivity.position).getOption1());
                        option2.setText(MainActivity.dataModelArrayList.get(MainActivity.position).getOption2());
                        option3.setText(MainActivity.dataModelArrayList.get(MainActivity.position).getOption3());
                        option4.setText(MainActivity.dataModelArrayList.get(MainActivity.position).getOption4());
                    }
                }
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
                dataModel.setAnswer(ans);
            }
        });


        return view;
    }
}