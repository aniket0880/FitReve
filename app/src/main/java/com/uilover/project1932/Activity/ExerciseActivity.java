package com.uilover.project1932.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uilover.project1932.Domain.Exercise;
import com.uilover.project1932.R;

import java.util.ArrayList;
import java.util.List;

public class ExerciseActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ExerciseAdapter exerciseAdapter;
    private List<Exercise> exerciseList;
    private EditText exerciseNameEditText;
    private Button addExerciseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exerise); // Use your actual layout resource

        // Initialize views
        recyclerView = findViewById(R.id.exerciseRecyclerView);
        exerciseNameEditText = findViewById(R.id.exerciseNameEditText);
        addExerciseButton = findViewById(R.id.addExerciseButton);

        // Initialize exercise list with some default exercises
        exerciseList = new ArrayList<>();
        exerciseList.add(new Exercise("Push-ups"));
        exerciseList.add(new Exercise("Squats"));
        exerciseList.add(new Exercise("Lunges"));

        // Initialize the adapter and RecyclerView
        exerciseAdapter = new ExerciseAdapter(exerciseList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(exerciseAdapter);

        // Set up button click listener for adding exercises
        addExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the input from the EditText field
                String exerciseName = exerciseNameEditText.getText().toString().trim();

                // Check if input is not empty
                if (!exerciseName.isEmpty()) {
                    // Create new Exercise and add it to the list
                    Exercise newExercise = new Exercise(exerciseName);
                    exerciseList.add(newExercise);

                    // Notify the adapter about the new item
                    exerciseAdapter.notifyItemInserted(exerciseList.size() - 1);

                    // Clear the input field
                    exerciseNameEditText.setText("");
                }
            }
        });
    }
}
