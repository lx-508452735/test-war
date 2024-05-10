package com.lx.funcationinterface;

import com.lx.funcationinterface.impl.FuncationInterfaceImpl;

import javax.xml.transform.Source;

/**
 * @author liux
 * @version 1.0
 * @create 2023-08-16-14:33
 */

public class FuncationInterfaceTest {

    static void read(FuncationInterface funcationInterface){
        funcationInterface.show();
    }


    public static void main(String[] args) {
        read(new FuncationInterfaceImpl());

        read(new FuncationInterface() {
            @Override
            public void show() {
                System.out.println("匿名内部类...");
            }
        });

        read(()->{
            System.out.println("啊这...");
        });

        read(()-> System.out.println("。。。"));

//        System.out.println(new FuncationInterfaceImpl().toString());


    }
}
