package com.nokia.teachersupport.generate;


import java.util.List;
import java.util.stream.Collectors;

import com.nokia.teachersupport.context.IContextService;
import com.nokia.teachersupport.filestorage.IFileStorage;
import com.nokia.teachersupport.tools.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;


@RestController
public class GenerateRestController {
    IContextService contextService;
    IFileStorage fileStorage;

    @Autowired
    public GenerateRestController(IContextService contextService, IFileStorage fileStorage) {
        this.contextService = contextService;
        this.fileStorage = fileStorage;
    }


    @PostMapping("/generate/listOfPages")
    public void generatePages(@RequestParam("listOfPages") String listOfPages) throws Exception {
        fileStorage.deleteAll();
        fileStorage.init();
        try {
            MultipartFile multipartFile = Generator.generate(listOfPages, contextService);
            fileStorage.store(multipartFile);
        } catch (Exception e) {

        }
    }
    @GetMapping("/generate/getListOfPages")
    public List<String> getListPages() {
        return fileStorage.loadFiles().map(
                path -> MvcUriComponentsBuilder.fromMethodName(GenerateRestController.class,
                        "downloadFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList());
    }
    @GetMapping("/generate/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("filename") String filename) {
        Resource file = fileStorage.loadFile(filename+".zip");
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
