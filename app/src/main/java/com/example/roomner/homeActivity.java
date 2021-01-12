package com.example.roomner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class homeActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void clicked(View view)
    {
        int id = view.getId();

        switch (id){
            case R.id.cvPreference:
                Intent intent = new Intent(homeActivity.this, userPreferences.class);
                intent.putExtra("status", "old user");
                startActivity(intent);
                break;
            case R.id.cvMatches:
                startActivity(new Intent(homeActivity.this, showMatches.class));
                break;
            case R.id.cvSignOut:
                signOutDialog();
                break;
            case R.id.cvFeedback:
                feedbackDialog dialog = new feedbackDialog();
                dialog.show(getSupportFragmentManager(), "feedback dialog");
        }
    }

    private void signOutDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Sign Out ?");
        builder.setMessage("Please confirm your signing out");
        builder.setPositiveButton("Yes, sign out", (dialog, which) -> {
            firebaseAuth.signOut();
            startActivity(new Intent(homeActivity.this, MainActivity.class));
        });
        builder.setNegativeButton("No, cancel", null);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}