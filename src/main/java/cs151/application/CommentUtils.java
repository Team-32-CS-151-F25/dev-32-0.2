package cs151.application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CommentUtils {

    private static final String COMMENT_FILE = "src/main/resources/data/comments.csv";

    public static void saveComment(String studentName, String commentText) {
        try {
            CSVParser parser = new CSVParser(COMMENT_FILE);
            String line = studentName + "," + commentText + "," + LocalDate.now();
            parser.setData(line);
            parser.addNewLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Comment> getComments(String studentName) {
        List<Comment> list = new ArrayList<>();
        try {
            CSVParser parser = new CSVParser(COMMENT_FILE);
            String[] allData = parser.getData(); // flattened list of all CSV values
            for (int i = 0; i < allData.length; i += 3) {
                if (allData[i].equals(studentName)) {
                    String text = allData[i + 1];
                    String date = allData[i + 2];
                    list.add(new Comment(text, LocalDate.parse(date)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}

