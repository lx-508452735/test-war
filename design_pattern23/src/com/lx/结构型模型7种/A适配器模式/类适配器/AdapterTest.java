package com.lx.结构型模型7种.A适配器模式.类适配器;

public class AdapterTest {

    /**
     * 有一个Source类，拥有一个方法，待适配，目标接口时Targetable，通过Adapter类，将Source的功能扩展到Targetable里
     *
     * @date 2026/6/1 17:25
     **/
    public static void main(String[] args) {  
        Targetable target = new Adapter();
        // 这样Targetable接口的实现类就具有了Source类的功能
        target.method1();
        target.method2();  
    }  
}  