package com.example.roomner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class personalDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinnerAge, spinnerCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        spinnerAge = (Spinner) findViewById(R.id.spinnerAge);
        ArrayAdapter<CharSequence> adapterAge = ArrayAdapter.createFromResource(this, R.array.age_array, R.layout.support_simple_spinner_dropdown_item);
        adapterAge.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerAge.setAdapter(adapterAge);
        spinnerAge.setOnItemSelectedListener(this);

        spinnerCity = (Spinner) findViewById(R.id.spinnerCity);
        ArrayAdapter<CharSequence> adapterCity = ArrayAdapter.createFromResource(this, R.array.city_array, R.layout.support_simple_spinner_dropdown_item);
        adapterCity.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerCity.setAdapter(adapterCity);
        spinnerCity.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String age = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}