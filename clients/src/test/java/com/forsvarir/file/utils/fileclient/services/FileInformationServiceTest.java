package com.forsvarir.file.utils.fileclient.services;

import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class FileInformationServiceTest {
    FileSystem dummyFileSystem = Jimfs.newFileSystem(Configuration.unix());

    FileInformationService fileInformationService = new FileInformationService();

    @Test
    void toFileDetails_populatesClientId() throws IOException {
        var filePath = asPath("./someFile.txt");
        Files.createFile(filePath);

        var fileDetails = fileInformationService.toFileDetails(filePath, 99L);

        assertThat(fileDetails.getClientId()).isEqualTo(99L);
    }

    @Test
    void toFileDetails_populatesFileName() throws IOException {
        var filePath = asPath("/someFolder/someFile.txt");
        Files.createDirectory(asPath("/someFolder"));
        Files.createFile(filePath);

        var fileDetails = fileInformationService.toFileDetails(filePath, 0L);

        assertThat(fileDetails.getName()).isEqualTo("someFile.txt");
    }

    @Test
    void toFileDetails_populatesPath() throws IOException {
        var filePath = asPath("/someFolder/someFile.txt");
        Files.createDirectory(asPath("/someFolder"));
        Files.createFile(filePath);

        var fileDetails = fileInformationService.toFileDetails(filePath, 0L);

        assertThat(fileDetails.getPath()).isEqualTo("/someFolder");
    }

    @Test
    void toFileDetails_populatesSize() throws IOException {
        var filePath = asPath("/someFolder/someFile.txt");
        Files.createDirectory(asPath("/someFolder"));
        Files.createFile(filePath);
        Files.write(filePath, new byte[]{0x1, 0x2, 0x3, 0x4, 0x5});

        var fileDetails = fileInformationService.toFileDetails(filePath, 0L);

        assertThat(fileDetails.getSize()).isEqualTo(5);
    }

    @NotNull
    private Path asPath(String path) {
        return dummyFileSystem.getPath(path);
    }
}