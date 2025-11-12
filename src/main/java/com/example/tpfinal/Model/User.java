package com.example.tpfinal.Model;

public class User {


    private long idUser;
    private String username;
    private String password;

    public User(String username, String password , long idUser) {
        this.username = username;
        this.password = password;
        this.idUser = idUser ;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
