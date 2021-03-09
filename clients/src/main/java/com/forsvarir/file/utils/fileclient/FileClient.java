package com.forsvarir.file.utils.fileclient;

import com.forsvarir.common.utils.file.FileWalker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Path;

@SpringBootApplication
public class FileClient implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(FileClient.class, args);
    }

    @Override
    public void run(String... args) {
        FileWalker.walk(Path.of("/tmp"))
                .forEach(f->System.out.println(f.toString()));
    }
}
