package com.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class FilesDemo {

    List<String> processInputStreamJava8Style() {
        URL resource = getClass().getResource("/file.csv");
        Objects.requireNonNull(resource, "file not found");
        try (BufferedReader is = new BufferedReader(
                new InputStreamReader(resource.openStream(), Charset.forName("UTF-8")))) {
            return is.lines()
                .skip(1)
                .map(l -> l.split(";"))
                .filter(a -> a.length > 1)
                .map(a -> a[1])
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
        } catch(IOException io) {
            throw new RuntimeException("Could not read file", io);
        }
    }

    public static void main(String[] args) {
        FilesDemo demo = new FilesDemo();
        List<String> list = demo.processInputStreamJava8Style();
        System.out.println(list);
    }
}
