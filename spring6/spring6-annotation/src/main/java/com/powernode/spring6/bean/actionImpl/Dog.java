package com.powernode.spring6.bean.actionImpl;

import com.powernode.spring6.bean.Action;
import org.springframework.stereotype.Service;

@Service("dog")
public class Dog implements Action {
    @Override
    public void protect() {
        System.out.println("狗狗保护主人");
    }
}
