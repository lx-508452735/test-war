package com.lx.创建型模式5种.A静态工厂方法模式;

public class MailSender implements Sender {
    @Override  
    public void Send() {  
        System.out.println("this is mailsender!");  
    }  
}  