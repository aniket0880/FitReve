package com.uilover.project1932.Activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.uilover.project1932.R;
import com.uilover.project1932.callback.stepsCallback;
import com.uilover.project1932.helper.GeneralHelper;
import com.uilover.project1932.service.StepDetectorService;

import android.content.Intent;


public class healthtrack_Activity extends AppCompatActivity implements stepsCallback {

    private TextView TV_STEPS;
    private TextView TV_CALORIES;
    private TextView TV_DISTANCE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heathtrack);

        // Initialize the TextViews
      TV_STEPS = findViewById(R.id.TV_STEPS);
      TV_CALORIES = findViewById(R.id.TV_CALORIES);
      TV_DISTANCE = findViewById(R.id.TV_DISTANCE);

        // Start the StepDetectorService
        Intent intent = new Intent(this, StepDetectorService.class);
        startService(intent);

        // Register the callback with StepDetectorService
        StepDetectorService.Subscribe.register(this);
    }

    @Override
    public void subscribeSteps(int steps) {
        TV_STEPS.setText(String.valueOf(steps));
        TV_CALORIES.setText(GeneralHelper.getCalories(steps));
        TV_DISTANCE.setText(GeneralHelper.getDistanceCovered(steps));
    }
}

