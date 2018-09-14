package com.nokia.teachersupport.tools;

public class GenerateLink {
    public static String goGenerateLink(String inputString) {
        String outputString = "";
        if (inputString.length() > 9) {
            if (inputString.substring(0, 9).equals("https://") || inputString.substring(0, 8).equals("http://"))
                outputString = inputString;
            else
                outputString = "https://" + inputString;

        }
        return outputString;
    }
}
