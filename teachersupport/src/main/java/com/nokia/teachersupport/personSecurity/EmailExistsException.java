package com.nokia.teachersupport.personSecurity;

//Baeldung

@SuppressWarnings("serial")
public class EmailExistsException extends Throwable {

    public EmailExistsException(final String message) {
        super(message);
    }

}