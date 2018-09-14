package com.nokia.teachersupport.fileUpload;

import com.nokia.teachersupport.fileUpload.FileModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;


@Transactional
public interface FileRepository extends CrudRepository<FileModel, Integer> {
}