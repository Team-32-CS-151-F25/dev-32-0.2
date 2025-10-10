package cs151.application;

import java.io.IOException;

public class ProgrammingLanguage {
    private final static String filename = "src/main/resources/cs151/application/languages.csv";
    private static CSVParser parser = new CSVParser(filename);

    public static void setLanguage(String language) throws IOException {
        if (!language.isEmpty() && !parser.exists(language)) {
            parser.setData(language);
        }
    }

    public static String[] getLanguage() {
        return format(parser.getData());
    }

    public static String[] format(String[] list) {

        // capitalizes first letter of each language - tony
        for (int i = 0; i < list.length; i++) {
            if (list[i].length() > 0) {
                String first = list[i].substring(0, 1).toUpperCase();
                String rest = "";
                if (list[i].length() > 1) {
                    rest = list[i].substring(1).toLowerCase();
                }
                list[i] = first + rest;
            }
        }

        // sorts alphabetically, slightly complicated but i wanted to avoid using any packages if possible - tony
        for (int i = 0; i < list.length - 1; i++) {
            for (int j = 0; j < list.length - i - 1; j++) {
                if (list[j].compareTo(list[j + 1]) > 0) {
                    String temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                }
            }
        }

        return list;
    }
}
