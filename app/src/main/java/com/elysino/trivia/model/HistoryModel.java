package com.elysino.trivia.model;

public class HistoryModel {
    String question1,question2,ans1,ans2,name,date;

    public HistoryModel(String question1, String question2, String ans1, String ans2, String name, String date) {
        this.question1 = question1;
        this.question2 = question2;
        this.ans1 = ans1;
        this.ans2 = ans2;
        this.name = name;
        this.date = date;
    }

    public String getQuestion1() {
        return question1;
    }

    public void setQuestion1(String question1) {
        this.question1 = question1;
    }

    public String getQuestion2() {
        return question2;
    }

    public void setQuestion2(String question2) {
        this.question2 = question2;
    }

    public String getAns1() {
        return ans1;
    }

    public void setAns1(String ans1) {
        this.ans1 = ans1;
    }

    public String getAns2() {
        return ans2;
    }

    public void setAns2(String ans2) {
        this.ans2 = ans2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
