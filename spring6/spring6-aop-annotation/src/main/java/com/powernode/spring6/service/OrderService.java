package com.powernode.spring6.service;

import org.springframework.stereotype.Service;

@Service("orderService")
public class OrderService {
    public void generate(){
        System.out.println("订单正在生成");
    }
}
