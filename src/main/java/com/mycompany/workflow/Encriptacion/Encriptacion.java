package com.mycompany.workflow.Encriptacion;

import org.mindrot.jbcrypt.BCrypt;

//_____

public class Encriptacion {
    public String encryptPassword(String password){
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        return hashedPassword;
    }

    public boolean checkPassword(String password, String hashedPassword){
        return BCrypt.checkpw(password, hashedPassword);
    }

    public static void main(String[] args) {
        Encriptacion en = new Encriptacion();
        String pass = en.encryptPassword("12345");
        System.out.println(pass);
    }
}

