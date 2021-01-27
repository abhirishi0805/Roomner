package com.example.roomner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class personalDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinnerAge, spinnerCity;
    EditText etName, etNumber;
    RadioGroup rgGender;
    Button btnRegister;

    String age, city, name, number, gender, email, password, id, uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        etName = (EditText) findViewById(R.id.etName);
        etNumber = (EditText) findViewById(R.id.etNumber);
        rgGender = (RadioGroup) findViewById(R.id.rgGender);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        spinnerAge = (Spinner) findViewById(R.id.spinnerAge);
        spinnerCity = (Spinner) findViewById(R.id.spinnerCity);

        email = getIntent().getExtras().getString("email");
        password = getIntent().getExtras().getString("password");

        ArrayAdapter<CharSequence> adapterAge = ArrayAdapter.createFromResource(this,
                R.array.age_array, R.layout.support_simple_spinner_dropdown_item);
        adapterAge.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerAge.setAdapter(adapterAge);
        spinnerAge.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapterCity = ArrayAdapter.createFromResource(this,
                R.array.city_array, R.layout.support_simple_spinner_dropdown_item);
        adapterCity.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerCity.setAdapter(adapterCity);
        spinnerCity.setOnItemSelectedListener(this);


        btnRegister.setOnClickListener(view -> {
            name = etName.getText().toString().trim();
            number = etNumber.getText().toString().trim();

            switch (rgGender.getCheckedRadioButtonId()){
                case R.id.rbMale:
                    gender = "Male";
                    break;
                case R.id.rbFemale:
                    gender = "Female";
                    break;
                case R.id.rbTrans:
                    gender = "Transgender";
            }

            if(name.isEmpty() || number.isEmpty() || gender.isEmpty() || age.equals("Age") || city.equals("City")) {
                Toast.makeText(personalDetails.this, "Please enter all details", Toast.LENGTH_SHORT).show();
            }
            else {
                uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                personalDataModel Personal_Data = new personalDataModel(uid, email, password, name, gender, age, number, city);

                Intent intent = new Intent(personalDetails.this, userPreferences.class);
                intent.putExtra("Personal_Data", Personal_Data);
                intent.putExtra("status", "new user");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(adapterView.getId() == R.id.spinnerAge)
            age = adapterView.getItemAtPosition(i).toString().trim();
        else if(adapterView.getId() == R.id.spinnerCity)
            city = adapterView.getItemAtPosition(i).toString().trim();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}