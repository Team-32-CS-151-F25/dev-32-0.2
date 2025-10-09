package cs151.application;

import java.io.IOException;

public class Faculty {


    public static void setProgrammingLanguage(String Language) throws IOException {
        ProgrammingLanguage.setLanguage(Language);
    }

    public static String[] getProgrammingLanguage() {
        return ProgrammingLanguage.getLanguage();
    }

}
