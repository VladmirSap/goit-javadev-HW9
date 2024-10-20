package org.example;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class HttpStatusImageDownloader {

    private final HttpStatusChecker statusChecker;

    public HttpStatusImageDownloader() {
        this.statusChecker = new HttpStatusChecker();
    }

    public void downloadStatusImage(int code) throws Exception {
        String imageUrl = statusChecker.getStatusImage(code);
        HttpURLConnection connection = null;
        try {
            URI uri = new URI(imageUrl);
            URL url = uri.toURL();
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException("Failed to download image, HTTP response code: " + connection.getResponseCode());
            }

            try (InputStream in = new BufferedInputStream(connection.getInputStream());
                 FileOutputStream out = new FileOutputStream(code + ".jpg")) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }
        } catch (IOException e) {
            throw new IOException("Error downloading image: " + e.getMessage(), e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}




