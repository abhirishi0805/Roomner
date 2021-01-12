package com.example.roomner;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class feedbackDialog extends AppCompatDialogFragment
{
    EditText etFeedback;
    DatabaseReference reference;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        reference = FirebaseDatabase.getInstance().getReference("Feedbacks");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.feedback_dialog, null);

        etFeedback = (EditText) view.findViewById(R.id.etFeedback);

        builder.setView(view)
                .setTitle("Feedback")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Submit", (dialog, which) -> {
                    String feedback = etFeedback.getText().toString();
                    reference.push().setValue(feedback);
                    Toast.makeText(getContext(), "We appreciate your feedback", Toast.LENGTH_SHORT).show();
                });

        return builder.create();
    }
}
