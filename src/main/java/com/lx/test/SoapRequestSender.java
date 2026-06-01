package com.lx.test;

import cn.hutool.http.webservice.SoapClient;
import com.fingard.ats.core.utils.AtsStringUtils;
import org.apache.axis.message.PrefixedQName;
import org.junit.Test;

import javax.xml.soap.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SoapRequestSender {

    public static void main(String[] args) {
        try {
            // 创建 SOAP 消息
            MessageFactory messageFactory = MessageFactory.newInstance();
            SOAPMessage soapMessage = messageFactory.createMessage();

            // 获取 SOAP 信封
            SOAPPart soapPart = soapMessage.getSOAPPart();
            SOAPEnvelope envelope = soapPart.getEnvelope();
            envelope.addNamespaceDeclaration("soapenv", "http://schemas.xmlsoap.org/soap/envelope/");
            envelope.addNamespaceDeclaration("ser", "http://service.nstc.com");

            // 创建 SOAP 头
            SOAPHeader header = envelope.getHeader();

            // 创建 SOAP 体
            SOAPBody body = envelope.getBody();
            SOAPElement supplierSyncExec = body.addChildElement("supplierSyncExec", "ser");
            SOAPElement arg0 = supplierSyncExec.addChildElement("arg0");

            // 添加 CDATA 内容
            String cdataContent = "<![CDATA[ \n" +
                    "                \n" +
                    "                \n" +
                    "                <?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "                <DATA>\n" +
                    "                    <DATA>\n" +
                    "                        <REQUESTDATA>完整xml字符串</REQUESTDATA>\n" +
                    "                        <DATAINFOS uuid=\"当前批数据的uuid\">\n" +
                    "                            <DATAINFO>\n" +
                    "                                <DESC1 REMARK=\"员工工号\">DESC1的值</DESC1>\n" +
                    "                                <DESC2 REMARK=\"姓名\">DESC2的值</DESC2>\n" +
                    "                                <DESC3 REMARK=\"岗位序列编码\">DESC3的值</DESC3>\n" +
                    "                                <DESC4 REMARK=\"性别\">DESC4的值</DESC4>\n" +
                    "                                <DESC5 REMARK=\"所属公司编码\">DESC5的值</DESC5>\n" +
                    "                                <DESC6 REMARK=\"所属公司名称\">DESC6的值</DESC6>\n" +
                    "                                <DESC7 REMARK=\"经营单位编码\">DESC7的值</DESC7>\n" +
                    "                                <DESC8 REMARK=\"所属部门编码\">DESC8的值</DESC8>\n" +
                    "                                <DESC9 REMARK=\"所属部门名称\">DESC9的值</DESC9>\n" +
                    "                                <DESC10 REMARK=\"上级部门编码（停）\">DESC10的值</DESC10>\n" +
                    "                                <DESC11 REMARK=\"证件号码\">DESC11的值</DESC11>\n" +
                    "                                <DESC12 REMARK=\"手机\">DESC12的值</DESC12>\n" +
                    "                                <DESC13 REMARK=\"电话号码（停）\">DESC13的值</DESC13>\n" +
                    "                                <DESC14 REMARK=\"电子邮箱\">DESC14的值</DESC14>\n" +
                    "                                <DESC15 REMARK=\"岗位编码\">DESC15的值</DESC15>\n" +
                    "                                <DESC16 REMARK=\"岗位名称\">DESC16的值</DESC16>\n" +
                    "                                <DESC17 REMARK=\"职务级别（停）\">DESC17的值</DESC17>\n" +
                    "                                <DESC18 REMARK=\"入职日期\">DESC18的值</DESC18>\n" +
                    "                                <DESC19 REMARK=\"离职日期\">DESC19的值</DESC19>\n" +
                    "                                <DESC20 REMARK=\"岗位序列\">DESC20的值</DESC20>\n" +
                    "                                <DESC21 REMARK=\"是否在岗\">DESC21的值</DESC21>\n" +
                    "                                <DESC22 REMARK=\"调离记录状态（停）\">DESC22的值</DESC22>\n" +
                    "                                <DESC23 REMARK=\"员工类别编码\">DESC23的值</DESC23>\n" +
                    "                                <DESC24 REMARK=\"员工类别\">DESC24的值</DESC24>\n" +
                    "                                <DESC25 REMARK=\"开户银行\">DESC25的值</DESC25>\n" +
                    "                                <DESC26 REMARK=\"银行账号\">DESC26的值</DESC26>\n" +
                    "                                <DESC27 REMARK=\"联行号\">DESC27的值</DESC27>\n" +
                    "                                <DESC28 REMARK=\"所属组织终身码（停）\">DESC28的值</DESC28>\n" +
                    "                                <DESC29 REMARK=\"所属部门终身码（停）\">DESC29的值</DESC29>\n" +
                    "                                <DESC30 REMARK=\"国家/地区（停）\">DESC30的值</DESC30>\n" +
                    "                                <DESC31 REMARK=\"HR职务级别编码（停）\">DESC31的值</DESC31>\n" +
                    "                                <DESC32 REMARK=\"归属范围（停）\">DESC32的值</DESC32>\n" +
                    "                                <DESC33 REMARK=\"HR档案主键（停）\">DESC33的值</DESC33>\n" +
                    "                                <DESC34 REMARK=\"数据来源\">DESC34的值</DESC34>\n" +
                    "                                <DESC35 REMARK=\"时间戳（停）\">DESC35的值</DESC35>\n" +
                    "                                <DESC36 REMARK=\"优先级别（停）\">DESC36的值</DESC36>\n" +
                    "                                <DESC37 REMARK=\"证件类型（停）\">DESC37的值</DESC37>\n" +
                    "                                <DESC38 REMARK=\"经营单位名称\">DESC38的值</DESC38>\n" +
                    "                                <DESC39 REMARK=\"上级部门名称（停）\">DESC39的值</DESC39>\n" +
                    "                                <DESC40 REMARK=\"职务指示\">DESC40的值</DESC40>\n" +
                    "                                <DESC41 REMARK=\"第一次入特变日期\">DESC41的值</DESC41>\n" +
                    "                                <DESC42 REMARK=\"汇报者岗位\">DESC42的值</DESC42>\n" +
                    "                                <DESC43 REMARK=\"汇报者岗位名称\">DESC43的值</DESC43>\n" +
                    "                                <DESC44 REMARK=\"直接上级工号\">DESC44的值</DESC44>\n" +
                    "                                <DESC45 REMARK=\"直接上级名称\">DESC45的值</DESC45>\n" +
                    "                                <DESC46 REMARK=\"数据新增/变更\">DESC46的值</DESC46>\n" +
                    "                                <DESC47 REMARK=\"社会工龄起算日\">DESC47的值</DESC47>\n" +
                    "                                <DESC48 REMARK=\"工时制编号\">DESC48的值</DESC48>\n" +
                    "                                <DESC49 REMARK=\"工时制\">DESC49的值</DESC49>\n" +
                    "                                <DESC50 REMARK=\"民族编号\">DESC50的值</DESC50>\n" +
                    "                                <DESC51 REMARK=\"民族\">DESC51的值</DESC51>\n" +
                    "                                <DESC52 REMARK=\"出生日期\">DESC52的值</DESC52>\n" +
                    "                                <DESC53 REMARK=\"转正生效日期\">DESC53的值</DESC53>\n" +
                    "                                <DESC54 REMARK=\"岗位层级编号\">DESC54的值</DESC54>\n" +
                    "                                <DESC55 REMARK=\"岗位层级\">DESC55的值</DESC55>\n" +
                    "                                <DESC56 REMARK=\"人才库标识\">DESC56的值</DESC56>\n" +
                    "                                <DESC57 REMARK=\"骨干员工标识\">DESC57的值</DESC57>\n" +
                    "                                <DESC58 REMARK=\"子女出生日期\">DESC58的值</DESC58>\n" +
                    "                                <DESC59 REMARK=\"日期预留2\">DESC59的值</DESC59>\n" +
                    "                                <DESC60 REMARK=\"日期预留3\">DESC60的值</DESC60>\n" +
                    "                                <DESC61 REMARK=\"政治面貌\">DESC61的值</DESC61>\n" +
                    "                                <DESC62 REMARK=\"职称\">DESC62的值</DESC62>\n" +
                    "                                <DESC63 REMARK=\"职业资格\">DESC63的值</DESC63>\n" +
                    "                                <DESC64 REMARK=\"工作地点-市级\">DESC64的值</DESC64>\n" +
                    "                                <DESC65 REMARK=\"文本预留5\">DESC65的值</DESC65>\n" +
                    "                                <DESC66 REMARK=\"文本预留6\">DESC66的值</DESC66>\n" +
                    "                                <DESC67 REMARK=\"文本预留7\">DESC67的值</DESC67>\n" +
                    "                                <DESC68 REMARK=\"预留字段8（停）\">DESC68的值</DESC68>\n" +
                    "                                <CODE REMARK=\"主编码\">code的值</CODE>\n" +
                    "                                <UUID REMARK=\"UUID\">uuid的值</UUID>\n" +
                    "                                <SUBMITCORP REMARK=\"提报单位\">submitcorp的值</SUBMITCORP>\n" +
                    "                                <SPECIALITYCODES>\n" +
                    "                                    <SPECIALITYCODE SPECIALITYNAME=\"银行信息\" CATEGORYCODE=\"\" SPECIALITYCODE=\"10002\">\n" +
                    "                                        <PROPERTYCODE PROPERTYCODE=\"BANKACCOUNT\" STANDARDCODE=\"\" PROPERTYNAME=\"银行账号\" PROPERTYLEN=\"长度\" EXTERNALCONTRASTCODE=\"外部对照码\" PREFIX=\"前置符号\" SUFFIX=\"后置符号\" BOUNDSYMBOL=\"连接符\" UNIT=\"计量单位\">BANKACCOUNT的值</PROPERTYCODE>\n" +
                    "                                        <PROPERTYCODE PROPERTYCODE=\"BANK\" STANDARDCODE=\"\" PROPERTYNAME=\"开户银行\" PROPERTYLEN=\"长度\" EXTERNALCONTRASTCODE=\"外部对照码\" PREFIX=\"前置符号\" SUFFIX=\"后置符号\" BOUNDSYMBOL=\"连接符\" UNIT=\"计量单位\">BANK的值</PROPERTYCODE>\n" +
                    "                                        <PROPERTYCODE PROPERTYCODE=\"NBR\" STANDARDCODE=\"\" PROPERTYNAME=\"联行号\" PROPERTYLEN=\"长度\" EXTERNALCONTRASTCODE=\"外部对照码\" PREFIX=\"前置符号\" SUFFIX=\"后置符号\" BOUNDSYMBOL=\"连接符\" UNIT=\"计量单位\">NBR的值</PROPERTYCODE>\n" +
                    "                                    </SPECIALITYCODE>\n" +
                    "                                </SPECIALITYCODES>\n" +
                    "                            </DATAINFO>\n" +
                    "                        </DATAINFOS>\n" +
                    "                    </DATA>\n" +
                    "]]>"

                    ;
            arg0.addTextNode(cdataContent);

            // 保存消息
            soapMessage.saveChanges();

            // 打印 SOAP 请求消息
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            soapMessage.writeTo(outputStream);
            String soapRequest = new String(outputStream.toByteArray());
            System.out.println("SOAP 请求: " + soapRequest);

            // 发送 SOAP 请求
            URL endpoint = new URL("http://localhost:8091/services/supplierSyncExecUser");
            HttpURLConnection connection = (HttpURLConnection) endpoint.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
//            connection.setRequestProperty("SOAPAction", "http://service.nstc.com/supplierSyncExec");
            connection.setDoOutput(true);

            connection.getOutputStream().write(outputStream.toByteArray());

            // 获取响应
            java.io.InputStream is = connection.getInputStream();
            java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
            String response = s.hasNext() ? s.next() : "";
            System.out.println("SOAP 响应: " + response);

        } catch (SOAPException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建 SOAP 头参数
     * @return 包含头参数的 Map
     */
    private static Map<String, Object> createHeaderParams() {
        Map<String, Object> headerParams = new HashMap<>();
        headerParams.put("user", "接口访问策略账号");
        headerParams.put("password", "接口访问策略账号密码（32位md5加密）");
        return headerParams;
    }

    @Test
    public void test190() throws Exception{
        // 创建 SOAP 消息
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        // 获取 SOAP 信封
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("SOAP-ENV", "http://schemas.xmlsoap.org/soap/envelope/");
        envelope.addNamespaceDeclaration("tns", "http://sys.webservice.client");
        envelope.addNamespaceDeclaration("web", "http://webservice.review.km.kmss.landray.com/");

        // 创建 SOAP 头
        SOAPHeader header = envelope.getHeader();
        SOAPElement requestHeader = header.addChildElement("RequestSOAPHeader", "tns");
        SOAPElement user = requestHeader.addChildElement("user", "tns");
        user.addTextNode("接口访问策略账号");
        SOAPElement password = requestHeader.addChildElement("password", "tns");
        password.addTextNode("接口访问策略账号密码（32位md5加密）");

        // 创建 SOAP 体
        SOAPBody body = envelope.getBody();
        SOAPElement supplierSyncExecAccount = body.addChildElement("supplierSyncExecAccount", "web");

        // 保存消息
        soapMessage.saveChanges();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        soapMessage.writeTo(outputStream);
        String soapRequest = new String(outputStream.toByteArray());
        System.out.println("SOAP 请求: " + soapRequest);
    }
}    