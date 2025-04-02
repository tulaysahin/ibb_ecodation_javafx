package com.tulaysahinduran.ibb_ecodation_javafx.exceptions;

public class RegisterNotFoundException extends RuntimeException {

    // Parametresiz Constructor
    public RegisterNotFoundException() { super("Kayıt bulunamadı"); }
   //Parametreli
    public RegisterNotFoundException(String message) {
        super(message);
    }

}
