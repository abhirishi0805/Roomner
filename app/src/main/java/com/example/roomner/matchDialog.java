package com.example.roomner;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class matchDialog extends AppCompatDialogFragment
{
    TextView tvName_match, tvAgeGender_match, tvMobile_match;
    personalDataModel personalData;

    public matchDialog(personalDataModel personalData) {
        this.personalData = personalData;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.match_dialog, null);

        tvName_match = view.findViewById(R.id.tvName_match);
        tvAgeGender_match = view.findViewById(R.id.tvAgeGender_match);
        tvMobile_match = view.findViewById(R.id.tvMobile_match);

        tvName_match.setText(personalData.getName().toUpperCase());
        tvAgeGender_match.setText(String.format("%s, %s", personalData.getAge(), personalData.getGender()));
        tvMobile_match.setText(personalData.getNumber());

        builder.setView(view)
                .setTitle("Details :");

        return builder.create();
    }
}
