package com.javadev.funtasking.makieiev;

public class Main {

    public static void main(String[] args) {
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        String pass = passwordGenerator.generatePassword();
        System.out.println(pass);
        passwordGenerator.writeToFile(pass);
        passwordGenerator.readFile();
    }
}
