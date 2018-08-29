package com.nokia.teachersupport.filestorage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface IFileStorage {
	public void store(MultipartFile file);
	public Resource loadFile(String filename);
	public void deleteAll();
	public void init();
	public Stream<Path> loadFiles();
}