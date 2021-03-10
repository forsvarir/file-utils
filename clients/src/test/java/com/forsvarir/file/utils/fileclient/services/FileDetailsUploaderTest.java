package com.forsvarir.file.utils.fileclient.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.file.Path;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FileDetailsUploaderTest {

    @Mock
    Function<Path, Stream<Path>> fileWalker;

    @InjectMocks
    private FileDetailsUploader uploader;

    @BeforeEach
    void beforeEach() {
        when(fileWalker.apply(any())).thenReturn(Stream.empty());
    }

    @Test
    void processFolder_walksSuppliedPath() {
        uploader.processFolder("someFolder");

        verify(fileWalker).apply(Path.of("someFolder"));
    }

    Stream<Path> fileSupplier(Path somePath) {
        return Stream.empty();
    }

}