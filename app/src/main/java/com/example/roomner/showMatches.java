package com.example.roomner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;

public class showMatches extends AppCompatActivity implements matchAdapter.itemClicked{

    ProgressBar progressBar3;
    TextView tvLoading;

    ArrayList<matchHelperModel> people;

    userModel me, other;
    DatabaseReference reference;

    RecyclerView recyclerViewMatches;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_matches);

        progressBar3 = (ProgressBar) findViewById(R.id.progressBar3);
        tvLoading = (TextView) findViewById(R.id.tvLoading);

        people = new ArrayList<>();

        recyclerViewMatches = findViewById(R.id.recyclerViewMatches);
        recyclerViewMatches.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(showMatches.this, 2, GridLayoutManager.VERTICAL, false);
        recyclerViewMatches.setLayoutManager(layoutManager);

        setData();
    }

    private void setData()
    {
        people.clear();

        reference = FirebaseDatabase.getInstance().getReference();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                me = snapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).getValue(userModel.class);

                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    if(!dataSnapshot.getKey().equals("Feedbacks")) {
                        other = dataSnapshot.getValue(userModel.class);

                        if ((!other.getPersonal_Data().getUid().equals(me.getPersonal_Data().getUid()))
                                && (other.getPersonal_Data().getCity().equals(me.getPersonal_Data().getCity()))) {
                            int percentageMatch = calculateMatch(me, other);
                            people.add(new matchHelperModel(other, percentageMatch));
                        }
                    }
                }

                Collections.sort(people, new sortingClass());

                adapter = new matchAdapter(showMatches.this, people);
                recyclerViewMatches.setAdapter(adapter);

                tvLoading.setVisibility(View.GONE);
                progressBar3.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onItemClicked(int index) {

    }

    public int calculateMatch(userModel me, userModel other)
    {
        double percentageMatch = 0.0;

        percentageMatch += ((double)(2-Math.abs(me.getPreferences().getQuestion_1().getChoice() - other.getPreferences().getQuestion_1().getChoice()))/2)
                * ((double)me.getPreferences().getQuestion_1().getImportance()/me.getPreferences().getWeight_sum()) * 100;

        percentageMatch += ((double)(2-Math.abs(me.getPreferences().getQuestion_2().getChoice() - other.getPreferences().getQuestion_2().getChoice()))/2)
                * ((double)me.getPreferences().getQuestion_2().getImportance()/me.getPreferences().getWeight_sum()) * 100;

        percentageMatch += (((double)2-Math.abs(me.getPreferences().getQuestion_3().getChoice() - other.getPreferences().getQuestion_3().getChoice()))/2)
                * ((double)me.getPreferences().getQuestion_3().getImportance()/me.getPreferences().getWeight_sum()) * 100;

        percentageMatch += ((double)(2-Math.abs(me.getPreferences().getQuestion_4().getChoice() - other.getPreferences().getQuestion_4().getChoice()))/2)
                * ((double)me.getPreferences().getQuestion_4().getImportance()/me.getPreferences().getWeight_sum()) * 100;

        percentageMatch += ((double)(2-Math.abs(me.getPreferences().getQuestion_5().getChoice() - other.getPreferences().getQuestion_5().getChoice()))/2)
                * ((double)me.getPreferences().getQuestion_5().getImportance()/me.getPreferences().getWeight_sum()) * 100;

        percentageMatch += ((double)(2-Math.abs(me.getPreferences().getQuestion_6().getChoice() - other.getPreferences().getQuestion_6().getChoice()))/2)
                * ((double)me.getPreferences().getQuestion_6().getImportance()/me.getPreferences().getWeight_sum()) * 100;

        percentageMatch += ((double)(2-Math.abs(me.getPreferences().getQuestion_7().getChoice() - other.getPreferences().getQuestion_7().getChoice()))/2)
                * ((double)me.getPreferences().getQuestion_7().getImportance()/me.getPreferences().getWeight_sum()) * 100;

        percentageMatch += ((double)(2-Math.abs(me.getPreferences().getQuestion_8().getChoice() - other.getPreferences().getQuestion_8().getChoice()))/2)
                * ((double)me.getPreferences().getQuestion_8().getImportance()/me.getPreferences().getWeight_sum()) * 100;

        percentageMatch += ((double)(2-Math.abs(me.getPreferences().getQuestion_9().getChoice() - other.getPreferences().getQuestion_9().getChoice()))/2)
                * ((double)me.getPreferences().getQuestion_9().getImportance()/me.getPreferences().getWeight_sum()) * 100;

        percentageMatch += ((double)(2-Math.abs(me.getPreferences().getQuestion_10().getChoice() - other.getPreferences().getQuestion_10().getChoice()))/2)
                * ((double)me.getPreferences().getQuestion_10().getImportance()/me.getPreferences().getWeight_sum()) * 100;

        return (int)percentageMatch;
    }
}