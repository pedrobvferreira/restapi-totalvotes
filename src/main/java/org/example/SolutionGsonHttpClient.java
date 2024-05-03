package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class ResultGsonHttpClient {
    public static int getVoteCount(String cityName, int estimatedCost) throws IOException, URISyntaxException, InterruptedException {
        // Construct the API URL
        String apiUrl = "https://jsonmock.hackerrank.com/api/food_outlets?city=" + cityName +
                "&estimated_cost=" + estimatedCost;

        // Create an instance of HttpClient
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(apiUrl))
                .build();

        // Execute the request
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Check if the response code is OK
        if (response.statusCode() == 200) {
            // Parse the JSON response
            Gson gson = new Gson();
            JsonObject jsonResponse = gson.fromJson(response.body(), JsonObject.class);
            JsonArray data = jsonResponse.getAsJsonArray("data");

            // If no matching records found, return -1
            if (data.isEmpty()) {
                return -1;
            }

            // Calculate the total votes
            int totalVotes = 0;
            for (JsonElement element : data) {
                JsonObject outlet = element.getAsJsonObject();
                JsonObject userRating = outlet.getAsJsonObject("user_rating");
                totalVotes += userRating.get("votes").getAsInt();
            }

            return totalVotes;
        } else {
            // If the response code is not OK, return -1
            return -1;
        }
    }
}

public class SolutionGsonHttpClient {
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // Read input from user
        String cityName = bufferedReader.readLine();
        int estimatedCost = Integer.parseInt(bufferedReader.readLine().trim());

        // Call getVoteCount method
        int result = ResultGsonHttpClient.getVoteCount(cityName, estimatedCost);

        // Output the result
        System.out.println(result);

        bufferedReader.close();
    }
}
