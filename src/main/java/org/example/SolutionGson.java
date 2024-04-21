package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class ResultGson {
    public static int getVoteCount(String cityName, int estimatedCost) throws IOException {
        // Construct the API URL
        String apiUrl = "https://jsonmock.hackerrank.com/api/food_outlets?city=" + cityName +
                "&estimated_cost=" + estimatedCost;

        // Create URL object
        URL url = new URL(apiUrl);

        // Create HttpURLConnection
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Read response
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        // Parse JSON response
        Gson gson = new Gson();
        JsonObject jsonResponse = gson.fromJson(response.toString(), JsonObject.class);

        // Check if data array is empty
        JsonArray data = jsonResponse.getAsJsonArray("data");
        if (data.isEmpty()) {
            return -1; // No matching records found
        }

        // Calculate total votes
        int totalVotes = 0;
        for (JsonElement element : data) {
            JsonObject outlet = element.getAsJsonObject();
            JsonObject userRating = outlet.getAsJsonObject("user_rating");
            totalVotes += userRating.get("votes").getAsInt();
        }

        return totalVotes;
    }
}

public class SolutionGson {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // Read input from user
        String cityName = bufferedReader.readLine();
        int estimatedCost = Integer.parseInt(bufferedReader.readLine().trim());

        // Call getVoteCount method
        int result = ResultGson.getVoteCount(cityName, estimatedCost);

        // Output the result
        System.out.println(result);

        bufferedReader.close();
    }
}
