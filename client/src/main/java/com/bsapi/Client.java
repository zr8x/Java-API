package com.bsapi;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public class Client {
    public int[] SendPostArray(int[] array) throws Exception {
        JSONObject bodyObject = ArrayToJSON(array);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8080/sort"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(bodyObject.toString()))
            .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject responseObject = new JSONObject(response.body());
        JSONArray responseArray = new JSONArray(responseObject.get("body").toString());
        
        int[] intArray = JSONToArray(responseArray);

        return intArray;
    }

    public JSONObject ArrayToJSON(int[] array) {
        JSONArray jsonArray = new JSONArray().putAll(array);
        JSONObject jsonObject = new JSONObject().put("body", jsonArray);
        return jsonObject;
    }

    public int[] JSONToArray(JSONArray jsonArray) {
        int[] intArray = new int[jsonArray.length()];

        for (int i = 0; i < jsonArray.length(); i++) {
            intArray[i] = jsonArray.optInt(i);
        }
        return intArray;
    }
}
