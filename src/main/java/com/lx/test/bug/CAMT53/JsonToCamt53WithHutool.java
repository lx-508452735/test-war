package com.lx.test.bug.CAMT53;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonToCamt53WithHutool {
    // CAMT53 标准命名空间（必须正确配置，否则报文无效）
    private static final String CAMT53_NAMESPACE = "urn:iso:std:iso:20022:tech:xsd:camt.053.001.02";
    private static final String REQ = "{\n" +
            "  \"mbs\": {\n" +
            "    \"pub\": {\n" +
            "      \"transcode\": \"BAPB02\",\n" +
            "      \"transdatetime\": \"2023-05-30 20:18:00\",\n" +
            "      \"srcoutsystemcode\": \"GIPS-V3\",\n" +
            "      \"srcbatchno\": \"153197d36e1f4a4bab4c8ea12936c249\",\n" +
            "      \"version\": \"3\",\n" +
            "      \"timezone\": \"GMT+8\",\n" +
            "      \"checktext\": \"\"\n" +
            "    },\n" +
            "    \"req\": {\n" +
            "      \"list\": {\n" +
            "        \"detail\": [\n" +
            "          {\n" +
            "            \"extfield_GrpHdr_CreDtTm\": \"2025-11-28T09:45:32.123Z\",\n" +
            "            \"extfield_GrpHdr_MsgRcpt_Nm\": \"杭州恒通商贸有限公司\",\n" +
            "            \"extfield_GrpHdr_AddtlInf\": \"月度资金结算批量交易\",\n" +
            "            \"extfield_CreDtTm\": \"2025-11-28T09:40:15.678Z\",\n" +
            "            \"tradedatetime\": \"2025-11-28T10:05:23.456Z\",\n" +
            "            \"extfield_RptgSrc_Cd\": \"ONLINE_BANK\",\n" +
            "            \"accountnumber\": \"6222081202009876543\",\n" +
            "            \"extfield_Acct_Id_Othr_SchmeNm_Prtry\": \"CNAPS\",\n" +
            "            \"extfield_Acct_Ccy\": \"CNY\",\n" +
            "            \"accountname\": \"浙江盛泰科技有限公司\",\n" +
            "            \"extfield_bankname\": \"中国工商银行杭州西湖支行\",\n" +
            "            \"extfield_bankcode\": \"102331002567\",\n" +
            "            \"extfield_lcoationkcode\": \"330106\",\n" +
            "            \"extfield_lcoationkname\": \"杭州市西湖区\",\n" +
            "            \"extfield_Bal_Tp_CdOrPrtry_Cd\": \"CLBD\",\n" +
            "            \"extfield_Bal_Amt\": 158260.75,\n" +
            "            \"extfield_Bal_CdtDbtInd\": \"CRDT\",\n" +
            "            \"Sum 流水条数\": 12,\n" +
            "            \"TxsSummry1\": \"单笔最大金额：52800.00\",\n" +
            "            \"TxsSummry2\": \"单笔最小金额：1250.50\",\n" +
            "            \"TxsSummry3\": \"贷方交易笔数：8\",\n" +
            "            \"TxsSummry4\": \"借方交易笔数：4\",\n" +
            "            \"TxsSummry5\": \"交易对手方数量：6\",\n" +
            "            \"TxsSummry6\": \"平均交易金额：13188.39\",\n" +
            "            \"extfield_Ntry_NtryRef\": \"NTRY202511280015\",\n" +
            "            \"extfield_Ntry_Amt\": 38650,\n" +
            "            \"moneyway\": \"TRANSFER\",\n" +
            "            \"extfield_Ntry_Sts\": \"BOOKED\",\n" +
            "            \"extfield_Ntry_AcctSvcrRef\": \"ACTSVCR2025112809876\",\n" +
            "            \"extfield__Ntry_NtryDtls_TxDtls_Refs_AcctSvcrRef\": \"TXR20251128123456\",\n" +
            "            \"extfield_Ntry_NtryDtls_TxDtls_Refs_EndToEndId\": \"E2E202511280000001234\",\n" +
            "            \"amount\": 38650,\n" +
            "            \"purpose\": \"支付 2025 年 11 月原材料采购款\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"extfield_GrpHdr_CreDtTm\": \"2025-11-28T09:45:32.123Z\",\n" +
            "            \"extfield_GrpHdr_MsgRcpt_Nm\": \"杭州恒通商贸有限公司\",\n" +
            "            \"extfield_GrpHdr_AddtlInf\": \"月度资金结算批量交易\",\n" +
            "            \"extfield_CreDtTm\": \"2025-11-28T09:40:15.678Z\",\n" +
            "            \"tradedatetime\": \"2025-11-28T10:05:23.456Z\",\n" +
            "            \"extfield_RptgSrc_Cd\": \"ONLINE_BANK\",\n" +
            "            \"accountnumber\": \"6222011113\",\n" +
            "            \"extfield_Acct_Id_Othr_SchmeNm_Prtry\": \"CNAPS\",\n" +
            "            \"extfield_Acct_Ccy\": \"CNY\",\n" +
            "            \"accountname\": \"浙江盛泰科技有限公司\",\n" +
            "            \"extfield_bankname\": \"中国工商银行杭州西湖支行\",\n" +
            "            \"extfield_bankcode\": \"102331002567\",\n" +
            "            \"extfield_lcoationkcode\": \"330106\",\n" +
            "            \"extfield_lcoationkname\": \"杭州市西湖区\",\n" +
            "            \"extfield_Bal_Tp_CdOrPrtry_Cd\": \"CLBD\",\n" +
            "            \"extfield_Bal_Amt\": 158260.75,\n" +
            "            \"extfield_Bal_CdtDbtInd\": \"CRDT\",\n" +
            "            \"Sum 流水条数\": 12,\n" +
            "            \"TxsSummry1\": \"单笔最大金额：52800.00\",\n" +
            "            \"TxsSummry2\": \"单笔最小金额：1250.50\",\n" +
            "            \"TxsSummry3\": \"贷方交易笔数：8\",\n" +
            "            \"TxsSummry4\": \"借方交易笔数：4\",\n" +
            "            \"TxsSummry5\": \"交易对手方数量：6\",\n" +
            "            \"TxsSummry6\": \"平均交易金额：13188.39\",\n" +
            "            \"extfield_Ntry_NtryRef\": \"NTRY202511280015\",\n" +
            "            \"extfield_Ntry_Amt\": 38650,\n" +
            "            \"moneyway\": \"TRANSFER\",\n" +
            "            \"extfield_Ntry_Sts\": \"BOOKED\",\n" +
            "            \"extfield_Ntry_AcctSvcrRef\": \"ACTSVCR2025112809876\",\n" +
            "            \"extfield__Ntry_NtryDtls_TxDtls_Refs_AcctSvcrRef\": \"TXR20251128123456\",\n" +
            "            \"extfield_Ntry_NtryDtls_TxDtls_Refs_EndToEndId\": \"E2E202511280000001234\",\n" +
            "            \"amount\": 38650,\n" +
            "            \"purpose\": \"支付 2025 年 11 月原材料采购款\"\n" +
            "          }\n" +
            "        ]\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";




    public static void main(String[] args) {
        try {
            // 1. 原始报文
            JSONObject rootJson = JSONUtil.parseObj(REQ);

            // 2. 提取 detail 数组（核心流水数据）
            JSONArray detailArray = rootJson.getByPath("mbs.req.list.detail",JSONArray.class);
            if (CollUtil.isEmpty(detailArray)) {
                System.err.println("JSON 报文中未找到 detail 流水数据");
                return;
            }

            // 3. 按 accountnumber 分组 ,同一账号生成一个 Stmt）
            Map<String, List<JSONObject>> accountMap = groupByAccountNumber(detailArray);

            // 4. 初始化 CAMT53 根文档
            Document camt53Doc = DocumentHelper.createDocument();
            Element rootElement = camt53Doc.addElement("Document", CAMT53_NAMESPACE);
            // 绑定默认命名空间，避免 XML 中重复出现命名空间前缀
            Namespace defaultNs = Namespace.get(CAMT53_NAMESPACE);
            rootElement.addNamespace("", CAMT53_NAMESPACE);

            // 5. 添加 CAMT53 核心父节点 BkToCstmrStmt
            Element bkToCstmrStmt = rootElement.addElement("BkToCstmrStmt", CAMT53_NAMESPACE);

            // 6. 生成报文头部 GrpHdr（整个报文唯一，取第一个账号的第一条流水数据）
            List<JSONObject> firstAccountList = CollUtil.getFirst(accountMap.values());
            if (CollUtil.isNotEmpty(firstAccountList)) {
                generateGroupHeader(bkToCstmrStmt, CollUtil.getFirst(firstAccountList));
            }

            // 7. 循环处理每个账号
            int stmtSeq = 1; // 电子序列号，按 Stmt 递增
            for (Map.Entry<String, List<JSONObject>> entry : accountMap.entrySet()) {
                String accountNumber = entry.getKey();
                List<JSONObject> accountList = entry.getValue();

                // 生成单个 Stmt 节点
                Element stmt = bkToCstmrStmt.addElement("Stmt", CAMT53_NAMESPACE);
                // 对账单编号（随机唯一，格式：STMT+时间戳+序列号）
                stmt.addElement("Id", CAMT53_NAMESPACE)
                        .setText(StrUtil.format("STMT_{}_{}", System.currentTimeMillis(), stmtSeq));
                // 电子序列号（防遗漏，严格递增）
                stmt.addElement("ElctrncSeqNb", CAMT53_NAMESPACE)
                        .setText(String.valueOf(stmtSeq));
                stmtSeq++;

                // 7.1 生成 Stmt 固定字段（Ntry 之前的字段，取 accountList 第 0 条）
                JSONObject firstDetail = CollUtil.getFirst(accountList);
                generateStmtFixedFields(stmt, firstDetail);

                // 7.2 生成交易汇总 TxsSummry（基于账号下所有流水的统计信息）
                generateTransactionsSummary(stmt, firstDetail);

                // 7.3 生成交易明细 Ntry（循环 accountList 每条流水，每条对应一个 Ntry）
                generateNtryList(stmt, accountList);
            }

            // 8. 输出 CAMT53 报文（可写入文件或直接传输）
            String camt53Xml = camt53Doc.asXML();
            System.out.println("=== 生成的 CAMT53 报文 ===");
            System.out.println(camt53Xml);

            // （可选）将报文写入文件
            // FileWriter writer = new FileWriter("生成的CAMT53报文.xml");
            // writer.write(camt53Xml);
            // writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 核心方法①：按 accountnumber 对 JSON 流水数组分组
     * @param detailArray JSON 中的 detail 数组
     * @return 分组后的 Map（key：账号，value：该账号的所有流水）
     */
    private static Map<String, List<JSONObject>> groupByAccountNumber(JSONArray detailArray) {
        Map<String, List<JSONObject>> accountMap = new HashMap<>();
        for (int i = 0; i < detailArray.size(); i++) {
            JSONObject detail = detailArray.getJSONObject(i);
            // 获取账号（若账号为空，跳过该条流水）
            String accountNumber = detail.getStr("accountnumber", "");
            if (StrUtil.isEmpty(accountNumber)) {
                System.err.println("跳过无账号的流水数据：" + detail);
                continue;
            }
            // 按账号分组，相同账号的流水放入同一 List
            accountMap.computeIfAbsent(accountNumber, k -> new ArrayList<>()).add(detail);
        }
        return accountMap;
    }

    /**
     * 生成报文头部 GrpHdr（CAMT53 标准结构）
     * @param parent 父节点（BkToCstmrStmt）
     * @param firstDetail 第一个账号的第一条流水（用于取头部字段）
     */
    private static void generateGroupHeader(Element parent, JSONObject firstDetail) {
        Element grpHdr = parent.addElement("GrpHdr", CAMT53_NAMESPACE);

        // 1. 报文唯一标识 MsgId（随机生成，格式：MSG+时间戳）
        grpHdr.addElement("MsgId", CAMT53_NAMESPACE)
                .setText(StrUtil.format("MSG_{}", System.currentTimeMillis()));

        // 2. 报文创建时间 CreDtTm（从 JSON 取 extfield_GrpHdr_CreDtTm）
        String creDtTm = firstDetail.getStr("extfield_GrpHdr_CreDtTm", "2025-11-28T09:45:32.123Z");
        grpHdr.addElement("CreDtTm", CAMT53_NAMESPACE).setText(creDtTm);

        // 3. 报文接收方 MsgRcpt（从 JSON 取 extfield_GrpHdr_MsgRcpt_Nm）
        Element msgRcpt = grpHdr.addElement("MsgRcpt", CAMT53_NAMESPACE);
        msgRcpt.addElement("Nm", CAMT53_NAMESPACE)
                .setText(firstDetail.getStr("extfield_GrpHdr_MsgRcpt_Nm", "未知接收方"));

        // 4. 附加信息 AddtlInf（从 JSON 取 extfield_GrpHdr_AddtlInf）
        grpHdr.addElement("AddtlInf", CAMT53_NAMESPACE)
                .setText(firstDetail.getStr("extfield_GrpHdr_AddtlInf", ""));
    }

    /**
     * 生成 Stmt 固定字段（Ntry 之前的字段，取 accountList 第 0 条流水）
     * 对应 CSV 规则中 "Document/BkToCstmrStmt/Stmt/Ntry/NtryRef" 之前的所有字段
     */
    private static void generateStmtFixedFields(Element stmt, JSONObject detail) {
        // 1. 对账单生成时间 CreDtTm（从 JSON 取 extfield_CreDtTm）
        String creDtTm = detail.getStr("extfield_CreDtTm", "2025-11-28T09:40:15.678Z");
        stmt.addElement("CreDtTm", CAMT53_NAMESPACE).setText(creDtTm);

        // 2. 对账起止时间 FrToDt（从 JSON 取 tradedatetime，起止时间相同）
        String tradeDateTime = detail.getStr("tradedatetime", "2025-11-28T10:05:23.456Z");
        Element frToDt = stmt.addElement("FrToDt", CAMT53_NAMESPACE);
        frToDt.addElement("FrDtTm", CAMT53_NAMESPACE).setText(tradeDateTime);
        frToDt.addElement("ToDtTm", CAMT53_NAMESPACE).setText(tradeDateTime);

        // 3. 报告来源 RptgSrc（从 JSON 取 extfield_RptgSrc_Cd）
        Element rptgSrc = stmt.addElement("RptgSrc", CAMT53_NAMESPACE);
        rptgSrc.addElement("Cd", CAMT53_NAMESPACE)
                .setText(detail.getStr("extfield_RptgSrc_Cd", "ONLINE_BANK"));

        // 4. 账户信息 Acct（核心字段，映射账号、币种、账户名等）
        Element acct = stmt.addElement("Acct", CAMT53_NAMESPACE);
        // 4.1 账号标识 Id/Othr（从 JSON 取 accountnumber 和 extfield_Acct_Id_Othr_SchmeNm_Prtry）
        Element id = acct.addElement("Id", CAMT53_NAMESPACE);
        Element othr = id.addElement("Othr", CAMT53_NAMESPACE);
        othr.addElement("Id", CAMT53_NAMESPACE)
                .setText(detail.getStr("accountnumber", ""));
        Element schmeNm = othr.addElement("SchmeNm", CAMT53_NAMESPACE);
        schmeNm.addElement("Prtry", CAMT53_NAMESPACE)
                .setText(detail.getStr("extfield_Acct_Id_Othr_SchmeNm_Prtry", "CNAPS"));

        // 4.2 账户币种 Ccy（从 JSON 取 extfield_Acct_Ccy）
        acct.addElement("Ccy", CAMT53_NAMESPACE)
                .setText(detail.getStr("extfield_Acct_Ccy", "CNY"));

        // 4.3 账户名称 Nm（从 JSON 取 accountname）
        acct.addElement("Nm", CAMT53_NAMESPACE)
                .setText(detail.getStr("accountname", ""));

        // 4.4 账户所有人 Ownr/Nm（从 JSON 取 accountname，与账户名一致）
        Element ownr = acct.addElement("Ownr", CAMT53_NAMESPACE);
        ownr.addElement("Nm", CAMT53_NAMESPACE)
                .setText(detail.getStr("accountname", ""));

        // 5. 开户银行信息 Acct/Svcr（映射银行名、联行号、分行信息）
        Element svcr = acct.addElement("Svcr", CAMT53_NAMESPACE);
        // 5.1 银行名称 FinInstnId/Nm（从 JSON 取 extfield_bankname）
        Element finInstnId = svcr.addElement("FinInstnId", CAMT53_NAMESPACE);
        finInstnId.addElement("Nm", CAMT53_NAMESPACE)
                .setText(detail.getStr("extfield_bankname", "中国工商银行杭州西湖支行"));

        // 5.2 银行联行号 ClrSysMmbId/MmbId（从 JSON 取 extfield_bankcode）
        Element clrSysMmbId = finInstnId.addElement("ClrSysMmbId", CAMT53_NAMESPACE);
        clrSysMmbId.addElement("MmbId", CAMT53_NAMESPACE)
                .setText(detail.getStr("extfield_bankcode", "102331002567"));

        // 5.3 分行信息 BrnchId（从 JSON 取 extfield_lcoationkcode 和 extfield_lcoationkname）
        Element brnchId = svcr.addElement("BrnchId", CAMT53_NAMESPACE);
        brnchId.addElement("Id", CAMT53_NAMESPACE)
                .setText(detail.getStr("extfield_lcoationkcode", "330106"));
        brnchId.addElement("Nm", CAMT53_NAMESPACE)
                .setText(detail.getStr("extfield_lcoationkname", "杭州市西湖区"));

        // 6. 余额信息 Bal（从 JSON 取余额相关字段）
        // 期初余额
        Element bal = stmt.addElement("Bal", CAMT53_NAMESPACE);
        // 6.1 余额类型 Tp/CdOrPrtry/Cd（从 JSON 取 extfield_Bal_Tp_CdOrPrtry_Cd）
        Element tp = bal.addElement("Tp", CAMT53_NAMESPACE);
        Element cdOrPrtry = tp.addElement("CdOrPrtry", CAMT53_NAMESPACE);
        cdOrPrtry.addElement("Cd", CAMT53_NAMESPACE)
                .setText(detail.getStr("extfield_Bal_Tp_CdOrPrtry_Cd", "CLBD"));

        // 6.2 余额金额 Amt（从 JSON 取 extfield_Bal_Amt，添加币种属性）
        Element balAmt = bal.addElement("Amt", CAMT53_NAMESPACE);
        balAmt.setText(detail.getStr("extfield_Bal_Amt", "158260.75"));
        balAmt.addAttribute("Ccy", detail.getStr("extfield_Acct_Ccy", "CNY"));


        // 6.3 借贷标识 CdtDbtInd（从 JSON 取 extfield_Bal_CdtDbtInd）
        bal.addElement("CdtDbtInd", CAMT53_NAMESPACE)
                .setText(detail.getStr("extfield_Bal_CdtDbtInd", "CRDT"));

        // 6.4 余额日期 Dt/Dt（从 JSON 取 tradedatetime，截取日期部分）
        Element balDt = bal.addElement("Dt", CAMT53_NAMESPACE);
        String balDate = StrUtil.split(detail.getStr("tradedatetime", "2025-11-28T10:05:23.456Z"), "T")[0];
        balDt.addElement("Dt", CAMT53_NAMESPACE).setText(balDate);

        // 期末余额
        Element bal2 = stmt.addElement("Bal", CAMT53_NAMESPACE);
        // 6.1 余额类型 Tp/CdOrPrtry/Cd（从 JSON 取 extfield_Bal_Tp_CdOrPrtry_Cd）
        Element tp2 = bal2.addElement("Tp", CAMT53_NAMESPACE);
        Element cdOrPrtry2 = tp2.addElement("CdOrPrtry", CAMT53_NAMESPACE);
        cdOrPrtry2.addElement("Cd", CAMT53_NAMESPACE)
                .setText(detail.getStr("extfield_Bal_Tp_CdOrPrtry_Cd", "CLBD"));

        // 6.2 余额金额 Amt（从 JSON 取 extfield_Bal_Amt，添加币种属性）
        Element balAmt2 = bal2.addElement("Amt", CAMT53_NAMESPACE);
        balAmt2.setText(detail.getStr("extfield_Bal_Amt", "158260.75"));
        balAmt2.addAttribute("Ccy", detail.getStr("extfield_Acct_Ccy", "CNY"));


        // 6.3 借贷标识 CdtDbtInd（从 JSON 取 extfield_Bal_CdtDbtInd）
        bal2.addElement("CdtDbtInd", CAMT53_NAMESPACE)
                .setText(detail.getStr("extfield_Bal_CdtDbtInd", "CRDT"));

        // 6.4 余额日期 Dt/Dt（从 JSON 取 tradedatetime，截取日期部分）
        Element balDt2 = bal2.addElement("Dt", CAMT53_NAMESPACE);
        String balDate2 = StrUtil.split(detail.getStr("tradedatetime", "2025-11-28T10:05:23.456Z"), "T")[0];
        balDt2.addElement("Dt", CAMT53_NAMESPACE).setText(balDate2);
    }

    /**
     * 生成交易汇总 TxsSummry（按 CSV 规则映射 TxsSummry1~TxsSummry6）
     */
    private static void generateTransactionsSummary(Element stmt, JSONObject firstDetail) {
        Element txsSummry = stmt.addElement("TxsSummry", CAMT53_NAMESPACE);

        // 1. 总交易统计 TtlNtries（映射 TxsSummry1：单笔最大金额）
        Element ttlNtries = txsSummry.addElement("TtlNtries", CAMT53_NAMESPACE);
        // 总流水条数（从 JSON 取 Sum 流水条数）
        ttlNtries.addElement("NbOfNtries", CAMT53_NAMESPACE)
                .setText(firstDetail.getStr("Sum 流水条数", "0"));
        // 单笔最大金额（截取 TxsSummry1 中的数值，如 "单笔最大金额：52800.00" → "52800.00"）
        String maxAmt = StrUtil.subAfter(firstDetail.getStr("TxsSummry1", "单笔最大金额：0.00"), "：", false);
        ttlNtries.addElement("Sum", CAMT53_NAMESPACE).setText(maxAmt);

        // 2. 净交易金额 TtlNetNtryAmt（映射 TxsSummry2：单笔最小金额）
        Element ttlNetNtryAmt = ttlNtries.addElement("TtlNetNtryAmt", CAMT53_NAMESPACE);
        String minAmt = StrUtil.subAfter(firstDetail.getStr("TxsSummry2", "单笔最小金额：0.00"), "：", false);
        ttlNetNtryAmt.setText(minAmt);

        // 3. 贷方交易汇总 TtlCdtNtries（映射 TxsSummry3：贷方笔数，TxsSummry4：借方笔数→此处按 CSV 规则映射）
        Element ttlCdtNtries = txsSummry.addElement("TtlCdtNtries", CAMT53_NAMESPACE);
        String cdtNb = StrUtil.subAfter(firstDetail.getStr("TxsSummry3", "贷方交易笔数：0"), "：", false);
        ttlCdtNtries.addElement("NbOfNtries", CAMT53_NAMESPACE).setText(cdtNb);
        String cdtSum = StrUtil.subAfter(firstDetail.getStr("TxsSummry4", "借方交易笔数：0"), "：", false);
        ttlCdtNtries.addElement("Sum", CAMT53_NAMESPACE).setText(cdtSum);

        // 4. 借方交易汇总 TtlDbtNtries（映射 TxsSummry5：对手方数量，TxsSummry6：平均金额→此处按 CSV 规则映射）
        Element ttlDbtNtries = txsSummry.addElement("TtlDbtNtries", CAMT53_NAMESPACE);
        String dbtNb = StrUtil.subAfter(firstDetail.getStr("TxsSummry5", "交易对手方数量：0"), "：", false);
        ttlDbtNtries.addElement("NbOfNtries", CAMT53_NAMESPACE).setText(dbtNb);
        String dbtSum = StrUtil.subAfter(firstDetail.getStr("TxsSummry6", "平均交易金额：0.00"), "：", false);
        ttlDbtNtries.addElement("Sum", CAMT53_NAMESPACE).setText(dbtSum);
    }

    /**
     * 生成交易明细 Ntry（循环账号下所有流水，每条流水对应一个 Ntry）
     * 对应 CSV 规则中 "Document/BkToCstmrStmt/Stmt/Ntry/NtryRef" 及之后的字段
     */
    private static void generateNtryList(Element stmt, List<JSONObject> accountList) {
        for (JSONObject detail : accountList) {
            Element ntry = stmt.addElement("Ntry", CAMT53_NAMESPACE);

            // 1. 交易引用号 NtryRef（从 JSON 取 extfield_Ntry_NtryRef）
            ntry.addElement("NtryRef", CAMT53_NAMESPACE)
                    .setText(detail.getStr("extfield_Ntry_NtryRef", "NTRY202511280015"));

            // 2. 交易金额 Amt（从 JSON 取 extfield_Ntry_Amt，添加币种属性）
            Element ntryAmt = ntry.addElement("Amt", CAMT53_NAMESPACE);
            ntryAmt.setText(detail.getStr("extfield_Ntry_Amt", "38650"));
            ntryAmt.addAttribute("Ccy", detail.getStr("extfield_Acct_Ccy", "CNY"));

            // 3. 借贷标识 CdtDbtInd（从 JSON 取 moneyway，TRANSFER 映射为 CRDT）
            String moneyway = detail.getStr("moneyway", "TRANSFER");
            ntry.addElement("CdtDbtInd", CAMT53_NAMESPACE)
                    .setText(StrUtil.equals(moneyway, "TRANSFER") ? "CRDT" : "DBIT");

            // 4. 交易状态 Sts（从 JSON 取 extfield_Ntry_Sts）
            ntry.addElement("Sts", CAMT53_NAMESPACE)
                    .setText(detail.getStr("extfield_Ntry_Sts", "BOOKED"));

            // 5. 记账日期 BookgDt/Dt（从 JSON 取 tradedatetime，截取日期）
            String tradeDate = StrUtil.split(detail.getStr("tradedatetime", "2025-11-28T10:05:23.456Z"), "T")[0];
            Element bookgDt = ntry.addElement("BookgDt", CAMT53_NAMESPACE);
            bookgDt.addElement("Dt", CAMT53_NAMESPACE).setText(tradeDate);

            // 6. 起息日期 ValDt/Dt（与记账日期一致）
            Element valDt = ntry.addElement("ValDt", CAMT53_NAMESPACE);
            valDt.addElement("Dt", CAMT53_NAMESPACE).setText(tradeDate);

            // 7. 银行交易参考号 AcctSvcrRef（从 JSON 取 extfield_Ntry_AcctSvcrRef）
            ntry.addElement("AcctSvcrRef", CAMT53_NAMESPACE)
                    .setText(detail.getStr("extfield_Ntry_AcctSvcrRef", "ACTSVCR2025112809876"));

            // todo 完善

            // 8. 交易详情 NtryDtls/TxDtls（映射参考号、交易金额明细）
            Element ntryDtls = ntry.addElement("NtryDtls", CAMT53_NAMESPACE);
            Element txDtls = ntryDtls.addElement("TxDtls", CAMT53_NAMESPACE);

            // 8.1 参考信息 Refs（从 JSON 取两个参考号字段）
            Element refs = txDtls.addElement("Refs", CAMT53_NAMESPACE);
            refs.addElement("AcctSvcrRef", CAMT53_NAMESPACE)
                    .setText(detail.getStr("extfield__Ntry_NtryDtls_TxDtls_Refs_AcctSvcrRef", "TXR20251128123456"));
            refs.addElement("EndToEndId", CAMT53_NAMESPACE)
                    .setText(detail.getStr("extfield_Ntry_NtryDtls_TxDtls_Refs_EndToEndId", "E2E202511280000001234"));

            // 8.2 交易金额明细 AmtDtls/TxAmt/Amt（从 JSON 取 amount）
            Element amtDtls = txDtls.addElement("AmtDtls", CAMT53_NAMESPACE);
            Element txAmt = amtDtls.addElement("TxAmt", CAMT53_NAMESPACE);
            Element txAmtVal = txAmt.addElement("Amt", CAMT53_NAMESPACE);
            txAmtVal.setText(detail.getStr("amount", "38650"));
            txAmtVal.addAttribute("Ccy", detail.getStr("extfield_Acct_Ccy", "CNY"));

            // 9. 交易附言 AddtlNtryInf（从 JSON 取 purpose）
            ntry.addElement("AddtlNtryInf", CAMT53_NAMESPACE)
                    .setText(detail.getStr("purpose", "支付 2025 年 11 月原材料采购款"));
        }
    }
}