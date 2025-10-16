package cs151.application;

import java.io.IOException;

public class StudentProfile {
    private static final CSVParser profileParser = new CSVParser("src/main/resources/cs151/application/studentsProfile.csv");

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
        if(jobDetails.equals("")) {
            profileParser.setData("N/A");
        }else
            profileParser.setData(jobDetails);
    }
    public static void newData() throws IOException {
        profileParser.addNewLine();
    }
    // Each of the below will add to their own csv file with Name as the identifier
    //method to add skills
    static class Skills {
        private static final CSVParser skillParser = new CSVParser("src/main/resources/cs151/application/skills.csv");

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
    }

    //method to add evaluations
    static class Evaluation {
        private static final CSVParser evaluationParser = new CSVParser("src/main/resources/cs151/application/evaluations.csv");

        public static void setEvaluation(String evaluation) throws IOException {
            //need to check if the student name already exists,
            // if there are prior evaluations and then add the data
            evaluationParser.setData(studentName);
            evaluationParser.setData(evaluation);
            evaluationParser.addNewLine();
        }
    }

    //method to add flags
    static class Flags {
        private static final CSVParser flagParser = new CSVParser("src/main/resources/cs151/application/flags.csv");

        public static void setFlag(String flag) throws IOException {
            flagParser.setData(studentName);
            flagParser.setData(flag);
            flagParser.addNewLine();
        }
    }





}