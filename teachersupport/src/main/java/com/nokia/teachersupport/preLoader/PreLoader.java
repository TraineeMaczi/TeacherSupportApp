package com.nokia.teachersupport.preLoader;

import com.nokia.teachersupport.faculty.Faculty;
import com.nokia.teachersupport.faculty.FacultyRepo;
import com.nokia.teachersupport.file.File;
import com.nokia.teachersupport.file.IFileService;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.person.PersonRepo;
import com.nokia.teachersupport.personSecurity.UserSecurityData;
import com.nokia.teachersupport.personSecurity.UserSecurityDataRepo;
import com.nokia.teachersupport.personSecurity.personRegister.verificationToken.TokenRepo;
import com.nokia.teachersupport.personSecurity.personRegister.verificationToken.VerificationToken;
import com.nokia.teachersupport.roles.RoleRepo;
import com.nokia.teachersupport.roles.SecurityRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class PreLoader implements ApplicationListener<ApplicationReadyEvent> {
    private FacultyRepo facultyRepo;
    private IFileService fileService;
    private RoleRepo roleRepo;
    private TokenRepo tokenRepo;
    private PersonRepo personRepo;
    private UserSecurityDataRepo userSecurityDataRepo;


    @Autowired
    public PreLoader(PersonRepo personRepo, TokenRepo tokenRepo, FacultyRepo facultyRepo, IFileService fileService, RoleRepo roleRepo, UserSecurityDataRepo userSecurityDataRepo) {
        this.facultyRepo = facultyRepo;
        this.fileService = fileService;
        this.roleRepo = roleRepo;
        this.tokenRepo = tokenRepo;
        this.userSecurityDataRepo = userSecurityDataRepo;
        this.personRepo = personRepo;
    }


    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        PreLoadRoles();
        PreLoadFaculties();
        CleanVerificationTokens();
        LoadTswaPrimeAdmin();
        LoadSampleUser();
    }


    private void PreLoadRoles() {
        if (roleRepo.findByRoleName("ADMIN") == null) {
            SecurityRole admin = new SecurityRole();
            admin.setRoleName("ADMIN");
            roleRepo.save(admin);
        }

        if (roleRepo.findByRoleName("USER") == null) {
            SecurityRole user = new SecurityRole();
            user.setRoleName("USER");
            roleRepo.save(user);
        }
    }

    private void CleanVerificationTokens() {
        if (tokenRepo.findAll() != null) {
            Calendar cal = Calendar.getInstance();
            List<VerificationToken> currentTokens = tokenRepo.findAll();
            for (VerificationToken token : currentTokens) {
                if ((token.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
                    tokenRepo.delete(tokenRepo.findByToken(token.getToken()));
                }
            }

        }
    }

    private void PreLoadFaculties() {
        List<PreFacultyObj> umkFaculties = new ArrayList<>();

        umkFaculties.add(new PreFacultyObj("Wydzial Biologii i Ochrony Srodowiska",new java.io.File("src\\main\\resources\\static\\images\\WBiologiiiOchronySrodowiska.jpg")));
        umkFaculties.add(new PreFacultyObj("Wydzial Chemii",new java.io.File("src\\main\\resources\\static\\images\\WChemii.jpg")));
        umkFaculties.add(new PreFacultyObj("Wydzial Farmaceutyczny (Collegium Medicum w Bydgoszczy)",new java.io.File("src\\main\\resources\\static\\images\\WFarmaceutyczny.jpg")));
        umkFaculties.add(new PreFacultyObj("Wydzial Filologiczny",new java.io.File("src\\main\\resources\\static\\images\\WFilologiczny.jpg")));
        umkFaculties.add(new PreFacultyObj("Wydzial Fizyki, Astronomii i Informatyki Stosowanej",new java.io.File("src\\main\\resources\\static\\images\\WFizykiAstronomii iInformatykiStosowanej.jpg")));
        umkFaculties.add(new PreFacultyObj("Wydzial Humanistyczny",new java.io.File("src\\main\\resources\\static\\images\\WHumanistyczny.jpg")));
        umkFaculties.add(new PreFacultyObj("Wydzial Lekarski (Collegium Medicum w Bydgoszczy)",new java.io.File("src\\main\\resources\\static\\images\\WLekarski.jpg")));
        umkFaculties.add(new PreFacultyObj("Wydzial Matematyki i Informatyki",new java.io.File("src\\main\\resources\\static\\images\\WMatematykiiInformatyki.jpg")));
        umkFaculties.add(new PreFacultyObj("Wydzial Nauk Ekonomicznych i Zarzadzania",new java.io.File("src\\main\\resources\\static\\images\\WNaukEkonomicznychiZarzadzania.jpg")));
        umkFaculties.add(new PreFacultyObj("Wydzial Nauk Historycznych",new java.io.File("src\\main\\resources\\static\\images\\WNaukHistorycznych.jpg")));
        umkFaculties.add(new PreFacultyObj("Wydzial Nauk o Zdrowiu (Collegium Medicum w Bydgoszczy)",new java.io.File("src\\main\\resources\\static\\images\\WNaukoZdrowiu.jpg")));
        umkFaculties.add(new PreFacultyObj("Wydzial Nauk o Ziemi",new java.io.File("src\\main\\resources\\static\\images\\WNauk o Ziemi.jpg")));
        umkFaculties.add(new PreFacultyObj("Wydzial Nauk Pedagogicznych",new java.io.File("src\\main\\resources\\static\\images\\WNaukPedagogicznych.jpg")));
        umkFaculties.add(new PreFacultyObj("Wydzial Politologii i Studiow Miedzynarodowych",new java.io.File("src\\main\\resources\\static\\images\\WPolitologiiiStudiowMiedzynarodowych.jpg")));
        umkFaculties.add(new PreFacultyObj("Wydzial Prawa i Administracji",new java.io.File("src\\main\\resources\\static\\images\\WPrawaiAdministracji.jpg")));
        umkFaculties.add(new PreFacultyObj("Wydzial Sztuk Pieknych",new java.io.File("src\\main\\resources\\static\\images\\WSztukPieknych.jpg")));
        umkFaculties.add(new PreFacultyObj("Wydzial Teologiczny",new java.io.File("src\\main\\resources\\static\\images\\WTeologiczny.jpg")));

        for (int i = umkFaculties.size() - 1; i >= 0; i--) {
            if (facultyRepo.findByFacultyNameField(umkFaculties.get(i).getFacultyName()) == null) {
                java.io.File file = umkFaculties.get(i).getFacultyPic();
                Faculty faculty = new Faculty();
                faculty.setFacultyNameField(umkFaculties.get(i).getFacultyName());
                File fileModel = new File();
                fileModel.setName(umkFaculties.get(i).getFacultyName());
                try {
                    fileModel.setPic(Files.readAllBytes(file.toPath()));
                } catch (IOException e) {
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

    private void LoadTswaPrimeAdmin() {
        String primeAdminEMail = "tswa@mail.com";
        if (userSecurityDataRepo.findByEmail(primeAdminEMail) == null) {

            Person person = new Person();
            person.setNameField("TSWA");
            person.setSurnameField("TSWA");
            UserSecurityData userSecurityData = new UserSecurityData();
            userSecurityData.setActive(true);
            userSecurityData.setMatchingPassword("pass");
            userSecurityData.setPassword("pass");
            userSecurityData.setEmail(primeAdminEMail);
            person.setUserSecurityDataField(userSecurityData);


            personRepo.save(person);
            userSecurityDataRepo.save(userSecurityData);
            SecurityRole securityRole = roleRepo.findByRoleName("ADMIN");
            userSecurityData.addARole(securityRole);
            securityRole.addUserSecurityDataToRole(userSecurityData);
            roleRepo.save(securityRole);


        }
    }
    private void LoadSampleUser() {
        String primeAdminEMail = "sampleUser@mail.com";
        if (userSecurityDataRepo.findByEmail(primeAdminEMail) == null) {

            Person person = new Person();
            person.setNameField("User");
            person.setSurnameField("User");
            UserSecurityData userSecurityData = new UserSecurityData();
            userSecurityData.setActive(true);
            userSecurityData.setMatchingPassword("pass");
            userSecurityData.setPassword("pass");
            userSecurityData.setEmail(primeAdminEMail);
            person.setUserSecurityDataField(userSecurityData);


            personRepo.save(person);
            userSecurityDataRepo.save(userSecurityData);
            SecurityRole securityRole = roleRepo.findByRoleName("USER");
            userSecurityData.addARole(securityRole);
            securityRole.addUserSecurityDataToRole(userSecurityData);
            roleRepo.save(securityRole);

        }
    }

}
