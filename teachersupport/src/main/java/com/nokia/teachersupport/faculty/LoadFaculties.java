package com.nokia.teachersupport.faculty;

import com.nokia.teachersupport.fileUpload.FileModel;
import com.nokia.teachersupport.fileUpload.IFileService;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.personSecurity.UserSecurityData;
import com.nokia.teachersupport.roles.SecutityRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Component
public class LoadFaculties implements ApplicationListener<ApplicationReadyEvent> {
    private FacultyRepo facultyRepo;
    private IFileService fileService;

    @Autowired
    public LoadFaculties(FacultyRepo facultyRepo, IFileService fileService) {
        this.facultyRepo = facultyRepo;
        this.fileService = fileService;
    }


    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent)  {
        List<String> umkFaculties = new ArrayList<>();

        umkFaculties.add("Wydzial Biologii i Ochrony Srodowiska");
        umkFaculties.add("Wydzial Chemii");
        umkFaculties.add("Wydzial Farmaceutyczny (Collegium Medicum w Bydgoszczy)");
        umkFaculties.add("Wydzial Filologiczny");
        umkFaculties.add("Wydzial Fizyki, Astronomii i Informatyki Stosowanej");
        umkFaculties.add("Wydzial Humanistyczny");
        umkFaculties.add("Wydzial Lekarski (Collegium Medicum w Bydgoszczy)");
        umkFaculties.add("Wydzial Matematyki i Informatyki");
        umkFaculties.add("Wydzial Nauk Ekonomicznych i Zarzadzania");
        umkFaculties.add("Wydzial Nauk Historycznych");
        umkFaculties.add("Wydzial Nauk o Zdrowiu (Collegium Medicum w Bydgoszczy)");
        umkFaculties.add("Wydzial Biologii i Ochrony Srodowiska");
        umkFaculties.add("Wydzial Nauk o Ziemi");
        umkFaculties.add("Wydzial Nauk Pedagogicznych");
        umkFaculties.add("Wydzial Politologii i Studiow Miedzynarodowych");
        umkFaculties.add("Wydzial Prawa i Administracji");
        umkFaculties.add("Wydzial Sztuk Pieknych");
        umkFaculties.add("Wydzial Teologiczny");
        for (int i = umkFaculties.size() - 1; i >= 0; i--) {
            if (facultyRepo.findByFacultyNameField(umkFaculties.get(i)) == null) {
                File file = new File("src\\main\\resources\\static\\images\\logo.jpg");
                Faculty faculty = new Faculty();
                faculty.setFacultyNameField(umkFaculties.get(i));
                FileModel fileModel = new FileModel();
                fileModel.setName(umkFaculties.get(i));
                try {
                    fileModel.setPic(Files.readAllBytes(file.toPath()));
                }catch(IOException e){
                    System.out.println("Cos nie dziala");
                }
                fileModel.setType("facultyFoto");
                fileService.saveFile(fileModel);
                facultyRepo.save(faculty);
                faculty.setFile(fileService.findFileById(fileModel.getId()));
                facultyRepo.save(faculty);

            }
        }


    }
}




