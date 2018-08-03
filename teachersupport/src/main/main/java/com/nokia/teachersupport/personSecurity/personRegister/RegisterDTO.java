package com.nokia.teachersupport.personSecurity.personRegister;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@PasswordMatches
public class RegisterDTO {

    @NotNull
    @NotEmpty
    private String userName_Email;

    @NotNull
    @NotEmpty
    private String userPass;

    @NotNull
    @NotEmpty
    private String userConfirmPass;
}
