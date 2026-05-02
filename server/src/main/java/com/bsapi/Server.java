package com.bsapi;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Server {

    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
            server.createContext("/", new HomeHandler());
            server.createContext("/sort", new SortHandler());

            server.setExecutor(null);
            server.start();
            System.out.println("Server is running on port 8080");
        } catch (IOException e) {
            System.out.println("Error starting the server: " + e.getMessage());
        }
    }

    static class HomeHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "Hello";
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    public static int[] bubbleSort(int[] arr) {
        int lastSwap = arr.length - 1;
        for (int i = 1; i < arr.length; i++) {
            boolean is_sorted = true;
            int currentSwap = -1;
            for (int j = 0; j < lastSwap; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    is_sorted = false;
                    currentSwap = j;
                }
            }
            if (is_sorted) {
                continue;
            }
            lastSwap = currentSwap;
        }
        return arr;
    }

    static class SortHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                InputStream is = exchange.getRequestBody();
                String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);

                JSONObject obj = new JSONObject(body);
                JSONArray array = obj.getJSONArray("body");

                int[] intArray = new int[array.length()];

                for (int i = 0; i < array.length(); i++) {
                    intArray[i] = array.optInt(i);
                }

                int[] sortedArray = bubbleSort(intArray);

                JSONArray sortedJSON = new JSONArray().putAll(sortedArray);
                JSONObject sortedObject = new JSONObject().put("body", sortedJSON);

                String response = sortedObject.toString();
                exchange.sendResponseHeaders(200, response.length());

                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                exchange.sendResponseHeaders(405, -1);
            }
        }
    }
}
