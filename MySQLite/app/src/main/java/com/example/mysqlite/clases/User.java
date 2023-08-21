package com.example.mysqlite.clases;

public class User {
    private int document;
    private String user;
    private String names;
    private String lastNames;
    private String pass;
    private  int status;

    public User() {

    }

    public User(int document, String user, String names, String lastNames, String pass, int status) {
        this.document = document;
        this.user = user;
        this.names = names;
        this.lastNames = lastNames;
        this.pass = pass;
        this.status = status;
    }

    public int getDocument() {
        return document;
    }

    public void setDocument(int document) {
        this.document = document;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "document=" + document +
                ", user='" + user + '\'' +
                ", names='" + names + '\'' +
                ", lastNames='" + lastNames + '\'' +
                ", pass='" + pass + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
