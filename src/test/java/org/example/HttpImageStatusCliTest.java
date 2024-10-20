package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class HttpImageStatusCliTest {

    private HttpImageStatusCli cli;

    @BeforeEach
    public void setUp() {
        cli = new HttpImageStatusCli();
    }

    @Test
    public void testValidStatusCode() {
        // Arrange
        String input = "200\nexit\n"; // Simulate user input
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Act
        cli.askStatus();

       }

    @Test
    public void testInvalidStatusCode() {
        // Arrange
        String input = "abc\nexit\n"; // Simulate user input
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Act
        cli.askStatus();

    }

    @Test
    public void testNoImageForStatus() {
        // Arrange
        String input = "404\nexit\n"; // Simulate user input
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Act
        cli.askStatus();


    }
}





