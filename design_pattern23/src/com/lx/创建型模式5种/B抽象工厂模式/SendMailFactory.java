package com.lx.创建型模式5种.B抽象工厂模式;

public class SendMailFactory implements Provider {
 
    @Override  
    public Sender produce(){  
        return new MailSender();  
    }  
}  