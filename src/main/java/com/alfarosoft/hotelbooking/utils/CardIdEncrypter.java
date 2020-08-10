package com.alfarosoft.hotelbooking.utils;

public class CardIdEncrypter {

    //This is a basic implementation of the functionality
    //TODO implement BASE encryption for the card number
    private String encryptCardNumber (String cardNumber){
        String numberEncrypted;
        char[] characters = cardNumber.toCharArray();
        int random1 = (int)(Math.random() * cardNumber.length());
        int random2 = (int)(Math.random() * cardNumber.length());
        int random3 = (int)(Math.random() * cardNumber.length());
        int random4 = (int)(Math.random() * cardNumber.length());
        int random5 = (int)(Math.random() * cardNumber.length());
        characters[random1] = '_';
        characters[random2] = '%';
        characters[random3] = '*';
        characters[random4] = '/';
        characters[random5] = '#';
        numberEncrypted = new String(characters);
        return numberEncrypted;
    }
}
