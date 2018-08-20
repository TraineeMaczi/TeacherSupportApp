package com.nokia.teachersupport.newsP;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EditNewsDTO {
    String oldContent;
    String newContent;
}
