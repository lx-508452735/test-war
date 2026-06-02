package com.lx.结构型模型7种.A适配器模式.对象适配器;

import com.lx.结构型模型7种.A适配器模式.类适配器.Targetable;

public class AdapterTest {

    /**
     * 基本思路和类的适配器模式相同，只是将Adapter类作修改，这次不继承Source类，而是持有Source类的实例，以达到解决兼容性的问题。
     *
     * @date 2026/6/1 17:26
     **/
    public static void main(String[] args) {  
        Source source = new Source();  
        Targetable target = new Wrapper(source);
        target.method1();  
        target.method2();  
    }  
}  