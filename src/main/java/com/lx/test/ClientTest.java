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
        // if (uJson.isNull(user_info)) {
        //     throw new Exception("用户信息不能为空");
        // }
        // String personuuid = user_info.getString("personuuid");
        // if (uString.isBlank(personuuid)) {
        //     throw new Exception("用户信息不能为空！");
        // }
        // http://10.99.148.19:8080/cgs-ui/services/TFM_HNGYTODO_FundService?wsdl
        String endpoint = "http://localhost:8023/services/ws?wsdl";// OaUtil.getSysParameterValue(context, "todo_tfm_url");
        String namespache = "http://impl.service.interfacemock.bsp.com/";// OaUtil.getSysParameterValue(context, "todo_tfm_namespace");
        //根据用户id获取登录名
        // EcpServiceClient ecpServiceClient = (EcpServiceClient) EcpCore.getBean(EcpConstants.ECP_SERVICE_CLIENT);
        // //查询人员名称
        // ecpServiceClient.init("uc", "service.uc.staffService");
        // EcpResultBean staffRb = ecpServiceClient.callService(context, "queryLoginNameByPersonuuid", personuuid);

        String loginName = "Jazen";// staffRb.obtainJSON().getString("loginName");
        // TODO 名称需要为process
        String method = "process";
        String res = "";
        try {
            /*
             * 组装入参
             * {
             "person_uuid": "A9EB133F3F694EC7A8F9BCB369E40913"
             }
             */
            JSONObject msg = new JSONObject();
            msg.put("person_uuid", loginName);
            /*
             * 返回的res格式
             * {
                	code: '1',
                	desc: '成功',
                	db_num: '待办数量',
                	db_list: [ //待办列表，如有
                		{
                			db_title: '待办标题',
                			db_date: '待办日期',
                			db_url: '待办链接（通过单点登录后能直接打开）'
                		}
                	]
                }
             */
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