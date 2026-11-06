package com.animeword.bakend.error;

public class errorNoencotrado extends RuntimeException {
    public errorNoencotrado (String mensaje){
        super(mensaje);
    }
}
