package com.example.roomner;

public class matchHelperModel {
    private userModel user;
    private int percentageMatch;

    public matchHelperModel() {
    }

    public matchHelperModel(userModel user, int percentageMatch) {
        this.user = user;
        this.percentageMatch = percentageMatch;
    }

    public userModel getUser() {
        return user;
    }

    public void setUser(userModel user) {
        this.user = user;
    }

    public int getPercentageMatch() {
        return percentageMatch;
    }

    public void setPercentageMatch(int percentageMatch) {
        this.percentageMatch = percentageMatch;
    }
}
