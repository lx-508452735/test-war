package com.lx.创建型模式5种.A静态工厂方法模式;

public class FactoryTest {

    /***
     * 工厂模式适合：凡是出现了大量的产品需要创建，并且具有共同的接口时，可以通过工厂方法模式进行创建,
     * 不需要实例化工厂类，所以，大多数情况下，我们会选用第三种——静态工厂方法模式。
     *
     * @date 2026/6/1 16:36
     **/
    public static void main(String[] args) {      
        Sender sender = SendFactory.produceMail();  
        sender.Send();

        Sender sender2 = SendFactory.produceSms();
        sender2.Send();
    }  
}