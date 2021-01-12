package com.example.roomner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    TextView tvSignUp;
    Button btnSubmit;
    ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        tvSignUp = (TextView) findViewById(R.id.tvSignUp);

        mAuth = FirebaseAuth.getInstance();

        tvSignUp.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, registerUser.class)));


        btnSubmit.setOnClickListener(view -> {

            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty())
                Toast.makeText(MainActivity.this, "Please fill the fields!", Toast.LENGTH_SHORT).show();
            else {
                progressBar.setVisibility(View.VISIBLE);

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, task -> {
                            if (task.isSuccessful()) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(MainActivity.this, "Successfully Loged In", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, homeActivity.class);
                                startActivity(new Intent(MainActivity.this, homeActivity.class));
                            } else {
                                Toast.makeText(MainActivity.this, "LogIn Failed or User Not Available", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        });
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(this, homeActivity.class));
        }
    }
}