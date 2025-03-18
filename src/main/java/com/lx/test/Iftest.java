package com.lx.test;


import cn.hutool.core.util.XmlUtil;
import cn.hutool.json.JSONObject;
import com.fingard.ats.core.utils.AtsDateUtils;
import com.fingard.ats.core.utils.AtsStringUtils;
import com.fingard.framework.common.util.CollectionUtils;
import io.swagger.models.auth.In;
import kafka.utils.Json;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author 湘
 * @version 1.0
 */
public class Iftest {

    private Boolean aNull;

    @Test
    public void test13() {
        if (false || true) {
            System.out.println(1);
        }

        if (false || false) {
            System.out.println(2);
        }

        if (true || false) {
            System.out.println(3);
        }

        if (true || true) {
            System.out.println(4);
        }
    }

    @Test
    public void test33() {
        BigDecimal decimal1 = new BigDecimal(7);
        BigDecimal decimal2 = new BigDecimal(3.498);
        BigDecimal divide = decimal1.divide(decimal2, 0, RoundingMode.UP);
        System.out.println(divide);

    }

    @Test
    public void test46() {
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        list.forEach(s -> {
            if (s.equals("b")) {
                return;
            }
            System.out.println(s);
        });
    }


    @Test
    public void test64() {
        if (!String.valueOf(1).equals(null)) {
            System.out.println(1111);
        }
    }

    @Test
    public void test71() throws UnsupportedEncodingException {
        int a = "简称简称简称简称简称简称简称简称简称简称简称简称简称简称简称简称简称简称简称简称简称简称简称简称简称简称简称简称简称简称010".getBytes("GBK").length;
        byte[] gbks = "简称".getBytes("GBK");
        System.out.println(gbks);
        System.out.println(a);
    }

    @Test
    public void test80() {
        System.out.println("0.00%");
    }

    @Test
    public void test85() {
        HashMap<String, BigDecimal> stringBigDecimalHashMap = new HashMap<>();
        stringBigDecimalHashMap.put("123", BigDecimal.ZERO);
        stringBigDecimalHashMap.put("123", new BigDecimal(1));

        for (String s : stringBigDecimalHashMap.keySet()) {
            System.out.println(s + "-->" + stringBigDecimalHashMap.get(s));
        }
    }

    @Test
    public void test97() throws UnsupportedEncodingException {
        String resultdescription = "aaaaaaaaaa" +
                "aaa在a子啊";
        if (resultdescription.getBytes("GBK").length > 16) {
            byte[] gbks = resultdescription.getBytes("GBK");
            byte[] bytes = new byte[16];
            for (int i = 0; i < bytes.length; i++) {
                if (gbks[i] < 0 && i + 1 < bytes.length) {
                    bytes[i] = gbks[i];
                    bytes[i + 1] = gbks[i + 1];
                    i++;
                } else if (gbks[i] > 0 && i + 1 < gbks.length && gbks[i + 1] > 0) {
                    bytes[i] = gbks[i];
                }
            }
            resultdescription = new String(bytes, "GBK");
        }
        System.out.println(resultdescription);
    }

    @Test
    public void test119() {
        ArrayList<String> list = new ArrayList<>();
        list.add("111");
        list.add("222");
        List<String> collect = list.stream().filter(p -> (true && false && true && false)).collect(Collectors.toList());
        List<String> collect2 = list.stream().filter(p -> (true)).collect(Collectors.toList());
        List<String> collect3 = list.stream().filter(p -> (true)).collect(Collectors.toList());
        List<String> collect4 = list.stream().filter(p -> (true)).collect(Collectors.toList());

        System.out.println(list.stream().map(p -> p).collect(Collectors.toList()));
        System.out.println(true && false && true && true);
    }

    @Test
    public void test134() {
        String s = ",1,2,3,";
        String[] split = s.substring(1, s.length() - 1).split(",");
        String substring = s.substring(1, 2);
        System.out.println(split);
        System.out.println(substring);
    }

    @Test
    public void test141() {
        int i = 0;
        int x = 0;
        int c = 0;
        while (i < 10) {
            x = ++x;
            System.out.println(x);
            c = ++x;
            i++;
            System.out.println(x + "-" + c);
        }

    }

