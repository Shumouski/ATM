package BANKOMAT;

public class Card{
    private String accountNumber;
    private int password;
    private double score;
    private String name;
    private String sureName;
    public Card(String accountNumber, int password, double score, String name, String sureName) {
        this.accountNumber = accountNumber;
        this.password = password;
        this.score = score;
        this.name = name;
        this.sureName = sureName;
    }
    @Override
    public String toString() {
        return  "Information " + "\n"+
                " 1. Name: " + name + " " + sureName +"\n" +
                " 2. Account number: " + accountNumber +"\n" +
                " 3. Password: " + password +"\n" +
                " 4. Score: " + score +"\n" +
                "\n";
    }
    public String getAccountNumber() {
        return accountNumber;
    }

    public int getPassword() {
        return password;
    }
    public void setPassword(int password) {
        this.password = password;
    }
    public double getScore() {
        return score;
    }
    public void setScore(double score) {
            this.score = score;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSureName() {
        return sureName;
    }
}
