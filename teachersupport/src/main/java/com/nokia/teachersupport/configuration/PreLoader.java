package com.nokia.teachersupport.configuration;

import com.nokia.teachersupport.faculty.Faculty;
import com.nokia.teachersupport.faculty.FacultyRepo;
import com.nokia.teachersupport.fileUpload.FileModel;
import com.nokia.teachersupport.fileUpload.IFileService;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.person.PersonRepo;
import com.nokia.teachersupport.personSecurity.UserSecurityData;
import com.nokia.teachersupport.personSecurity.UserSecurityDataRepo;
import com.nokia.teachersupport.personSecurity.personRegister.verificationToken.TokenRepo;
import com.nokia.teachersupport.personSecurity.personRegister.verificationToken.VerificationToken;
import com.nokia.teachersupport.roles.RoleRepo;
import com.nokia.teachersupport.roles.SecutityRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class PreLoader implements ApplicationListener<ApplicationReadyEvent> {
    private FacultyRepo facultyRepo;
    private IFileService fileService;
    private RoleRepo myRoleRepoInstance;
    private TokenRepo tokenRepo;

    private UserSecurityDataRepo userSecurityDataRepo;
    private PersonRepo personRepo;

    @Autowired
    public PreLoader(TokenRepo tokenRepo, FacultyRepo facultyRepo, IFileService fileService, RoleRepo roleRepo,PersonRepo personRepo,UserSecurityDataRepo userSecurityDataRepo) {
        this.facultyRepo = facultyRepo;
        this.fileService = fileService;
        this.myRoleRepoInstance = roleRepo;
        this.tokenRepo=tokenRepo;
        this.personRepo=personRepo;
        this.userSecurityDataRepo=userSecurityDataRepo;
    }


    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        PreLoadRoles();
        PreLoadFaculties();
        CleanVerificationTokens();
        LoadTswaPrimeAdmin();
    }


    private void PreLoadRoles() {
        if (myRoleRepoInstance.findByRoleName("ADMIN") == null) {
            SecutityRole admin = new SecutityRole();
            admin.setRoleName("ADMIN");
            myRoleRepoInstance.save(admin);
        }

        if (myRoleRepoInstance.findByRoleName("USER") == null) {
            SecutityRole user = new SecutityRole();
            user.setRoleName("USER");
            myRoleRepoInstance.save(user);
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

        private void LoadTswaPrimeAdmin()
        {
            String primeAdminEMail="tswpPrime@mail.com";
            if(userSecurityDataRepo.findByEmail(primeAdminEMail)==null)
            {

                UserSecurityData primeUser=new UserSecurityData();
                primeUser.setActive(true);
                primeUser.setEmail(primeAdminEMail);
                primeUser.setPassword("pass");
                primeUser.setMatchingPassword("pass");
                userSecurityDataRepo.save(primeUser);
                primeUser.addARole(myRoleRepoInstance.findByRoleName("ADMIN"));

                Person primePerson=new Person();
                primePerson.setUserSecurityDataField(primeUser);
                primePerson.setNameField("User");
                primePerson.setSurnameField("Prime");
                personRepo.save(primePerson);

            }
        }

}
