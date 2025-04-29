package entity;

import java.time.LocalDate;

public class Transaction {
    private String card;
    private int bookId;
    private String action;
    private LocalDate date;
    private LocalDate dueDate;

    public Transaction(String card, int bookId, String action, LocalDate date, LocalDate dueDate) {
        this.card = card;
        this.bookId = bookId;
        this.action = action;
        this.date = date;
        this.dueDate = dueDate;
    }

    public String getAction() {
        return action;
    }

    public int getBookId() {
        return bookId;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
    public LocalDate getDate(){
        return date;
    }
    public String getCard() {
        return card;
    }

    public String toString() {
        return card + " " + action + " book ID: " + bookId + " on " + date +
               (dueDate != null ? ", Due: " + dueDate : "");
    }
}
