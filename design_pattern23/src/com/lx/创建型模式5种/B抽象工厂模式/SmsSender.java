package com.lx.创建型模式5种.B抽象工厂模式;

public class SmsSender implements Sender {

    @Override
    public void Send() {
        System.out.println("this is sms sender!");
    }
}