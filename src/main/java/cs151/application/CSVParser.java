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


    CSVParser(String filename){
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
        line = br.readLine();
        while (line != null) {
            String[] data = line.split(",");
            Collections.addAll(languages, data);
        }

    }

    public void setData(String data) throws IOException {
        FileWriter fw = null;
        try {
            fw = new FileWriter(filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(",");
        bw.write(data);
        languages.add(data);
    }

    public String getData() {
        String data = languages.get(this.index);
        this.index++;
        return data;
    }
}
