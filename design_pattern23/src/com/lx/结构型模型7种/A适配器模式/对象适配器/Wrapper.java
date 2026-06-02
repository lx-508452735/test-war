package com.lx.结构型模型7种.A适配器模式.对象适配器;

import com.lx.结构型模型7种.A适配器模式.类适配器.Targetable;

public class Wrapper implements Targetable {
 
    private Source source;  
 
    public Wrapper(Source source){  
        super();  
        this.source = source;  
    }  
    @Override  
    public void method2() {  
        System.out.println("this is the targetable method!");  
    }  
 
    @Override  
    public void method1() {  
        source.method1();  
    }  
}  