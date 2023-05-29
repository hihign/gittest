package com.powernode.spring6.invocationhandler;

import com.powernode.spring6.dao.OrderDao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TimerInvocationHandler implements InvocationHandler {
    OrderDao orderDao;
    public TimerInvocationHandler(OrderDao orderDao){
        this.orderDao = orderDao;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object retValue = method.invoke(orderDao, args);
        return null;
    }
}
