package com.nokia.teachersupport.studGroup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudGroupDTO {
    private String groupNameField;
    private String facultyField;
    private String groupNrFiled;
    private String classNameField;
    private String classDayFiled;
    private String timeFromFieldH;
    private String timeToFieldH;
    private String timeFromFieldM;
    private String timeToFieldM;
}
