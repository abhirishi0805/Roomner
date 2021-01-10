package com.example.roomner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class homeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void clicked(View view)
    {
        int id = view.getId();

        switch (id){
            case R.id.cvPreference:
                startActivity(new Intent(homeActivity.this, userPreferences.class));
                break;
            case R.id.cvMatches:
                startActivity(new Intent(homeActivity.this, showMatches.class));
                break;
            case R.id.cvSignOut:
                break;
            case R.id.cvFeedback:
        }
    }
}