package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

class ResultJackson {
    public static int getVoteCount(String cityName, int estimatedCost) throws IOException {
        // Construct the API URL
        String apiUrl = "https://jsonmock.hackerrank.com/api/food_outlets?city=" + cityName + "&estimated_cost=" + estimatedCost;

        // Make HTTP GET request
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Read the response
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        // Parse JSON response
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(response.toString());

        // Check if data field is empty
        JsonNode dataNode = rootNode.get("data");
        if (dataNode.isEmpty()) {
            return -1; // No matching outlets found
        }

        // Calculate sum of votes
        int totalVotes = 0;
        for (JsonNode outletNode : dataNode) {
            totalVotes += outletNode.get("user_rating").get("votes").asInt();
        }

        return totalVotes;
    }
}

public class SolutionJackson {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // Read input from user
        String cityName = bufferedReader.readLine();
        int estimatedCost = Integer.parseInt(bufferedReader.readLine().trim());

        // Call getVoteCount method
        int result = ResultJackson.getVoteCount(cityName, estimatedCost);

        // Output the result
        System.out.println(result);

        bufferedReader.close();
    }
}
