package cs151.application;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StudentProfile {
    private static final CSVParser profileParser = new CSVParser("src/main/resources/data/studentsProfile.csv");

    private static String studentName = "";

    //Faculty class will call these methods
    //method to add name and details -> add to the csv file
    public static void setName(String name) throws IOException {
        studentName = name;
        profileParser.setData(name);
    }
    public static void setAcademicStatus(String academicStatus) throws IOException {
        profileParser.setData(academicStatus);
    }
    public static void setJobStatus(String jobStatus) throws IOException {
        profileParser.setData(jobStatus);
    }
    public static void setJobDetails(String jobDetails) throws IOException {
        if(jobDetails.isEmpty()) {
            profileParser.setData("N/A");
        }else
            profileParser.setData(jobDetails);
    }
    public static void newData() throws IOException {
        profileParser.addNewLine();
    }

    public static boolean checkName(String name){
        boolean found = false;
        try {
            ArrayList<String> names = profileParser.getFirstValues();
            if (names.contains(name))
                found = true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return found;
    }

    public static List<List<String>> getAllData() {
        List<List<String>> allData = new ArrayList<>();
        try {
            allData = profileParser.getFileData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return allData;
    }

    public static void removeStudentData(String name) throws IOException {
        ArrayList<String> names = profileParser.getFirstValues();
        if(names.contains(name)){
            int lineNum = names.indexOf(name);
            profileParser.removeLine(lineNum);
        }
    }
    // Each of the below will add to their own csv file with Name as the identifier
    //method to add skills
    static class Skills {
        private static final CSVParser skillParser = new CSVParser("src/main/resources/data/skills.csv");

        public static void setProgrammingLang (String[] langauage) throws IOException {
            skillParser.setData(studentName);
            skillParser.setData("Programming Language");
            for (String lang : langauage) {
                skillParser.setData(lang);
            }

        }
        public static void setDatabases(String[] databases) throws IOException {
            skillParser.setData("Databases");
            for (String db : databases) {
                skillParser.setData(db);
            }
        }
        public static void setRole(String role) throws IOException {
            skillParser.setData("Role");
            skillParser.setData(role);
            skillParser.addNewLine();
        }

        public static List<List<String>> getAllData() {
            List<List<String>> allData = new ArrayList<>();
            try {
                allData = skillParser.getFileData();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return allData;
        }

        public static void removeStudentData(String name) throws IOException {
            ArrayList<String> names = skillParser.getFirstValues();
            if(names.contains(name)){
                int lineNum = names.indexOf(name);
                skillParser.removeLine(lineNum);
            }
        }
    }

    //method to add evaluations
    static class Evaluation {
        private static final CSVParser evaluationParser = new CSVParser("src/main/resources/data/evaluations.csv");

        public static void setEvaluation(String evaluation) throws IOException {
            //need to check if the student name already exists,
            // if there are prior evaluations and then add the data
            String[] evaluations;
            ArrayList<String> names =  new ArrayList<>();
            //get all the names of the students who have an evaluation
            while ((evaluations = evaluationParser.getLineArray()) != null){
                names.add(evaluations[0]);
            }
            //the index of student Name will correspond to the line the student evaluation is stored in
            if (names.contains(studentName)) {
                int lineNum = names.indexOf(studentName);
                evaluationParser.updateLine(LocalDateTime.now().toString(),lineNum,1);
                evaluationParser.updateLine(evaluation,lineNum,1);

            }else {
                evaluationParser.setData(studentName);
                evaluationParser.setData(evaluation);
                evaluationParser.setData(LocalDateTime.now().toString());
                evaluationParser.addNewLine();
            }
        }

        public static List<List<String>> getAllData() {
            List<List<String>> allData = new ArrayList<>();
            try {
                allData = evaluationParser.getFileData();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return allData;
        }

        public static void removeStudentData(String name) throws IOException {
            ArrayList<String> names = evaluationParser.getFirstValues();
            if(names.contains(name)){
                int lineNum = names.indexOf(name);
                evaluationParser.removeLine(lineNum);
            }
        }
    }

    //method to add flags
    public static class Flags {
        private static final CSVParser flagParser = new CSVParser("src/main/resources/data/flags.csv");
        //  ADD A CHECK FOR EXISTING DATA AND UPDATE THE NEW VALUE -> NEEDS UPDATE BEFORE IT WORS
        public static void setFlag(String flag) throws IOException {
            ArrayList<String> names = flagParser.getFirstValues();
            if(names.contains(studentName)){
                flagParser.updateLine(flag,names.indexOf(studentName),1);
            }
            flagParser.setData(studentName);
            flagParser.setData(flag);
            flagParser.addNewLine();
        }

        public static List<List<String>> getAllData() {
            List<List<String>> allData = new ArrayList<>();
            try {
                allData = flagParser.getFileData();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return allData;
        }

        public static void removeStudentData(String name) throws IOException {
            ArrayList<String> names = flagParser.getFirstValues();
            if(names.contains(name)){
                int lineNum = names.indexOf(name);
                flagParser.removeLine(lineNum);
            }


        }
//

    }







}