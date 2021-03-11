package com.forsvarir.file.utils.fileclient;

import com.forsvarir.file.utils.fileclient.services.FileDetailsUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class FileWalkerApplication implements CommandLineRunner {
    public static final String DEFAULT_SEARCH_FOLDER = "/tmp";

    @Autowired
    FileDetailsUploader fileDetailsUploader;

    public static void main(String[] args) {
        new SpringApplicationBuilder(FileWalkerApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

    @Override
    public void run(String... args) {
        String pathToSearch = args.length > 0 ? args[0] : DEFAULT_SEARCH_FOLDER;
        fileDetailsUploader.processFolder(pathToSearch);
    }
}
