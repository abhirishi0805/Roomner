package com.example.roomner;

import java.io.Serializable;

public class userModel implements Serializable
{
    private personalDataModel Personal_Data;
    private preferencesModel Preferences;

    public userModel() {
    }

    public userModel(personalDataModel personal_Data, preferencesModel preferences) {
        Personal_Data = personal_Data;
        Preferences = preferences;
    }

    public personalDataModel getPersonal_Data() {
        return Personal_Data;
    }

    public void setPersonal_Data(personalDataModel personal_Data) {
        Personal_Data = personal_Data;
    }

    public preferencesModel getPreferences() {
        return Preferences;
    }

    public void setPreferences(preferencesModel preferences) {
        Preferences = preferences;
    }
}
