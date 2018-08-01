package com.nokia.teachersupport.faculty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
//TODO global loading
@Component
public class LoadFaculties implements ApplicationListener<ApplicationReadyEvent> {
    private FacultyRepo facultyRepo;

    @Autowired
    public LoadFaculties(FacultyRepo facultyRepoo) {
        this.facultyRepo = facultyRepoo;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        List<String> umkFaculties=new ArrayList<>();

        umkFaculties.add("Wydział Biologii i Ochrony Środowiska");
        umkFaculties.add("Wydział Chemii");
        umkFaculties.add("Wydział Farmaceutyczny (Collegium Medicum w Bydgoszczy)");
        umkFaculties.add("Wydział Filologiczny");
        umkFaculties.add("Wydział Fizyki, Astronomii i Informatyki Stosowanej");
        umkFaculties.add("Wydział Humanistyczny");
        umkFaculties.add("Wydział Lekarski (Collegium Medicum w Bydgoszczy)");
        umkFaculties.add("Wydział Matematyki i Informatyki");
        umkFaculties.add("Wydział Nauk Ekonomicznych i Zarządzania");
        umkFaculties.add("Wydział Nauk Historycznych");
        umkFaculties.add("Wydział Nauk o Zdrowiu (Collegium Medicum w Bydgoszczy)");
        umkFaculties.add("Wydział Biologii i Ochrony Środowiska");
        umkFaculties.add("Wydział Nauk o Ziemi");
        umkFaculties.add("Wydział Nauk Pedagogicznych");
        umkFaculties.add("Wydział Politologii i Studiów Międzynarodowych");
        umkFaculties.add("Wydział Prawa i Administracji");
        umkFaculties.add("Wydział Sztuk Pięknych");
        umkFaculties.add("Wydział Teologiczny");


        for(int i=umkFaculties.size()-1;i>=0;i--)
        {
            if (facultyRepo.findByFacultyNameField(umkFaculties.get(i)) == null) {
                Faculty faculty=new Faculty();
                faculty.setFacultyNameField(umkFaculties.get(i));
                facultyRepo.save(faculty);
            }
        }


    }
}




