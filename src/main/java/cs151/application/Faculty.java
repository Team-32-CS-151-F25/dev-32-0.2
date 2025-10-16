package cs151.application;

import java.io.IOException;

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
}
