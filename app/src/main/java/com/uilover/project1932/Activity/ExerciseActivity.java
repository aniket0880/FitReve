package com.uilover.project1932.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uilover.project1932.Domain.Exercise;
import com.uilover.project1932.R;

import java.util.ArrayList;
import java.util.List;

public class ExerciseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exerise); // Update with your activity layout

        RecyclerView recyclerView = findViewById(R.id.exerciseRecyclerView); // Update with your RecyclerView ID
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Exercise> exerciseList = new ArrayList<>();
        exerciseList.add(new Exercise("Push-ups"));
        exerciseList.add(new Exercise("Squats"));
        exerciseList.add(new Exercise("Lunges"));

        ExerciseAdapter adapter = new ExerciseAdapter(exerciseList);
        recyclerView.setAdapter(adapter);
    }
}