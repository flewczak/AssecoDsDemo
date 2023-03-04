package org.example;

import java.io.IOException;
import java.io.InputStream;

public class TestUtils {
    static String loadFile(String name) throws IOException {
        try (InputStream resourceAsStream = TestUtils.class.getClassLoader().getResourceAsStream(name)) {
            byte[] bytes = resourceAsStream.readAllBytes();
            return new String(bytes);
        }
    }
}
