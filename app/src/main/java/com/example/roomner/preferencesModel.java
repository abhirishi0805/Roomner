package com.example.roomner;

import java.io.Serializable;

public class preferencesModel implements Serializable
{
    private questionUploadModel Question_1;
    private questionUploadModel Question_2;
    private questionUploadModel Question_3;
    private questionUploadModel Question_4;
    private questionUploadModel Question_5;
    private questionUploadModel Question_6;
    private questionUploadModel Question_7;
    private questionUploadModel Question_8;
    private questionUploadModel Question_9;
    private questionUploadModel Question_10;
    private int weight_sum;

    public preferencesModel() {
    }

    public preferencesModel(questionUploadModel question_1, questionUploadModel question_2, questionUploadModel question_3, questionUploadModel question_4, questionUploadModel question_5, questionUploadModel question_6, questionUploadModel question_7, questionUploadModel question_8, questionUploadModel question_9, questionUploadModel question_10, int weight_sum) {
        Question_1 = question_1;
        Question_2 = question_2;
        Question_3 = question_3;
        Question_4 = question_4;
        Question_5 = question_5;
        Question_6 = question_6;
        Question_7 = question_7;
        Question_8 = question_8;
        Question_9 = question_9;
        Question_10 = question_10;
        this.weight_sum = weight_sum;
    }

    public questionUploadModel getQuestion_1() {
        return Question_1;
    }

    public void setQuestion_1(questionUploadModel question_1) {
        Question_1 = question_1;
    }

    public questionUploadModel getQuestion_2() {
        return Question_2;
    }

    public void setQuestion_2(questionUploadModel question_2) {
        Question_2 = question_2;
    }

    public questionUploadModel getQuestion_3() {
        return Question_3;
    }

    public void setQuestion_3(questionUploadModel question_3) {
        Question_3 = question_3;
    }

    public questionUploadModel getQuestion_4() {
        return Question_4;
    }

    public void setQuestion_4(questionUploadModel question_4) {
        Question_4 = question_4;
    }

    public questionUploadModel getQuestion_5() {
        return Question_5;
    }

    public void setQuestion_5(questionUploadModel question_5) {
        Question_5 = question_5;
    }

    public questionUploadModel getQuestion_6() {
        return Question_6;
    }

    public void setQuestion_6(questionUploadModel question_6) {
        Question_6 = question_6;
    }

    public questionUploadModel getQuestion_7() {
        return Question_7;
    }

    public void setQuestion_7(questionUploadModel question_7) {
        Question_7 = question_7;
    }

    public questionUploadModel getQuestion_8() {
        return Question_8;
    }

    public void setQuestion_8(questionUploadModel question_8) {
        Question_8 = question_8;
    }

    public questionUploadModel getQuestion_9() {
        return Question_9;
    }

    public void setQuestion_9(questionUploadModel question_9) {
        Question_9 = question_9;
    }

    public questionUploadModel getQuestion_10() {
        return Question_10;
    }

    public void setQuestion_10(questionUploadModel question_10) {
        Question_10 = question_10;
    }

    public int getWeight_sum() {
        return weight_sum;
    }

    public void setWeight_sum(int weight_sum) {
        this.weight_sum = weight_sum;
    }
}
