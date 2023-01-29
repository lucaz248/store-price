package com.store.exception;

public class PriceNotFoundException extends Exception {
    public PriceNotFoundException(){
        super("Price not found.");
    }
}
