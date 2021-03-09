package com.forsvarir.file.utils.fileclient;

import com.forsvarir.common.utils.file.FileWalker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.nio.file.Path;

@SpringBootApplication
public class FileClient implements CommandLineRunner {
    public static void main(String[] args) {
        new SpringApplicationBuilder(FileClient.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

    @Override
    public void run(String... args) {
        FileWalker.walk(Path.of("/tmp"))
                .forEach(f -> System.out.println(f.toString()));
    }
}
