package com.nokia.teachersupport.person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BasicInfoDTO {
    String degree;
    String workplace;
    String profession;
    String usos;
    String twitter;
    String facebook;
}
