package com.example.roomner;

public class questionDisplayModel {
    private String question;
    private String high;
    private String neutral;
    private String low;

    public questionDisplayModel() {
    }

    public questionDisplayModel(String question, String high, String neutral, String low) {
        this.question = question;
        this.high = high;
        this.neutral = neutral;
        this.low = low;
    }

    public String getQuestion() {
        return question;
    }

    public String getHigh() {
        return high;
    }

    public String getNeutral() {
        return neutral;
    }

    public String getLow() {
        return low;
    }
}
