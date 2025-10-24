package cs151.application.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVHandler {
    private String filename;
    public static List<String> data = new ArrayList<>();
    private Boolean newLine = true;
    private BufferedReader br;

    public CSVHandler(String filename){
        this.filename = filename;
        try {
            readFile(filename);
            //newline was causing issues for programming language for some reason
            if (filename == "src/main/resources/data/languages.csv") {newLine = false;}
            br = new BufferedReader(new FileReader(filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFile(String filename) throws IOException {
        data.clear(); //  prevent duplicates when reloading scenes

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                for (String d : data) {
                    String trimmed = d.trim();
                    if (!trimmed.isEmpty()) {
                        CSVHandler.data.add(trimmed);
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
            if (!this.data.isEmpty() && !newLine) {
                bw.write(","); // no comma on first entry
            }
            bw.write(data);
        }
        newLine = false;
        CSVHandler.data.add(data);
    }

    public String[] getData() {

        ArrayList<String> names = new ArrayList<>();
        String line;
        try {
            BufferedReader br = new BufferedReader( new FileReader(filename));
            while((line = br.readLine()) != null){
                String[] data = line.split(",");
                for (String d : data) {
                     names.add(d.trim());
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return names.toArray(new String[0]);
    }

    public String getLine() {
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void updateLine(String text, int lineNum, int addIndex)  {
        List<List<String>> fileData;

        try {
            fileData = getFileData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //get the row to update
        List<String> row = fileData.get(lineNum);

        if (addIndex < 0 || addIndex > row.size()) {throw new IndexOutOfBoundsException("Add Index out of bounds");}
        //if the add index is greater than size then add at end
        if (addIndex == row.size()) {
            row.add(text);
        }else {

            row.add(addIndex, text); //add the data
        }

        //write all the data back the csv file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, false));){
            for (int i = 0; i < fileData.size(); i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < fileData.get(i).size(); j++) {
                    sb.append(fileData.get(i).get(j));
                    if (j != fileData.get(i).size() - 1) {
                        sb.append(",");
                    }
                }
                bw.write(sb.toString());
                bw.newLine();
                newLine = true;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<List<String>> getFileData() throws IOException {
        List<List<String>> fileData = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null){
                String[] lineData = line.split(",");
                List<String> row = new ArrayList<>();
                for(String data : lineData){ row.add(data);}
                fileData.add(row);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileData;
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
        }else{
            return null;
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

    public ArrayList<String> getFirstValues() throws IOException {
        ArrayList<String> firstValues = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 1) {
                    firstValues.add(data[0]);
                }
            }
        }
        return firstValues;
    }
}
