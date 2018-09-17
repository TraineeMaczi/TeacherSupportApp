package com.nokia.teachersupport.generate;


import java.util.List;
import java.util.stream.Collectors;

import com.nokia.teachersupport.context.IContextService;
import com.nokia.teachersupport.filestorage.IFileStorage;
import com.nokia.teachersupport.serviceProvider.IServiceProvider;
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

private IServiceProvider serviceProvider;
    @Autowired
    public GenerateRestController(IServiceProvider serviceProvider) {
        this.serviceProvider=serviceProvider;
    }


    @PostMapping("/generate/listOfPages")
    public void generatePages(@RequestParam("listOfPages") String listOfPages) throws Exception {
        serviceProvider.getIFileStorage().deleteAll();
        serviceProvider.getIFileStorage().init();
        try {
            MultipartFile multipartFile = Generator.generate(listOfPages, serviceProvider);
            serviceProvider.getIFileStorage().store(multipartFile);
        } catch (Exception e) {

        }
    }
    @GetMapping("/generate/templates")
    public void generatePages() throws Exception {
        serviceProvider.getIFileStorage().deleteAll();
        serviceProvider.getIFileStorage().init();
        try {
            MultipartFile multipartFile = Generator.generateTemplates(serviceProvider);
            serviceProvider.getIFileStorage().store(multipartFile);
        } catch (Exception e) {

        }
    }
    @GetMapping("/generate/getListOfPages")
    public List<String> getListPages() {
        return serviceProvider.getIFileStorage().loadFiles().map(
                path -> MvcUriComponentsBuilder.fromMethodName(GenerateRestController.class,
                        "downloadFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList());
    }
    @GetMapping("/generate/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("filename") String filename) {
        Resource file =serviceProvider.getIFileStorage().loadFile(filename+".zip");
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
