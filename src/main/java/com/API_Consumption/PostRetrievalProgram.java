package com.API_Consumption;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class PostRetrievalProgram {

    public static void main(String[] args) {
        // Prompt the user for a user ID
        System.out.print("Enter User ID: ");
        Scanner scanner = new Scanner(System.in);
        int userId = scanner.nextInt();

        try {
            // Construct the API URL
            URL url = new URL("https://jsonplaceholder.typicode.com/posts?userId=" + userId);

            // Create an HTTP connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Check for a successful response code
            if (conn.getResponseCode() == 200) {
                // Read the response
                JSONArray postsArray = getJsonArray(conn);

                // Display the posts
                for (int i = 0; i < postsArray.length(); i++) {
                    JSONObject post = postsArray.getJSONObject(i);
                    System.out.println("Title: " + post.getString("title"));
                    System.out.println("Body: " + post.getString("body"));
                    System.out.println();
                }
            } else {
                System.out.println("Error: Unable to retrieve posts.");
            }

            // Close the connection
            conn.disconnect();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static JSONArray getJsonArray(HttpURLConnection conn) throws IOException, JSONException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        // Parse the JSON response
        JSONArray postsArray = new JSONArray(response.toString());
        return postsArray;
    }
}
