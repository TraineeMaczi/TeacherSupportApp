package com.nokia.teachersupport.resource.repository;

import com.nokia.teachersupport.resource.model.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface FileRepository extends JpaRepository<FileModel, Long> {

}