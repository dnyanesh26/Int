package org.example.java8.Streams.pojo;

public class Transaction {

    String date;
    int amount;

    public Transaction(String date, int amount) {
        this.amount = amount;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
