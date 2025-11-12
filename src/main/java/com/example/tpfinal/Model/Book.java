package com.example.tpfinal.Model;

public class Book {

    private long bookId ;
    private String autheur;
    private String title ;
    private int quantity;
    private long price;


    public Book(long bookId, int quantity, String autheur, String title, long price) {
        this.bookId = bookId;
        this.quantity = quantity;
        this.autheur = autheur;
        this.title = title;
        this.price = price;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAutheur() {
        return autheur;
    }

    public void setAutheur(String autheur) {
        this.autheur = autheur;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
