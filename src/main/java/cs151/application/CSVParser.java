package cs151.application;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {
    private String filename;
    public static List<String> data = new ArrayList<>();
    private Boolean newLine = true;
    private BufferedReader br;

    {
        try {
            br = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public CSVParser(String filename){
        this.filename = filename;
        try {
            readFile(filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readFile(String filename) throws IOException {
        data.clear(); //  prevent duplicates when reloading scenes

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                for (String d : data) {
                    String trimmed = d.trim();
                    if (!trimmed.isEmpty()) {
                        CSVParser.data.add(trimmed);
                    }
                }
            }
        }
    }

    public void setData(String data) throws IOException {
        data = data.trim();
        /*
        programming language already checks for duplicates before calling setData
        and, we don't need this for any other class
                if (data.isEmpty() || exists(data)) {  // duplicate prevention
                    return;
                }
        */
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
            if (!CSVParser.data.isEmpty() && !newLine) {
                bw.write(","); // no comma on first entry
            }
            bw.write(data);
        }
        newLine = false;
        CSVParser.data.add(data);
    }

    public String[] getData() {
        String[] names = new String[data.size()];
        int count = 0;
        for (String lang : data) {
            names[count++] = lang;
        }
        return names;
    }

    public String getLine() throws IOException {
        return br.readLine();
    }
    
    public String[] getLineArray() throws IOException {
        String line;
        ArrayList<String> lineArray = new ArrayList<>();
        if ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            for (String d : data) {
                String trimmed = d.trim();
                if (!trimmed.isEmpty()) {
                    lineArray.add(trimmed);
                }
            }
        }
        return lineArray.toArray(new String[0]);
    }

    public boolean exists(String value) {
        for (String lang : data) {
            if (lang.trim().equalsIgnoreCase(value.trim())) {
                return true;
            }
        }
        return false;
    }

    public void addNewLine() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
                bw.newLine(); // add new line to the csv file
                newLine = true;
        }
    }
}
