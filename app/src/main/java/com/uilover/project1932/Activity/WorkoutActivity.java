package com.uilover.project1932.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;

import com.uilover.project1932.Adapter.LessionsAdapter;
import com.uilover.project1932.Domain.Workout;
import com.uilover.project1932.databinding.ActivityWorkoutBinding;


public class WorkoutActivity extends AppCompatActivity {
    ActivityWorkoutBinding binding;
    private Workout workout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWorkoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        getObject();
        setVariable();

    }

    private void getObject() {
        workout = (Workout) getIntent().getSerializableExtra("object");
    }

    private void setVariable() {
        int resId = getResources().getIdentifier(workout.getPicPath(), "drawable", getPackageName());
        Glide.with(this)
                .load(resId)
                .into(binding.pic);

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.titleTxt.setText(workout.getTitle());
        binding.excerciseTxt.setText(workout.getLessions().size() + " Exercise");
        binding.kcalTxt.setText(workout.getKcal() + " Kcal");
        binding.durationTxt.setText(workout.getDurationAll());
        binding.descriptionTxt.setText(workout.getDescription());

        binding.view3.setLayoutManager(new LinearLayoutManager(WorkoutActivity.this, LinearLayoutManager.VERTICAL, false));
        binding.view3.setAdapter(new LessionsAdapter(workout.getLessions()));
    }
}