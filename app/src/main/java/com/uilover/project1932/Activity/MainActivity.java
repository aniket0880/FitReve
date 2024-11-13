package com.uilover.project1932.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.uilover.project1932.Adapter.WorkoutAdapter;
import com.uilover.project1932.Domain.Lession;
import com.uilover.project1932.Domain.Workout;
import com.uilover.project1932.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.view1.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        binding.view1.setAdapter(new WorkoutAdapter(getData()));
    }

    // Click event for Daily Calories
    public void onDailyCaloriesClick(View view) {
        // Start AddCaloriesActivity when "Daily Calories" is clicked
        Intent intent = new Intent(MainActivity.this, AddCaloriesActivity.class);
        startActivity(intent);
    }

    // click event for health track
    public void onHeathTrackClick(View view){
        Intent intent=new Intent(MainActivity.this, healthtrack_Activity.class);
        startActivity(intent);
    }

    private ArrayList<Workout> getData() {
        ArrayList<Workout> list = new ArrayList<>();
        list.add(new Workout("Running", "You just woke up...", "pic_1", 160, "9 min", getLession_1()));
        list.add(new Workout("Stretching", "You just woke up...", "pic_2", 230, "85 min", getLession_2()));
        list.add(new Workout("Yoga", "You just woke up...", "pic_3", 180, "65 min", getLession_3()));
        return list;
    }

    private ArrayList<Lession> getLession_1() {
        ArrayList<Lession> list = new ArrayList<>();
        list.add(new Lession("Lesson 1", "03:46 ", "HBPMvFkpNgE", "pic_1_1"));
        list.add(new Lession("Lesson 2", "03:41 ", "K6I24WgiiPw", "pic_1_2"));
        list.add(new Lession("Lesson 3", "01:57 ", "Zc08v4YYOeA", "pic_1_3"));
        return list;
    }

    private ArrayList<Lession> getLession_2() {
        ArrayList<Lession> list = new ArrayList<>();
        list.add(new Lession("Lesson 1", "20:23 ", "L3eImBAXT7I", "pic_3_1"));
        list.add(new Lession("Lesson 2", "18:27 ", "47ExgzO7FlU", "pic_3_2"));
        list.add(new Lession("Lesson 3", "32:25 ", "OmLx8tmaQ-4", "pic_3_3"));
        list.add(new Lession("Lesson 4", "07:52 ", "w86EalEoFRY", "pic_3_4"));
        return list;
    }

    private ArrayList<Lession> getLession_3() {
        ArrayList<Lession> list = new ArrayList<>();
        list.add(new Lession("Lesson 1", "23:00 ", "v7AYKMP6rOE", "pic_3_1"));
        list.add(new Lession("Lesson 2", "27:00 ", "Eml2xnoLpYE", "pic_3_2"));
        list.add(new Lession("Lesson 3", "25:00 ", "v7SN-d4qXx0", "pic_3_3"));
        list.add(new Lession("Lesson 4", "21:00 ", "LqXZ628YNj4", "pic_3_4"));
        return list;
    }
}
