package com.nokia.teachersupport.person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MeetMeDTO {
    private String placeField;
    private String officeField;
    private String dayField;
    private String timeFromFieldH;
    private String timeToFieldH;
    private String timeFromFieldM;
    private String timeToFieldM;

}
