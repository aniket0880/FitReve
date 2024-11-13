package com.uilover.project1932.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.uilover.project1932.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddCaloriesActivity extends AppCompatActivity {

    private EditText edtFood;
    private TextView txtNutritionDisplay;
    private ProgressBar progressBar;
    private apiinterface nutritionixAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_calories);

        // Initialize the views
        //edtFood = findViewById(R.id.);
        //txtNutritionDisplay = findViewById(R.id.txtNutritionDisplay);
        progressBar = findViewById(R.id.progressBar);

        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://developer.edamam.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        nutritionixAPI = retrofit.create(apiinterface.class);

        // Button to get nutrition data
        findViewById(R.id.progressBar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String foodItem = edtFood.getText().toString().trim();

                if (!foodItem.isEmpty()) {
                    fetchNutritionData(foodItem);
                } else {
                    Toast.makeText(AddCaloriesActivity.this, "Please enter a food item", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void fetchNutritionData(String foodItem) {
        String appId = "c210203f"; // Replace with your App ID
        String apiKey = "161a71bfd586b32f09b509aaa44b91e0"; // Replace with your API Key

        // Show the progress bar while loading
        progressBar.setVisibility(View.VISIBLE);
        txtNutritionDisplay.setText(""); // Clear the previous data

        // Fetching detailed nutrition data
        Call<ProteinCalorieResponse> call = nutritionixAPI.getNutritionData(
                foodItem, "nf_protein,nf_calories,nf_total_fat,nf_total_carbohydrate,nf_sugars,nf_dietary_fiber,nf_sodium", appId, apiKey);

        call.enqueue(new Callback<ProteinCalorieResponse>() {
            @Override
            public void onResponse(Call<ProteinCalorieResponse> call, Response<ProteinCalorieResponse> response) {
                // Hide the progress bar
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {
                    List<ProteinCalorieResponse.Hit> hits = response.body().getHits();
                    if (!hits.isEmpty()) {
                        ProteinCalorieResponse.Hit hit = hits.get(0);
                        ProteinCalorieResponse.Fields fields = hit.getFields();

                        String itemName = fields.getItem_name();
                        double protein = fields.getNf_protein();
                        double calories = fields.getNf_calories();
                        double fat = fields.getNf_total_fat();
                        double carbs = fields.getNf_total_carbohydrate();
                        double sugars = fields.getNf_sugars();
                        double fiber = fields.getNf_dietary_fiber();
                        double sodium = fields.getNf_sodium();

                        // Display the nutritional information
                        txtNutritionDisplay.setText(String.format(
                                "Food: %s\nCalories: %.2f kcal\nProtein: %.2f g\nFat: %.2f g\nCarbs: %.2f g\nSugars: %.2f g\nFiber: %.2f g\nSodium: %.2f mg",
                                itemName, calories, protein, fat, carbs, sugars, fiber, sodium));
                    } else {
                        txtNutritionDisplay.setText("No data found for the specified food item.");
                    }
                } else {
                    txtNutritionDisplay.setText("Error: " + response.message());
                    Toast.makeText(AddCaloriesActivity.this, "Failed to get data. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProteinCalorieResponse> call, Throwable t) {
                // Hide the progress bar and show error
                progressBar.setVisibility(View.GONE);
                txtNutritionDisplay.setText("Network request failed.");
                Toast.makeText(AddCaloriesActivity.this, "Network request failed. Check your connection.", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
}
