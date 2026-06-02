package com.lx.创建型模式5种.A静态工厂方法模式;

public class SendFactory {

    public static Sender produceMail(){
        return new MailSender();
    }

    public static Sender produceSms(){
        return new SmsSender();
    }
}  