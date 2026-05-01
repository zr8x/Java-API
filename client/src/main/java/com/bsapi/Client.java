package com.bsapi;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public class Client {
    public int[] SendPostArray(int[] array) throws Exception {
        JSONArray arrayUnordered = new JSONArray().putAll(array);
        JSONObject bodyObject = new JSONObject().put("body", arrayUnordered);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8080/sort"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(bodyObject.toString()))
            .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject responseObject = new JSONObject(response.body());
        JSONArray responseArray = new JSONArray(responseObject.get("body").toString());
        
        int[] intArray = new int[responseArray.length()];

        for (int i = 0; i < responseArray.length(); i++) {
            intArray[i] = responseArray.optInt(i);
        }

        return intArray;
    }
}
