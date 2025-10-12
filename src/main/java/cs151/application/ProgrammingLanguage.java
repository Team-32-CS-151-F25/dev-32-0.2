package cs151.application;

import java.io.IOException;
import java.util.*;

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
        List<String> names = Arrays.asList(list);
        Collections.sort(names, String.CASE_INSENSITIVE_ORDER);

        // sorts alphabetically manually, to avoid using any packages if possible - tony
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
