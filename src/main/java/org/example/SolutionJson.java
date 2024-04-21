package org.example;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

class ResultJson {
    public static int getVoteCount(String cityName, int estimatedCost) throws IOException {
        // Construct the URL
        String urlString = "https://jsonmock.hackerrank.com/api/food_outlets?city=" + cityName +
                "&estimated_cost=" + estimatedCost;

        // Create URL object
        URL url = new URL(urlString);

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
        JSONObject jsonResponse = new JSONObject(response.toString());

        // Check if data array is empty
        JSONArray data = (JSONArray) jsonResponse.get("data");
        if (data.isEmpty()) {
            return -1; // No matching records found
        }

        // Calculate total votes
        int totalVotes = 0;
        for (int i = 0; i < data.length(); i++) {
            JSONObject outlet = data.getJSONObject(i);
            JSONObject userRating = outlet.getJSONObject("user_rating");
            totalVotes += userRating.getInt("votes");
        }

        return totalVotes;
    }
}

public class SolutionJson {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // Read input from user
        String cityName = bufferedReader.readLine();
        int estimatedCost = Integer.parseInt(bufferedReader.readLine().trim());

        // Call getVoteCount method
        int result = ResultJson.getVoteCount(cityName, estimatedCost);

        // Output the result
        System.out.println(result);

        bufferedReader.close();
    }
}
