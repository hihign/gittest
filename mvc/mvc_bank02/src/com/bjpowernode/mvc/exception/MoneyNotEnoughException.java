package com.bjpowernode.mvc.exception;

public class MoneyNotEnoughException extends Exception{
    public MoneyNotEnoughException(){}

    public MoneyNotEnoughException(String msg){
        super(msg);
    }
}
