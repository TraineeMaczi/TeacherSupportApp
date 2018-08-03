package com.nokia.teachersupport.personSecurity.personRegister;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator
        implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        RegisterDTO registerDTO = (RegisterDTO) obj;
        return registerDTO.getUserPass().equals(registerDTO.getUserConfirmPass());
    }
}