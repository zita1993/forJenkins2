package com.czx.mvnbook.account.email;

public class AccountEmailException extends Exception{

    private static final long serialVersionUID = 4135419264770994159L;
    public AccountEmailException(String message){
        super(message);
    }
    public AccountEmailException(String message,Throwable throwable){
        super(message,throwable);
    }
}
