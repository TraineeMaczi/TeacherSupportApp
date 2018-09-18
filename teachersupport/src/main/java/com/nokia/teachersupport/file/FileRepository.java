package com.nokia.teachersupport.file;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface FileRepository extends CrudRepository<File, Integer> {
}