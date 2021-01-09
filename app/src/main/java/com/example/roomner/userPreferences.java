package com.example.roomner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.MessageFormat;
import java.util.ArrayList;

public class userPreferences extends AppCompatActivity {

    TextView tvQuestion, tvSerial;
    RadioGroup rgChoice;
    RadioButton rbHigh, rbNeutral, rbLow;
    SeekBar sbImportance;
    Button btnPrevious, btnNext, btnSubmitPref;
    ProgressBar progressBar2;

    ArrayList<questionModel> questions;
    int currentQuestion = 0;
    int[] choices = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
    int[] importance = {1,1,1,1,1,1,1,1,1,1};

    String uid;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_preferences);

        tvQuestion = (TextView) findViewById(R.id.tvQuestion);
        rgChoice = (RadioGroup) findViewById(R.id.rgChoice);
        rbHigh = (RadioButton) findViewById(R.id.rbHigh);
        rbNeutral = (RadioButton) findViewById(R.id.rbNeutral);
        rbLow = (RadioButton) findViewById(R.id.rbLow);
        sbImportance = (SeekBar) findViewById(R.id.sbImportance);
        tvSerial = (TextView) findViewById(R.id.tvSerial);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnPrevious = (Button) findViewById(R.id.btnPrevious);
        btnSubmitPref = (Button) findViewById(R.id.btnSubmitPref);
        progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);

        questions = new ArrayList<questionModel>();

        questions.add(new questionModel("At what time do you get up?", "Quite early", "Around 7-8", "Quite late"));
        questions.add(new questionModel("At what time do you sleep?", "About 9", "Around 10-11", "Quite late"));
        questions.add(new questionModel("How important is cleanliness to you?", "I keep my surroundings extremely clean", "I lie somewhere in between", "I'm not much into cleanliness"));
        questions.add(new questionModel("Are you into the habit of drinking or smoking?", "Quite often", "Sometimes", "Never or rarely"));
        questions.add(new questionModel("How often do you stay outside the house?", "Most of the times", "Occasional outings", "Only during the working hours"));
        questions.add(new questionModel("Are you into gaming?", "Yes oftenly", "Sometimes", "Not at all"));
        questions.add(new questionModel("Do you have guests or friends at home?", "Yes, quite oftenly", "Maybe once or twice in a month", "No, I don't bring people to my house"));
        questions.add(new questionModel("Do you use nightlamps?", "Yes I do", "I'm comfortable either way", "No I don't"));
        questions.add(new questionModel("Are you into using your roommate's stuff?", "Yes, I like sharing", "I'm ok with occasional borrowing and lendings", "No, I'm not much into sharing"));
        questions.add(new questionModel("Do you listen to loud music?", "Almost everyday", "Sometimes", "Not at all"));

        btnSubmitPref.setVisibility(View.GONE);

        questionAdapter();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentQuestion != 9)
                {
                    if(rbHigh.isChecked() || rbNeutral.isChecked() || rbLow.isChecked()) {
                        ++currentQuestion;
                        questionAdapter();
                    }
                    else
                    {
                        Toast.makeText(userPreferences.this, "Please select something", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentQuestion != 0)
                {
                    if(rbHigh.isChecked() || rbNeutral.isChecked() || rbLow.isChecked()) {
                        --currentQuestion;
                        questionAdapter();
                    }
                    else
                    {
                        Toast.makeText(userPreferences.this, "Please select something", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnSubmitPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar2.setVisibility(View.VISIBLE);

                uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference(uid);

                int total_weight = 0;

                for(int i=0; i<=9; ++i)
                {
                    databaseReference.child("Preferences").child("Question "+(i+1)).child("Choice").setValue(choices[i]);
                    databaseReference.child("Preferences").child("Question "+(i+1)).child("Importance").setValue(importance[i]);
                    total_weight += importance[i];
                }

                databaseReference.child("Weight Sum").setValue(total_weight);

                Toast.makeText(userPreferences.this, "Preferences Registered", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(userPreferences.this, homeActivity.class));
            }
        });

        rbHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choices[currentQuestion] = 3;

                if(currentQuestion == 9)
                    btnSubmitPref.setVisibility(View.VISIBLE);
            }
        });

        rbNeutral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choices[currentQuestion] = 2;

                if(currentQuestion == 9)
                    btnSubmitPref.setVisibility(View.VISIBLE);
            }
        });

        rbLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choices[currentQuestion] = 1;

                if(currentQuestion == 9)
                    btnSubmitPref.setVisibility(View.VISIBLE);
            }
        });

        sbImportance.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress == 0) {
                    importance[currentQuestion] = 1;
                    Toast.makeText(userPreferences.this, "Not much important", Toast.LENGTH_SHORT).show();
                }
                else if(progress == 1) {
                    importance[currentQuestion] = 2;
                    Toast.makeText(userPreferences.this, "Quite important", Toast.LENGTH_SHORT).show();
                }
                else if(progress == 2) {
                    importance[currentQuestion] = 3;
                    Toast.makeText(userPreferences.this, "Extremely important", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(userPreferences.this, "Drag and pick a value", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void questionAdapter()
    {
        tvQuestion.setText(questions.get(currentQuestion).getQuestion());
        rbHigh.setText(questions.get(currentQuestion).getHigh());
        rbNeutral.setText(questions.get(currentQuestion).getNeutral());
        rbLow.setText(questions.get(currentQuestion).getLow());
        tvSerial.setText(MessageFormat.format("{0}/10", currentQuestion+1));

        if(choices[currentQuestion] == -1)
        rgChoice.clearCheck();
        else if(choices[currentQuestion] == 3)
            rbHigh.setChecked(true);
        else if(choices[currentQuestion] == 2)
            rbNeutral.setChecked(true);
        else if(choices[currentQuestion] == 1)
            rbLow.setChecked(true);

        if(importance[currentQuestion] == 1)
            sbImportance.setProgress(0);
        else if(importance[currentQuestion] == 2)
            sbImportance.setProgress(1);
        else if(importance[currentQuestion] == 3)
            sbImportance.setProgress(2);
    }
}