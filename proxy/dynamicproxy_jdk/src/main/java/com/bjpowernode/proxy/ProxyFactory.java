package com.bjpowernode.proxy;

import com.bjpowernode.service.Service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {
    private Service target;
    public  ProxyFactory(Service target){
        this.target = target;
    }

    //开始代理工作，返回动态代理代理对象
    public Object getAgent(){
        return Proxy.newProxyInstance(/*ClassLoader loader, 类加载器，加载目标对象的类
                                      Class<?>[] interfaces, 获取目标对象所有接口实现方法
                                      InvocationHandler h     代理功能和新增功能的接口  */
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    //proxy为创建的代理对象，method就是客户需求中的核心业务方法，args是方法的参数
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("商量场地。。。。。。");
                        System.out.println("商量时间。。。。。。");
                        //核心业务
                        Object retValue = method.invoke(target,args);
                        System.out.println("结清费用。。。。。。");
                        return retValue;
                    }
                }
        );
    }
}
