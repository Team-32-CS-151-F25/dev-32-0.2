package cs151.application;

import java.io.IOException;

public class Faculty {
    ProgrammingLanguage programmingLanguage;

    public void setProgrammingLanguage(String Language) throws IOException {
        programmingLanguage.setLanguage(Language);
    }

}
