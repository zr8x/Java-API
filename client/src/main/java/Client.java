import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;
import org.json.JSONArray;

public class Client {
    public static void main(String[] args) throws Exception {
        int[] array = { 2, 8, 1, 6, 9, 1, 5, 2 };

        JSONArray arrayUnordered = new JSONArray().putAll(array);
        JSONObject bodyObject = new JSONObject().put("body", arrayUnordered);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8080/sort"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(bodyObject.toString()))
            .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status code: " + response.statusCode());
        System.out.println("Response body: " + response.body());
    }
}
