package com.nokia.teachersupport.person;

import com.nokia.teachersupport.serviceProvider.IServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class IndexController {

    private IServiceProvider serviceProvider;

    @Autowired
    public IndexController(IServiceProvider serviceProvider) {
       this.serviceProvider=serviceProvider;
    }

    @GetMapping("/")
    String index(Model model) {
        serviceProvider.getIModelService().indexModel(model,serviceProvider);
        return "teacherSupportIndex";
    }

    @PostMapping("/index/confirmFaculty")
    String saveFaculty(@RequestParam("facultyName") String name) {
        serviceProvider.getIPersonService().goSaveMyFaculty(name, serviceProvider);
        return "teacherSupportIndex";
    }

    @GetMapping("/index/giveMePhoto")
    ResponseEntity<Object> giveFacultyPhoto() {
        List<String> pic = serviceProvider.getIPersonService().goGiveMeFacultyPhoto(serviceProvider);
        ServiceResponse<List<String>> response = new ServiceResponse<>("success", pic);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @GetMapping("/index/giveMeId")
    ResponseEntity<Object> giveFacultyId() {
        List<Integer> Id = serviceProvider.getIPersonService().goGiveMeFacultyId(serviceProvider);
        ServiceResponse<List<Integer>> response = new ServiceResponse<>("success", Id);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
}
