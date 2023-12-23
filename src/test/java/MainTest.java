import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    @DisplayName("Main prints Hello world!")
    void mainPrintsHelloWorld() {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(stream));
        Main.main(new String[0]);
        String output = stream.toString().trim();
        assertEquals("Hello world!", output);
    }
}