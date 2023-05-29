package com.bjpowernode.mvc.bank.exceptions;

public class MoneyNotEnoughException  extends Exception{
    public MoneyNotEnoughException(){}

    public MoneyNotEnoughException(String msg){
        super(msg);
    }
}
