package org.TicketBooking.Service;

import org.asynchttpclient.*;
import org.asynchttpclient.util.HttpConstants;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TrainService {

    private static final String API_KEY = "e43c76a1cemsh3e333d5dfeb1ec8p1216cdjsn5cec368fa5f8"; // Replace with your actual API key
    private static final String API_URL = "https://indian-railways-train-fetcher.p.rapidapi.com/get_train_info?start=%s&destination=%s";

    private final ObjectMapper objectMapper = new ObjectMapper();

    // Fetch trains between source and destination
    public List<Train> searchTrains(String source, String destination) {
        List<Train> trains = new ArrayList<>();
        String url = String.format(API_URL, source, destination); // Create the API URL with provided source and destination

        try (AsyncHttpClient client = Dsl.asyncHttpClient()) {  // ✅ Auto-closing client
            // Prepare the API request
            ListenableFuture<Response> futureResponse = client.prepare(HttpConstants.Methods.GET, url)
                    .setHeader("x-rapidapi-key", API_KEY)
                    .setHeader("x-rapidapi-host", "indian-railways-train-fetcher.p.rapidapi.com")
                    .execute();

            // Wait for the response asynchronously
            Response response = futureResponse.get();

            // Check if the response is successful (status code 200)
            if (response.getStatusCode() == 200) {
                // Parse the JSON response
                JsonNode rootNode = objectMapper.readTree(response.getResponseBody());

                // Assuming the API response has a 'Trains' node that holds train data
                if (rootNode.has("Trains") && rootNode.get("Trains").isArray()) {
                    trains = objectMapper.readValue(rootNode.get("Trains").toString(),
                            objectMapper.getTypeFactory().constructCollectionType(List.class, Train.class));
                }
            } else {
                System.out.println("Error fetching train data: " + response.getResponseBody());
            }

        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Error making the API request: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error processing train data: " + e.getMessage());
        }

        return trains;
    }
}
