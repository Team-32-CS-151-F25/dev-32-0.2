package cs151.application;

import java.io.IOException;
import java.util.List;

public class Faculty {


    public static void setProgrammingLanguage(String Language) throws IOException {
        ProgrammingLanguage.setLanguage(Language);
    }

    public static String[] getProgrammingLanguage() {
        return ProgrammingLanguage.getLanguage();
    }

    public static void setStudentProfile(String name, String academicStatus, String jobStatus, String jobDetails) throws IOException {
        StudentProfile.setName(name);
        StudentProfile.setAcademicStatus(academicStatus);
        StudentProfile.setJobStatus(jobStatus);
        StudentProfile.setJobDetails(jobDetails);
        StudentProfile.newData();
    }

    public static void setStudentFlag(String flag) throws IOException {
        StudentProfile.Flags.setFlag(flag);
    }

    public static void setStudentEval(String eval) throws IOException {
        StudentProfile.Evaluation.setEvaluation(eval);
    }

    public static void setStudentSkills(String[] languages, String[] databases, String role) throws IOException {
        StudentProfile.Skills.setProgrammingLang(languages);
        StudentProfile.Skills.setDatabases(databases);
        StudentProfile.Skills.setRole(role);
    }

    public static boolean matchName(String name) throws IOException {
        return StudentProfile.checkName(name);
    }

    public static List<List<String>> getStudentProfileRecord(){
        return StudentProfile.getAllData();
    }

    public static List<List<String>> getStudentSkillsRecord(){
        return StudentProfile.Skills.getAllData();
    }

    public static List<List<String>> getStudentEvaluationRecord(){
        return StudentProfile.Evaluation.getAllData();
    }

    public static List<List<String>> getStudentFlagsRecord(){
        return StudentProfile.Flags.getAllData();
    }

    public static void removeStudentData(String name) throws IOException {
        StudentProfile.removeStudentData(name);
        StudentProfile.Skills.removeStudentData(name);
        StudentProfile.Evaluation.removeStudentData(name);
        StudentProfile.Flags.removeStudentData(name);
    }

}
