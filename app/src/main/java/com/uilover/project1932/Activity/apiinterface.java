package com.uilover.project1932.Activity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface apiinterface {
    @GET("api/nutrition-data")
    Call<ProteinCalorieResponse> getNutritionData(
            @Query("query") String foodItem, // Corrected the parameter name
            @Query("fields") String fields,
            @Header("x-app-id") String appId, // Corrected header for app ID
            @Header("x-app-key") String apiKey // Corrected header for API key
    );
}
