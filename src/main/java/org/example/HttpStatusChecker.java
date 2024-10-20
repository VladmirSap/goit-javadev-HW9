package org.example;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class HttpStatusChecker {

    private final OkHttpClient client;

    public HttpStatusChecker() {
        this.client = new OkHttpClient();
    }

    public String getStatusImage(int code) throws Exception {
        if (code < 100 || code >= 600) {
            throw new Exception("Invalid HTTP status code: " + code);
        }

        // Тут обробляємо 404 статус-код до запиту
        if (code == 404) {
            throw new Exception("Image not found for status code: " + code);
        }

        String imageUrl = "https://http.cat/" + code + ".jpg";

        Request request = new Request.Builder()
                .url(imageUrl)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return imageUrl;
            } else {
                throw new Exception("Error fetching image for status code: " + code + ", Response code: " + response.code());
            }
        } catch (IOException e) {
            throw new Exception("Error during connection: " + e.getMessage(), e);
        }
    }
}




