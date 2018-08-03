//package com.nokia.teachersupport.personSecurity.personSecurityOld;
//
//
//import com.nokia.teachersupport.personSecurity.UserSecurityData;
//import com.nokia.teachersupport.personSecurity.personSecurityOld.PasswordMatches;
//
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//
//public  class PasswordMatchesValidator
//        implements ConstraintValidator<PasswordMatches, Object> {
//
//    @Override
//    public void initialize(PasswordMatches constraintAnnotation) {
//    }
//    @Override
//    public boolean isValid(Object obj, ConstraintValidatorContext context){
//        UserSecurityData user = (UserSecurityData) obj;
//        return user.getPassword().equals(user.getMatchingPassword());
//    }
//}