package cs151.application;

import javafx.stage.Stage;

public class StudentProfile {
    private final static String filename = "src/main/resources/cs151/application/studentsProfile.csv";
    private static CSVParser parser = new CSVParser(filename);
    private static Flags flags = new Flags();
    private static Skills stage = new Skills();
    private static Evaluation evaluation = new Evaluation();

    // Name
    // Academic Status - dropdown list , hard coded
    // Job Status - employed/ unemployed
    // Job Details - required if employed

    //Faculty class will call these methods
    //method to add name and details -> add to the csv file
    // Each of the below will add to their own csv file with Name as the identifier
    //method to add skills
    //method to add evaluations
    //method to add flags

    // add new line at the end for each row of data

}
