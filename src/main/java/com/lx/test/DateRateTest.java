package com.lx.test;

import cn.hutool.core.date.DateUnit;
import com.lx.test.po.DateRate;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.BiConsumer;

/**
 * @author liux
 * @version 1.0
 */
public class DateRateTest {

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Map<Date, BigDecimal> interestValuesList = new TreeMap<Date,BigDecimal>();
        interestValuesList.put(simpleDateFormat.parse("2024-01-01"),new BigDecimal(10));
        interestValuesList.put(simpleDateFormat.parse("2024-02-01"),new BigDecimal(20));
        interestValuesList.put(simpleDateFormat.parse("2024-03-01"),new BigDecimal(30));

//        interestValuesList.put(simpleDateFormat.parse("2021-08-04"),new BigDecimal(10));

        List<DateRate> lendsRateAdjustDaoManagerList = new ArrayList<>();
        DateRate dateRate1 = new DateRate(simpleDateFormat.parse("2024-01-01"),simpleDateFormat.parse("2024-03-01"),new BigDecimal(15));
//        DateRate dateRate2 = new DateRate(simpleDateFormat.parse("2024-01-01"),simpleDateFormat.parse("2024-02-01"),new BigDecimal(40));

        lendsRateAdjustDaoManagerList.add(dateRate1);
//        lendsRateAdjustDaoManagerList.add(dateRate2);

//        lendsRateAdjustDaoManagerList.forEach(p->{
//            TreeMap<Date, BigDecimal> dateBigDecimalTreeMap = dealRate(interestValuesList, p);
//        });

        lendsRateAdjustDaoManagerList.forEach(p->{
            p.setAdjustrate(p.getAdjustrate().divide(BigDecimal.valueOf(100)));
                Set<Date> dateSet =interestValuesList.keySet();
            ArrayList<Date> dateArrayList = new ArrayList<>(dateSet);//副本
            Iterator<Date> iterator = dateArrayList.iterator();
            Date date = iterator.next();//取出map的第一个date
            Date nextDate = date;//下一个date，初始第一个date
            BigDecimal temp = null;
            if (p.getAdjustenddate().compareTo(date)<0){
                //不存在比第开始还小
                return;
            }
            if (!iterator.hasNext()){
                interestValuesList.put(p.getAdjustbegindate(),p.getAdjustrate());
                interestValuesList.put(p.getAdjustenddate(),interestValuesList.get(date));
                return;
            }
            while (p.getAdjustbegindate().compareTo(date)>=0 && nextDate.compareTo(p.getAdjustenddate())<0 && iterator.hasNext()){

                while (iterator.hasNext() && nextDate.compareTo(p.getAdjustenddate())<0){
                    nextDate = iterator.next();
                    if(p.getAdjustenddate().compareTo(nextDate)>0){
                        temp = interestValuesList.get(nextDate);
                        //移除小于结束日期的
                        interestValuesList.remove(nextDate);
                    }else if (p.getAdjustenddate().compareTo(nextDate)<0){
                        //插入结束日期，利率为原利率
                        interestValuesList.put(p.getAdjustenddate(),temp!=null?temp:interestValuesList.get(date));
                        return;
                    }
                }
                interestValuesList.put(p.getAdjustbegindate(),p.getAdjustrate());
            }
            if (p.getAdjustenddate().compareTo(nextDate)>0){
                //补上最后一段
                interestValuesList.put(p.getAdjustenddate(),temp!=null?temp:interestValuesList.get(date));
            }
        });

        System.out.println(interestValuesList);

    }
    public static TreeMap<Date,BigDecimal> dealRate(Map<Date, BigDecimal> interestValuesList, DateRate dateRate){
        Date adjustbegindate = dateRate.getAdjustbegindate();
        Date adjustenddate = dateRate.getAdjustenddate();
        BigDecimal adjustrate = dateRate.getAdjustrate();
        List<Date> removeDate = new ArrayList<>();

        TreeMap<Date,BigDecimal> newMap = new TreeMap<>();
        newMap.put(adjustbegindate,adjustrate);
        BigDecimal dateRate1 = getDateRate(interestValuesList, adjustenddate);
        newMap.put(adjustenddate,dateRate1);
        //如果该值在调整期中，则移除
        interestValuesList.forEach((date, rate) -> {
            if(date.compareTo(adjustbegindate) < 0){
                newMap.put(new Date(date.getTime()),new BigDecimal(String.valueOf(rate)));
            }else if(date.compareTo(adjustenddate) > 0){
                newMap.put(new Date(date.getTime()),new BigDecimal(String.valueOf(rate)));
            }
        });
        return newMap;
    }

    public static BigDecimal getDateRate(Map<Date, BigDecimal> interestValuesList, Date da){
        final BigDecimal[] res = {null};
        interestValuesList.forEach((date, rate) -> {
            if(da.compareTo(date) >= 0){
                res[0] = rate;
                return;
            }
        });
        return res[0];
    }

}
