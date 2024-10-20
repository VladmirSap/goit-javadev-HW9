package org.example;

import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

class HttpStatusImageDownloaderTest {

    private static final int VALID_STATUS_CODE = 200;
    private static final int NOT_FOUND_STATUS_CODE = 404;
    private static final int INVALID_STATUS_CODE = 1000;

    private final HttpStatusImageDownloader downloader = new HttpStatusImageDownloader();

    @Test
    void testDownloadStatusImage_ValidCode() {
        assertDoesNotThrow(() -> {
            downloader.downloadStatusImage(VALID_STATUS_CODE);
        });

        File file = new File(VALID_STATUS_CODE + ".jpg");
        assertTrue(file.exists(), "The image file should exist.");

        // Clean up
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testDownloadStatusImage_InvalidCode() {
        Exception exception = assertThrows(Exception.class, () -> {
            downloader.downloadStatusImage(INVALID_STATUS_CODE);
        });

        assertEquals("Invalid HTTP status code: " + INVALID_STATUS_CODE, exception.getMessage());
    }

    @Test
    void testDownloadStatusImage_NoImage() {
        Exception exception = assertThrows(Exception.class, () -> {
            downloader.downloadStatusImage(NOT_FOUND_STATUS_CODE);
        });

        assertEquals("Image not found for status code: " + NOT_FOUND_STATUS_CODE, exception.getMessage());
    }
}




