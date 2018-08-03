package com.nokia.teachersupport.personSecurity.personRegister;

import com.nokia.teachersupport.personSecurity.UserSecurityData;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private String appUrl;
    private Locale locale;
    private UserSecurityData user;

    public OnRegistrationCompleteEvent(
            UserSecurityData user, Locale locale, String appUrl) {
        super(user);

        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }


    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public UserSecurityData getUser() {
        return user;
    }

    public void setUser(UserSecurityData user) {
        this.user = user;
    }
}