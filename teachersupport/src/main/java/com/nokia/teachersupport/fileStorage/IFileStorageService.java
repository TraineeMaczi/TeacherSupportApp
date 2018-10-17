package com.nokia.teachersupport.fileStorage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface IFileStorageService {
    void store(MultipartFile file);
    Resource loadFile(String filename);
    void deleteAll();
    void init();
    Stream<Path> loadFiles();
}