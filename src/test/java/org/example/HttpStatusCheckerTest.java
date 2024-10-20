package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HttpStatusCheckerTest {
    private final HttpStatusChecker checker = new HttpStatusChecker();

    @Test
    public void testGetStatusImage_validStatusCode() throws Exception {
        HttpStatusChecker checker = new HttpStatusChecker();
        String url = checker.getStatusImage(200);

        assertEquals("https://http.cat/200.jpg", url);
    }

    @Test
    public void testGetStatusImage_NotFoundCode() {
        Exception exception = assertThrows(Exception.class, () -> {
            checker.getStatusImage(404);
        });
        assertEquals("Image not found for status code: 404", exception.getMessage());
    }



    @Test
    public void testGetStatusImage_invalidStatusCode() {
        Exception exception = assertThrows(Exception.class, () -> {
            checker.getStatusImage(1000);
        });
        assertEquals("Invalid HTTP status code: 1000", exception.getMessage());
    }


}
