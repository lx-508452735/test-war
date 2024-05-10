package com.lx.funcationinterface.impl;

import com.lx.funcationinterface.FuncationInterface;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.lang.annotation.Annotation;
import java.util.StringJoiner;

/**
 * @author liux
 * @version 1.0
 * @create 2023-08-16-14:30
 */
@Data
public class FuncationInterfaceImpl implements FuncationInterface {

    @Override
    public void show() {
        System.out.println("实现类执行...");
    }


}
