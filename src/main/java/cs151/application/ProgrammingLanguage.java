package cs151.application;

import java.io.IOException;

public class ProgrammingLanguage {
    private final static String filename = "src/main/resources/cs151/application/languages.csv";
    private static CSVParser parser = new CSVParser(filename);

    public void setLanguage(String language) throws IOException {
        parser.setData(language);
    }

    public String getLanguage(String language) {
        return parser.getData();
    }
}
