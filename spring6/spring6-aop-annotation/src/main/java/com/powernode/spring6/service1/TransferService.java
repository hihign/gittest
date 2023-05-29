package com.powernode.spring6.service1;

import org.springframework.stereotype.Service;

@Service("transferService")
public class TransferService {
    public void transfer(){
        System.out.println("正在转账");
    }
}
