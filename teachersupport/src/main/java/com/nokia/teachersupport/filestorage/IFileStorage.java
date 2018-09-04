package com.nokia.teachersupport.filestorage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface IFileStorage {
    void store(MultipartFile file);
    Resource loadFile(String filename);
    void deleteAll();
    void init();
    Stream<Path> loadFiles();
}