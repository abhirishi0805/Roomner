package com.example.roomner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class showMatches extends AppCompatActivity implements matchAdapter.itemClicked{

    ArrayList<matchHelperModel> people;

    FirebaseDatabase database;
    DatabaseReference preferencesReference;

    RecyclerView recyclerViewMatches;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_matches);

        getData();

        recyclerViewMatches = findViewById(R.id.recyclerViewMatches);
        recyclerViewMatches.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(showMatches.this, 2, GridLayoutManager.VERTICAL, false);
        recyclerViewMatches.setLayoutManager(layoutManager);

        adapter = new matchAdapter(this, people);
        recyclerViewMatches.setAdapter(adapter);
    }

    private void getData()
    {
        database = FirebaseDatabase.getInstance();
     //   personalDataReference = database.getReference().child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Personal Data");
        preferencesReference = database.getReference().child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Preferences");
        //weightSumReference = database.getReference().child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Weight Sum");

        people.clear();

        preferencesReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
    public void onItemClicked(int index) {

    }
}