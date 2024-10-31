package com.mycompany.workflow.EstructuraUsuarios;

public class Usuario {
    private int id_user;
    private int tipo_user;
    private String mail;
    private  String name_c;
    private String passwordS;
    
    public Usuario(int id_user, int tipo_user, String mail, String name_c, String passwordS) {
        this.id_user = id_user;
        this.tipo_user = tipo_user;
        this.mail = mail;
        this.name_c = name_c;
        this.passwordS = passwordS;
    }

    public Usuario () {

    }

    public Usuario (String mail, String passwordS) {
        this.mail = mail;
        this.passwordS = passwordS;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getTipo_user() {
        return tipo_user;
    }

    public void setTipo_user(int tipo_user) {
        this.tipo_user = tipo_user;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName_c() {
        return name_c;
    }

    public void setName_c(String name_c) {
        this.name_c = name_c;
    }

    public String getPasswordS() {
        return passwordS;
    }

    public void setPasswordS(String passwordS) {
        this.passwordS = passwordS;
    }

    
 


}

