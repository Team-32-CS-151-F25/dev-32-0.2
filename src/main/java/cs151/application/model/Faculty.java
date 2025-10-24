package cs151.application.model;

import java.io.IOException;
import java.util.List;

public class Faculty {


    public static void setProgrammingLanguage(String Language) throws IOException {
        ProgrammingLanguageDAO.setLanguage(Language);
    }

    public static String[] getProgrammingLanguage() {
        return ProgrammingLanguageDAO.getLanguage();
    }

    public static void setStudentProfile(String name, String academicStatus, String jobStatus, String jobDetails) throws IOException {
        StudentProfileDAO.setName(name);
        StudentProfileDAO.setAcademicStatus(academicStatus);
        StudentProfileDAO.setJobStatus(jobStatus);
        StudentProfileDAO.setJobDetails(jobDetails);
        StudentProfileDAO.newData();
    }

    public static void setStudentFlag(String flag) throws IOException {
        StudentProfileDAO.Flags.setFlag(flag);
    }

    public static void setStudentEval(String eval) throws IOException {
        StudentProfileDAO.Evaluation.setEvaluation(eval);
    }

    public static void setStudentSkills(String[] languages, String[] databases, String role) throws IOException {
        StudentProfileDAO.Skills.setProgrammingLang(languages);
        StudentProfileDAO.Skills.setDatabases(databases);
        StudentProfileDAO.Skills.setRole(role);
    }

    public static boolean matchName(String name) throws IOException {
        return StudentProfileDAO.checkName(name);
    }

    public static List<List<String>> getStudentProfileRecord(){
        return StudentProfileDAO.getAllData();
    }

    public static List<List<String>> getStudentSkillsRecord(){
        return StudentProfileDAO.Skills.getAllData();
    }

    public static List<List<String>> getStudentEvaluationRecord(){
        return StudentProfileDAO.Evaluation.getAllData();
    }

    public static List<List<String>> getStudentFlagsRecord(){
        return StudentProfileDAO.Flags.getAllData();
    }

}
