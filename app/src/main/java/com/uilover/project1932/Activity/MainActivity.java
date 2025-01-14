package com.uilover.project1932.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uilover.project1932.Adapter.WorkoutAdapter;
import com.uilover.project1932.Domain.Lession;
import com.uilover.project1932.Domain.Workout;
import com.uilover.project1932.R;
import com.uilover.project1932.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FirebaseAuth auth;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize Firebase Auth and Database
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();

        // Check if the user is logged in
        if (auth.getCurrentUser() != null) {
            String userId = auth.getCurrentUser().getUid();
            database.child("user").child(userId).get().addOnSuccessListener(snapshot -> {
                if (snapshot.exists()) {
                    String userName = snapshot.child("userName").getValue(String.class);
                    if (userName != null) {
                        binding.userNameTextView.setText(userName);  // Use View Binding
                    }
                }
            }).addOnFailureListener(e -> {
                Toast.makeText(MainActivity.this, "Failed to load user data", Toast.LENGTH_SHORT).show();
            });
        } else {
            binding.userNameTextView.setText("Guest User");  // Set a default name if not logged in
        }

        // Set up RecyclerView
        binding.view1.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        binding.view1.setAdapter(new WorkoutAdapter(getData()));
    }

    // Click event for Daily Calories
    public void onDailyCaloriesClick(View view) {
        Intent intent = new Intent(MainActivity.this, AddCaloriesActivity.class);
        startActivity(intent);
    }

    // Click event for Health Track
    public void onHealthTrackClick(View view) {
        Intent intent = new Intent(MainActivity.this, healthtrack_Activity.class);
        startActivity(intent);
    }

    // Click event for AI Chatbot
    public void onChatbotClick(View view) {
        Intent intent = new Intent(MainActivity.this, Ai_Page.class);
        startActivity(intent);
    }

    // click event for profile page
    public void onProfileClick(View view){
        Intent intent =new Intent(MainActivity.this, Profile_Page.class);
        startActivity(intent);
    }

    public void onWorkoutClick(View view){
        Intent intent=new Intent(MainActivity.this, ExerciseActivity.class);
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
