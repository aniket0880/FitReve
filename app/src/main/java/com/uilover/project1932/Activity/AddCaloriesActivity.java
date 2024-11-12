package com.uilover.project1932.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.uilover.project1932.R;

public class AddCaloriesActivity extends AppCompatActivity {

    private EditText edtCalories;
    private TextView txtCaloriesDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_calories);

        // Initialize the views
        edtCalories = findViewById(R.id.edtCalories);
        txtCaloriesDisplay = findViewById(R.id.txtCaloriesDisplay);  // TextView to display entered calories

        // Button to save calories
        findViewById(R.id.btnSaveCalories).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String calories = edtCalories.getText().toString().trim();

                if (!calories.isEmpty()) {
                    // Save the calories or do something with it
                    // Display the entered calories in the TextView
                    txtCaloriesDisplay.setText("Calories: " + calories);

                    // Optionally, show a toast to confirm
                    Toast.makeText(AddCaloriesActivity.this, "Calories Saved: " + calories, Toast.LENGTH_SHORT).show();

                    // Clear the EditText after saving
                    edtCalories.setText("");
                } else {
                    // If the user hasn't entered any value
                    Toast.makeText(AddCaloriesActivity.this, "Please enter calories", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
