package com.example.roomner;

public class matchHelperModel {
    private personalDataModel personalData;
    private int[] choices;
    private int[] importance;
    private int weightSum;
    private int percentageMatch;

    public matchHelperModel(personalDataModel personalData, int[] choices, int[] importance, int weightSum, int percentageMatch) {
        this.personalData = personalData;
        this.choices = choices;
        this.importance = importance;
        this.weightSum = weightSum;
        this.percentageMatch = percentageMatch;
    }

    public personalDataModel getPersonalData() {
        return personalData;
    }

    public void setPersonalData(personalDataModel personalData) {
        this.personalData = personalData;
    }

    public int[] getChoices() {
        return choices;
    }

    public void setChoices(int[] choices) {
        this.choices = choices;
    }

    public int[] getImportance() {
        return importance;
    }

    public void setImportance(int[] importance) {
        this.importance = importance;
    }

    public int getWeightSum() {
        return weightSum;
    }

    public void setWeightSum(int weightSum) {
        this.weightSum = weightSum;
    }

    public int getPercentageMatch() {
        return percentageMatch;
    }

    public void setPercentageMatch(int percentageMatch) {
        this.percentageMatch = percentageMatch;
    }
}
