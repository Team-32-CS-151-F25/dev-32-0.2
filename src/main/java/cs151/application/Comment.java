package cs151.application;

import java.time.LocalDate;

public class Comment {
    private String text;
    private LocalDate date;

    public Comment(String text, LocalDate date) {
        this.text = text;
        this.date = date;
    }

    public String getText() { return text; }
    public LocalDate getDate() { return date; }
    //
}
