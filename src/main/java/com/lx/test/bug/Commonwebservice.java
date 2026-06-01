package com.lx.test.bug;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.fingard.ats.core.utils.AtsStringUtils;
import org.junit.Test;

/**
 * @author liux
 * @version 1.0
 */
public class Commonwebservice {
    private static String decodeAndRemove(String xml) {
        if(AtsStringUtils.isEmpty(xml)){
            return xml;
        }
        xml = xml.replace("&#60;", "<").replace("&#62;", ">")
                .replace("&lt;", "<").replace("&gt;", ">")
                .replace("&quot;", "\"").replace("<![CDATA[", "")
                .replace("]]>", "");
        return ReUtil.replaceAll(xml, "<\\?.*\\?>","");
    }

    @Test
    public void test23(){
        // 公钥加密,所以RSA对象私钥为null
        String secret = "cad29cc0-f3bc-42e3-9c74-a0c3d2f67fa4";
        String spk = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvjeU2HInLBY6r/ob8hBlpBdpBKGOhWVoV8Edy9dODfIQGUFEwE2D+cv23tc1H58b4gKeiocIanayQSHYzUCx8garc2PT+8Ge9zGAwvbPLzsaWR70RdEzb50R9f5W+yf0VvbUtc0R1q13cXn7Neyqxp06EA0umN0EH+kdiTQpB8mQlg2TaxI2zv/UUtSNktUvqk9Jh0dqCSi88a9mwzEmzZKNk9KESF50rgEAPmsT2yB3THQeMgaLjEympfu7IUQqsah1RjH7u3UtxAVWw/QNLHWtWrIj/pUR0TLP+uyi69MOZGDX1DQPC/ipIaVfJ6L9rLp1HWxqtyEOYzdBEGglAwIDAQAB";
        RSA rsa = new RSA(null,spk);
        //对秘钥进行加密传输，防止篡改数据
        String encryptSecret = rsa.encryptBase64(secret,CharsetUtil.CHARSET_UTF_8,KeyType.PublicKey);
        System.out.println(encryptSecret);


        //封装请求头参数 userid
        RSA rsa2 = new RSA(null,spk);
        //对用户信息进行加密传输,暂仅支持传输OA用户ID
        String encryptUserid = rsa2.encryptBase64("1",CharsetUtil.CHARSET_UTF_8,KeyType.PublicKey);

        System.out.println("\n"+encryptUserid);
    }
}
