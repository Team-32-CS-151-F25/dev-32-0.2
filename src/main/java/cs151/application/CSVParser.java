package cs151.application;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CSVParser {
    public static String filename;
    public static List<String> languages = new ArrayList<>();

    //counter for data
    private int index = 0;


    public CSVParser(String filename){
        CSVParser.filename = filename;
        try {
            readFile(filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readFile(String filename) throws IOException {
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            Collections.addAll(languages, data);
        }

    }

    public void setData(String data) throws IOException {
        data = data.trim();
        if (data.isEmpty() || exists(data)) {  // duplicate prevention
            return;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
            if (!languages.isEmpty()) {
                bw.write(","); // no comma on first entry
            }
            bw.write(data);
        }
        languages.add(data);
    }

    /*
    public String getData() {
        String data = languages.get(this.index);
        if(index < languages.size()) {
            this.index++;
        }
        return data;
    }

     */

    public boolean exists(String value) {
        for (String lang : languages) {
            if (lang.trim().equalsIgnoreCase(value.trim())) {
                return true;
            }
        }
        return false;
    }
}
