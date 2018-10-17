package com.nokia.teachersupport.personSecurity.personRegister;


import com.nokia.teachersupport.personSecurity.UserSecurityData;
import com.nokia.teachersupport.serviceProvider.IServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements
        ApplicationListener<OnRegistrationCompleteEvent> {

   @Autowired
    IServiceProvider serviceProvider;


    @Autowired
    private JavaMailSender mailSender;


    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        UserSecurityData user = event.getUser();
        String token = UUID.randomUUID().toString();
        serviceProvider.getITokenService().createVerificationToken(user, token, event.getPassword());

        String recipientAddress = user.getEmail();
        String subject = "Registration TeacherSupportWebApp Confirmation";
        String confirmationUrl
                = event.getAppUrl() + "/teacherSupportRegister/regitrationConfirm?token=" + token;
        // String message = messages.getMessage("message.regSucc", null, event.getLocale());

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText("You have successfully registered in TeacherSupportWebApp." +"/n"+
                "Activate your account by pressing this link:"+"/n"+"http://localhost:9000" + confirmationUrl
        +"/n"+"If you received this e-mail by a mistake, ignore this message.");
        mailSender.send(email);
    }
}