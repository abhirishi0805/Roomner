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

    userModel me, other;

    FirebaseDatabase database;
    DatabaseReference reference;

    RecyclerView recyclerViewMatches;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_matches);

        people = getData();

        recyclerViewMatches = findViewById(R.id.recyclerViewMatches);
        recyclerViewMatches.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(showMatches.this, 2, GridLayoutManager.VERTICAL, false);
        recyclerViewMatches.setLayoutManager(layoutManager);

        adapter = new matchAdapter(this, people);
        recyclerViewMatches.setAdapter(adapter);
    }

    private ArrayList<matchHelperModel> getData()
    {
        ArrayList<matchHelperModel> list = new ArrayList<matchHelperModel>();
        list.clear();

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                me = snapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).getValue(userModel.class);

                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    other = dataSnapshot.getValue(userModel.class);

                    if((!other.getPersonal_Data().getUid().equals(me.getPersonal_Data().getUid()))
                            && (other.getPersonal_Data().getCity().equals(me.getPersonal_Data().getCity())))
                    {
                        int percentageMatch = calculateMatch(me, other);
                        list.add(new matchHelperModel(other, percentageMatch));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return list;
    }

    @Override
    public void onItemClicked(int index) {

    }

    public int calculateMatch(userModel me, userModel other)
    {
        int percentageMatch;
        return 0;
    }
}