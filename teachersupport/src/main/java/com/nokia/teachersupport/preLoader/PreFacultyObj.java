package com.nokia.teachersupport.preLoader;

import java.io.File;

public class PreFacultyObj {
    private String facultyName;
    private java.io.File facultyPic;

    public PreFacultyObj(String facultyName,java.io.File facultyPic) {
        this.facultyName = facultyName;
        this.facultyPic=facultyPic;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public File getFacultyPic() {
        return facultyPic;
    }

    public void setFacultyPic(File facultyPic) {
        this.facultyPic = facultyPic;
    }
}
