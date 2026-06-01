package com.lx.test;

import cn.hutool.json.JSONObject;
import org.apache.axis.Constants;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import java.net.URL;

/**
 * @author Jazen
 */

public class ClientTest {

    public static void main(String[] args) throws Exception {
        String process = process();
        System.out.println(process);
    }

    public static String process() throws Exception {
        // http://10.99.148.19:8080/cgs-ui/services/TFM_HNGYTODO_FundService?wsdl
        String endpoint = "http://localhost:8091/services/ws?wsdl";
        String namespache = "http://impl.service.interfacemock.bsp.com/";

        String loginName = "Jazen";
        // TODO 名称需要为process
        String method = "process";
        String res = "";
        try {
            JSONObject msg = new JSONObject();
            msg.putByPath("person_uuid", loginName);

            Service service = new Service();

            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(new URL(endpoint));
            call.setOperation(method);
            call.setOperationName(new QName(namespache, method));
            call.addParameter("xml", Constants.XSD_STRING, ParameterMode.IN);
            call.setReturnType(XMLType.XSD_STRING);
            res = (String) call.invoke(new Object[]{msg.toString()});

            //res = new WSClient(endpoint, namespache, method, msg.toString()).send();
            return res;
        } catch (Exception ex) {
//            log.error(ex.getMessage(), ex);
            throw new Exception(ex);
        }
    }
}