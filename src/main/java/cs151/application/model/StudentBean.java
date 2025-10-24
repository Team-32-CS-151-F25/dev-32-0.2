package cs151.application.model;

public class StudentBean {
    private String name;
    private String academicStatus;
    private String jobStatus;
    private String jobDetails;
    private String programmingLang;
    private String databases;
    private String preferredRole;
    private String flags;
    private String evaluation;

    public StudentBean(String name, String academicStatus, String jobStatus,
                       String jobDetails, String programmingLang, String databases,
                       String preferredRole, String flags, String evaluation) {
        this.name = name;
        this.academicStatus = academicStatus;
        this.jobStatus = jobStatus;
        this.jobDetails = jobDetails;
        this.programmingLang = programmingLang;
        this.databases = databases;
        this.preferredRole = preferredRole;
        this.flags = flags;
        this.evaluation = evaluation;
    }

    public String getName() { return name; }
    public String getAcademicStatus() { return academicStatus; }
    public String getJobStatus() { return jobStatus; }
    public String getJobDetails() { return jobDetails; }
    public String getProgrammingLang() { return programmingLang; }
    public String getDatabases() { return databases; }
    public String getPreferredRole() { return preferredRole; }
    public String getFlags() { return flags; }
    public String getEvaluation() { return evaluation; }
}