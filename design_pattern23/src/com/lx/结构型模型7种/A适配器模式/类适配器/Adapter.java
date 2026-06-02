package com.lx.结构型模型7种.A适配器模式.类适配器;

public class Adapter extends Source implements Targetable {
 
    @Override  
    public void method2() {  
        System.out.println("this is the targetable method!");  
    }  
}  