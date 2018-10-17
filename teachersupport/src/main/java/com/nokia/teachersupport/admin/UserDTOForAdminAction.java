package com.nokia.teachersupport.admin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTOForAdminAction {
    private String userNameDTOField;
    private String userSurnameDTOField;
    private String userEmailDTOField;
    private String userFacultyDTOField;
    private String userRoleDTOField;
}


