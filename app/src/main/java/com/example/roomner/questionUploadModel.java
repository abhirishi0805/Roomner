package com.example.roomner;

import java.io.Serializable;

public class questionUploadModel implements Serializable
{
    private int choice;
    private int importance;

    public questionUploadModel() {
    }

    public questionUploadModel(int choice, int importance) {
        this.choice = choice;
        this.importance = importance;
    }

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }
}
