package com.powernode.spring6.proxy;

import com.powernode.spring6.dao.OrderDao;

public class OrderServiceProxy implements OrderDao {
    private OrderDao orderDao;
    public OrderServiceProxy(OrderDao orderDao){
        this.orderDao = orderDao;
    }
    @Override
    public void insert() {
        Long begin = System.currentTimeMillis();
        orderDao.insert();
        Long end = System.currentTimeMillis();
        System.out.println("耗时："+(end-begin));
    }
}
