package com.lx.test.po;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author liux
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class DateRate {

    Date adjustbegindate;
    Date adjustenddate;
    BigDecimal adjustrate;


}