    @Test
    public void test157() {
        final StringBuilder stringBuilder = new StringBuilder("tdnotecode");

        int i = stringBuilder.indexOf("tdnotecode");

        stringBuilder.delete(i, i + 10);

        System.out.println(stringBuilder);
    }

    @Test
    public void test168() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = dateFormat.format(new Date());
        System.out.println(date);
    }

    @Test
    public void test177() {
        BigDecimal decimal = new BigDecimal("王华");
        System.out.println(decimal);
    }

    @Test
    public void test183() {

        ArrayList<Object> objects = null;

        List<Object> collect = objects.stream().collect(Collectors.toList());


    }

    @Test
    public void test194() {
        String passelNo = "hdhe_APPLY";
        int indexOf = passelNo.indexOf("_APPLY");
        passelNo = indexOf > -1 ? passelNo.substring(0, indexOf) : passelNo;

        System.out.println(passelNo);

    }

    @Test
    public void test204() {

        int i = 0;
        while (i++ < 5) {
            System.out.println(i);
            if (i == 3) {
                continue;
            }
        }
    }

    @Test
    public void test216() {
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        System.out.println(String.valueOf(objectObjectHashMap.get("1")));
    }

    @Test
    public void test224() {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("111", "aaa");
        hashMap.put("111", "bb");

        for (String key : hashMap.keySet()) {
            System.out.println(key + "-" + hashMap.get(key));
        }

    }

    @Test
    public void test234() {
        boolean a = false;
        boolean b = false;
        // 业务逻辑代码后 可能会改成true
        if (CollectionUtils.isEmpty(new HashMap())) {
            a = true;
        }
        if (a != b) {
            System.out.println("XXX");
        }

    }

    @Test
    public void test249() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.println("i " + i + "j: " + j);
                if (j == 3) {
                    break;
                }
                System.out.println("i " + i + "j: " + j);
            }
        }
    }

    @Test
    public void test262() {
        String value = "中国建筑第七工程局有限公司大良云近东区东乐路以南桂峰路以东地块项目一期农民工工资专用账户";

        int valueLength = 0;
        String chinese = "[Α-￥]";

        for (int i = 0; i < value.length(); ++i) {
            String temp = value.substring(i, i + 1);
            if (temp.matches(chinese)) {
                valueLength += 3;
            } else {
                ++valueLength;
            }
        }

        System.out.println(valueLength);
    }

    @Test
    public void test281() {
        int count = 1;
        for (; ; ) {
            System.out.println("---->1 " + count++);
        }
    }

    @Test
    public void test290() {
        char a = '嚄';
        System.out.println(aNull);

        System.out.println("".equals(null));

    }

    @Test
    public void test301() {
        String s = "ABCD";
        String reverse = Iftest.reverse(s);
        System.out.println(reverse);

    }

    public static String reverse(String originStr) {
        if (originStr == null || originStr.length() <= 1)
            return originStr;
        return reverse(originStr.substring(1)) + originStr.charAt(0);
    }

    @Test
    public void test315() {
        HashMap<String, Object> map = new HashMap<>();
        HashMap<String, Object> map2 = new HashMap<>();
        HashMap<String, Object> map3 = new HashMap<>();
        map.put("html", map2);
        map2.put("haed", map3);
        map2.put("javascript", "java/json");
        map3.put("titile", "bbb");

        Document html = XmlUtil.mapToXml((Map<String, Object>) map.get("html"), "html");
        Element documentElement = html.getDocumentElement();

        method350(documentElement);

        String html2 = XmlUtil.mapToXmlStr((Map<String, Object>) map.get("html"), "html", null, true);
        System.out.println(html2);


    }

    public static void method350(Element documentElement) {

        //todo 判断当前节点的属性

        // 获取子节点列表
        NodeList nodeList = documentElement.getChildNodes();
        // 遍历子节点
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            // 判断节点类型为元素节点
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                method350(element);
            }
        }
    }

    @Test
    public void test358() {
        TreeMap<String, Object> map = new TreeMap<String, Object>();
        boolean b = map.containsKey("");
        boolean c = map.containsKey(null);
        System.out.println(c);
    }

    @Test
    public void test366() {
        String spel = "${{\"dt1\":[{\"qfrq\":\"2024-06-05\",\"wbgpdw\":\"宁波金田铜管有限公司\",\"pjczbs\":\"6f744e48208844acabdb4e93f797d03a\",\"dqrq\":\"2024-12-05\",\"cdf\":\"中国工商银行宁波市江北支行\",\"sprq\":\"2024-06-12\",\"pmje\":33232,\"cpdw\":\"宁波金田铜管有限公司\",\"pjh\":\"000020240605042504541100000045[000000000001-000003323200]\",\"fc\":\"9301,9302,9303,9306,9308,9309\"}],\"method\":\"createworkflow\",\"param\":{\"dt1\":[{\"qfrq\":\"2024-06-05\",\"wbgpdw\":\"宁波金田铜管有限公司\",\"pjczbs\":\"6f744e48208844acabdb4e93f797d03a\",\"dqrq\":\"2024-12-05\",\"cdf\":\"中国工商银行宁波市江北支行\",\"sprq\":\"2024-06-12\",\"pmje\":33232,\"cpdw\":\"宁波金田铜管有限公司\",\"pjh\":\"000020240605042504541100000045[000000000001-000003323200]\",\"fc\":\"9301,9302,9303,9306,9308,9309\"}],\"isnextflow\":\"1\",\"main\":{\"skzz\":\"宁波金田铜业（集团）股份有限公司\",\"ywfw\":\"9301,9302,9303,9306,9308,9309\"},\"creater\":\"999999\",\"workflowid\":\"86271\"}}}";
        if (spel.contains("${") && spel.indexOf("${") == spel.lastIndexOf("${")) {
            spel = spel.replace("${", "");
            spel = AtsStringUtils.replace(spel, spel.lastIndexOf("}") + 1, "}", "", true);
        } else {
            spel = spel.replace("${", "").replace("}", "");
        }

        // 打印最终替换后的字符串，但根据需求，这里其实并未改变原字符串内容
        System.out.println("处理后的字符串: " + spel);
    }

    @Test
    public void test383() throws Exception {
        String source = "0001";
        String[] params = {"^0+(?!$)"};
        String value = String.valueOf(source);
        if (AtsStringUtils.isEmpty(value)) {
            System.out.println("");
        }
        if (params != null && params.length > 0) {
            try {
                String regex = params[0];
                String replacement = "";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(value);
                String s = matcher.replaceAll(replacement);
                System.out.println(s);

            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        } else {
            //{}配置有误,请检查函数配置！
            throw new Exception("根据正则表达式替换函数");
        }

    }

    @Test
    public void test411(){
        final JSONObject s = test410();
        System.out.println(s.toString());
        String a = "1";
        final int s2 = test412(a);
        System.out.println(s2);
        System.out.println(a);
    }

    public JSONObject test410(){
        JSONObject res = new JSONObject();
        res.put("aaa","111");
        try{
            return res;
        }catch (Exception e){
            System.out.println("异常");
        }finally {
            res.put("bbb","222");
        }
        return res;
    }

    public int test412(String a) {
        int i = 1;
        try {
            return i;
        } catch (Exception e) {
            System.out.println("异常");
        } finally {
            i++;
            a= "2";
        }
        a="3";
        return ++i;
    }
    
    @Test
    public void test450(){
        Date date1 = cn.hutool.core.date.DateUtil.parse("2025-03-05", "yyyy-MM-dd");
        Date date2 = cn.hutool.core.date.DateUtil.parse("2026-03-06", "yyyy-MM-dd");
        // 计算两个日期之间的年数差值
        long years = cn.hutool.core.date.DateUtil.betweenYear(date1, date2, false);
        System.out.println(years);

        final Object year2 = AtsDateUtils.getYmdByBeginAndEndDate(date1, date2).get("year");
        final Object month = AtsDateUtils.getYmdByBeginAndEndDate(date1, date2).get("month");
        final Object day = AtsDateUtils.getYmdByBeginAndEndDate(date1, date2).get("day");
        System.out.println(year2);
        System.out.println(month);
        System.out.println(day);
    }

}

