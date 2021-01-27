package com.example.roomner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class registerUser extends AppCompatActivity {

    EditText etEmail;
    Button btnProceed;
    ProgressBar progressBar;
    TextInputLayout input_text_password, input_text_confirmPassword;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        etEmail = (EditText) findViewById(R.id.etEmail);
        btnProceed = (Button) findViewById(R.id.btnSubmit);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        input_text_password = (TextInputLayout) findViewById(R.id.input_text_password);
        input_text_confirmPassword = (TextInputLayout) findViewById(R.id.input_text_confirmPassword);

        btnProceed.setOnClickListener(view -> {

            input_text_password.setError(null);
            input_text_confirmPassword.setError(null);

            String email = etEmail.getText().toString().trim();
            String password = input_text_password.getEditText().getText().toString().trim();
            String confirmPassword = input_text_confirmPassword.getEditText().getText().toString().trim();

            mAuth = FirebaseAuth.getInstance();

            if(email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
                Toast.makeText(registerUser.this, "Please enter all fields!!", Toast.LENGTH_SHORT).show();
            }
            else if(password.length() < 6) {
                input_text_password.setError("Password must be of atleast 6 characters");
            }
            else if(!password.equals(confirmPassword)){
                input_text_confirmPassword.setError("Passwords don't match");
            }
            else{
                progressBar.setVisibility(View.VISIBLE);

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(registerUser.this, task -> {

                            progressBar.setVisibility(View.GONE);

                            if (task.isSuccessful()) {
                                Intent intent = new Intent(registerUser.this, uploadDP.class);
                                intent.putExtra("email", email);
                                intent.putExtra("password", password);
                                startActivity(intent);
                                Toast.makeText(registerUser.this, "User Registered", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(registerUser.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}