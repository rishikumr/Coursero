package com.example.coursero.model;

import java.util.Arrays;

public class Quiz {

    private String question;
    private String[] answerList;
    private int ans;

    public Quiz(String question, String[] answerList, int ans) {
        this.question = question;
        this.answerList = answerList;
        this.ans = ans;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getAnswerList() {
        return answerList;
    }

    public void setAnswerList(String[] answerList) {
        this.answerList = answerList;
    }

    public int getAns() {
        return ans;
    }

    public void setAns(int ans) {
        this.ans = ans;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "question='" + question + '\'' +
                ", answerList=" + Arrays.toString(answerList) +
                ", ans=" + ans +
                '}';
    }
}
