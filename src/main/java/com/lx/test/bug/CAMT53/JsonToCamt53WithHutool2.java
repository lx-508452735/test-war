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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonToCamt53WithHutool2 {
    private static final String CAMT53_NAMESPACE = "urn:iso:std:iso:20022:tech:xsd:camt.053.001.02";

    private static final String ELEMENT_DOCUMENT = "Document";
    private static final String ELEMENT_BK_TO_CSTMR_STMT = "BkToCstmrStmt";
    private static final String ELEMENT_GRP_HDR = "GrpHdr";
    private static final String ELEMENT_MSG_ID = "MsgId";
    private static final String ELEMENT_CRE_DT_TM = "CreDtTm";
    private static final String ELEMENT_MSG_RCPT = "MsgRcpt";
    private static final String ELEMENT_NM = "Nm";
    private static final String ELEMENT_ADDTL_INF = "AddtlInf";
    private static final String ELEMENT_STMT = "Stmt";
    private static final String ELEMENT_ID = "Id";
    private static final String ELEMENT_ELCTRNC_SEQ_NB = "ElctrncSeqNb";
    private static final String ELEMENT_FR_TO_DT = "FrToDt";
    private static final String ELEMENT_FR_DT_TM = "FrDtTm";
    private static final String ELEMENT_TO_DT_TM = "ToDtTm";
    private static final String ELEMENT_RPTG_SRC = "RptgSrc";
    private static final String ELEMENT_CD = "Cd";
    private static final String ELEMENT_ACCT = "Acct";
    private static final String ELEMENT_OTHR = "Othr";
    private static final String ELEMENT_SCHME_NM = "SchmeNm";
    private static final String ELEMENT_PRTRY = "Prtry";
    private static final String ELEMENT_CCY = "Ccy";
    private static final String ELEMENT_OWNR = "Ownr";
    private static final String ELEMENT_SVCR = "Svcr";
    private static final String ELEMENT_FIN_INSTN_ID = "FinInstnId";
    private static final String ELEMENT_CLR_SYS_MMB_ID = "ClrSysMmbId";
    private static final String ELEMENT_MMB_ID = "MmbId";
    private static final String ELEMENT_BRNCH_ID = "BrnchId";
    private static final String ELEMENT_BAL = "Bal";
    private static final String ELEMENT_TP = "Tp";
    private static final String ELEMENT_CD_OR_PRTRY = "CdOrPrtry";
    private static final String ELEMENT_AMT = "Amt";
    private static final String ELEMENT_CDT_DBT_IND = "CdtDbtInd";
    private static final String ELEMENT_DT = "Dt";
    private static final String ELEMENT_TXS_SUMMRY = "TxsSummry";
    private static final String ELEMENT_TTL_NTRIES = "TtlNtries";
    private static final String ELEMENT_NB_OF_NTRIES = "NbOfNtries";
    private static final String ELEMENT_SUM = "Sum";
    private static final String ELEMENT_TTL_NET_NTRY_AMT = "TtlNetNtryAmt";
    private static final String ELEMENT_TTL_CDT_NTRIES = "TtlCdtNtries";
    private static final String ELEMENT_TTL_DBT_NTRIES = "TtlDbtNtries";
    private static final String ELEMENT_NTRY = "Ntry";
    private static final String ELEMENT_NTRY_REF = "NtryRef";
    private static final String ELEMENT_STS = "Sts";
    private static final String ELEMENT_BOOKG_DT = "BookgDt";
    private static final String ELEMENT_VAL_DT = "ValDt";
    private static final String ELEMENT_ACCT_SVCR_REF = "AcctSvcrRef";
    private static final String ELEMENT_NTRY_DTLS = "NtryDtls";
    private static final String ELEMENT_TX_DTLS = "TxDtls";
    private static final String ELEMENT_REFS = "Refs";
    private static final String ELEMENT_END_TO_END_ID = "EndToEndId";
    private static final String ELEMENT_AMT_DTLS = "AmtDtls";
    private static final String ELEMENT_TX_AMT = "TxAmt";
    private static final String ELEMENT_ADDTL_NTRY_INF = "AddtlNtryInf";

    private static final String JSON_FIELD_MBS_REQ_LIST_DETAIL = "mbs.req.list.detail";
    private static final String JSON_FIELD_ACCOUNT_NUMBER = "accountnumber";
    private static final String JSON_FIELD_EXT_GRP_HDR_MSG_ID = "mbs.pub.srcbatchno";
    private static final String JSON_FIELD_EXT_GRP_HDR_CRE_DT_TM = "mbs.pub.transdatetime";
    private static final String JSON_FIELD_EXT_GRP_HDR_MSG_RCPT_NM = "mbs.pub.srcoutsystemcode";
    private static final String JSON_FIELD_EXT_GRP_HDR_ADDTL_INF = "mbs.pub.version";
    private static final String JSON_FIELD_EXT_CRE_DT_TM = "extfield_CreDtTm";
    private static final String JSON_FIELD_TRADE_DATE = "tradedate";
    private static final String JSON_FIELD_TRADE_DATETIME_FROM = "from_tradedatetime";
    private static final String JSON_FIELD_TRADE_DATETIME_TO = "to_tradedatetime";
    private static final String JSON_FIELD_EXT_RPTG_SRC_CD = "extfield_RptgSrc_Cd";
    private static final String JSON_FIELD_EXT_ACCT_ID_OTHR_SCHME_NM_PRTRY = "extfield_Acct_Id_Othr_SchmeNm_Prtry";
    private static final String JSON_FIELD_EXT_ACCT_CCY = "extfield_Acct_Ccy";
    private static final String JSON_FIELD_ACCOUNT_NAME = "accountname";
    private static final String JSON_FIELD_EXT_BANK_NAME = "extfield_bankname";
    private static final String JSON_FIELD_EXT_BANK_CODE = "extfield_bankcode";
    private static final String JSON_FIELD_EXT_LOCATION_CODE = "extfield_lcoationkcode";
    private static final String JSON_FIELD_EXT_LOCATION_NAME = "extfield_lcoationkname";
    private static final String JSON_FIELD_EXT_BAL_TP_CD_OR_PRTRY_CD_OPBD = "OPBD";
    private static final String JSON_FIELD_EXT_BAL_TP_CD_OR_PRTRY_CD_CLBD = "CLBD";
    private static final String JSON_FIELD_EXT_BAL_AMT_OPBD = "extfield_Bal_Amt_Opbd";
    private static final String JSON_FIELD_EXT_BAL_AMT_CLBD = "extfield_Bal_Amt_Clbd";
    private static final String JSON_FIELD_EXT_BAL_CDT_DBT_IND = "extfield_Bal_CdtDbtInd";
    private static final String JSON_FIELD_EXT_NTRY_NTRY_REF = "extfield_Ntry_NtryRef";
    private static final String JSON_FIELD_MONEY_WAY = "moneyway";
    private static final String JSON_FIELD_EXT_NTRY_STS = "extfield_Ntry_Sts";
    private static final String JSON_FIELD_EXT_NTRY_ACCT_SVCR_REF = "extfield_Ntry_AcctSvcrRef";
    private static final String JSON_FIELD_EXT_NTRY_TX_DTLS_REFS_ACCT_SVCR_REF = "ntryDtls_TxDtls_Refs_AcctSvcrRef";
    private static final String JSON_FIELD_EXT_NTRY_TX_DTLS_REFS_END_TO_END_ID = "ntryDtls_TxDtls_Refs_EndToEndId";
    private static final String JSON_FIELD_AMOUNT = "amount";
    private static final String JSON_FIELD_PURPOSE = "purpose";
    private static final String NODE_BK_TX_CD = "BkTxCd";
    private static final String NODE_DOMN = "Domn";
    private static final String NODE_FMLY = "Fmly";
    private static final String NODE_PRTRY = "Prtry";
    private static final String NODE_CD = "Cd";
    private static final String NODE_SUB_FMLY_CD = "SubFmlyCd";
    private static final String NODE_ISSR = "Issr";

    private static final String CONFIG_DOMN_CD = "extfield_Ntry_BkTxCd_Domn_Cd";
    private static final String CONFIG_FMLY_CD = "extfield_Ntry_BkTxCd_Domn_Fmly_Cd";
    private static final String CONFIG_SUB_FMLY_CD = "extfield_Ntry_BkTxCd_Domn_Fmly_SubFmlyCd"; // 保留原拼写
    private static final String CONFIG_PRTRY_CD = "extfield_Ntry_BkTxCd_Prtry_Cd";
    private static final String CONFIG_PRTRY_ISSR = "extfield_Ntry_BkTxCd_Prtry_Issr"; // 保留原拼写

    private static final String CRDT = "CRDT";
    private static final String DBIT = "DBIT";
    private static final String STMT_PREFIX = "STMT";
    private static final String MONEYWAY_EXPENSE = "1";
    private static final String MONEYWAY_INCOME = "2";
    private static final BigDecimal ZERO = BigDecimal.ZERO;

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
            // 原始报文
            JSONObject rootJson = JSONUtil.parseObj(REQ);

            // 提取 detail 数组（核心流水数据）
            JSONArray detailArray = rootJson.getByPath(JSON_FIELD_MBS_REQ_LIST_DETAIL, JSONArray.class);
            if (CollUtil.isEmpty(detailArray)) {
                // todo 替换log
                System.err.println("JSON 报文中未找到 detail 流水数据");
                return;
            }

            // 按 accountnumber 分组 ,同一账号生成一个 Stmt）
            Map<String, List<JSONObject>> accountMap = groupByAccountNumber(detailArray);

            // 初始化 CAMT53 根文档
            Document camt53Doc = DocumentHelper.createDocument();
            Element rootElement = camt53Doc.addElement(ELEMENT_DOCUMENT, CAMT53_NAMESPACE);

            // 绑定默认命名空间，避免 XML 中重复出现命名空间前缀
            Namespace defaultNs = Namespace.get(CAMT53_NAMESPACE);
            // todo 空格
            rootElement.addNamespace("", CAMT53_NAMESPACE);

            // 添加 CAMT53 核心父节点 BkToCstmrStmt
            Element bkToCstmrStmt = rootElement.addElement(ELEMENT_BK_TO_CSTMR_STMT, CAMT53_NAMESPACE);

            // 生成报文头部 GrpHdr（整个报文唯一，取第一个账号的第一条流水数据）
            generateGroupHeader(bkToCstmrStmt, rootJson);

            // 循环处理每个账号, 电子序列号，按 Stmt 递增
            // todo 常量
            int stmtSeq = 1;
            long timeMillis = System.currentTimeMillis();
            for (Map.Entry<String, List<JSONObject>> entry : accountMap.entrySet()) {
                String accountNumber = entry.getKey();
                System.out.println("当前正在处理账号: " + accountNumber);
                List<JSONObject> accountList = entry.getValue();

                // 生成单个 Stmt 节点
                Element stmt = bkToCstmrStmt.addElement(ELEMENT_STMT, CAMT53_NAMESPACE);

                // 对账单编号（随机唯一，格式：STMT+时间戳+序列号）
                stmt.addElement(ELEMENT_ID, CAMT53_NAMESPACE)
                        .setText(StrUtil.format("{}_{}_{}", STMT_PREFIX, timeMillis, stmtSeq));

                // 电子序列号（防遗漏，严格递增）
                stmt.addElement(ELEMENT_ELCTRNC_SEQ_NB, CAMT53_NAMESPACE)
                        .setText(String.valueOf(timeMillis));
                stmtSeq++;
                timeMillis++;

                // 生成 Stmt 固定字段（Ntry 之前的字段，取 accountList 第 0 条）
                JSONObject firstDetail = CollUtil.getFirst(accountList);
                generateStmtFixedFields(stmt, firstDetail);

                // 生成交易汇总 TxsSummry（基于账号下所有流水的统计信息）
                generateTransactionsSummary(stmt, accountList);

                // 生成交易明细 Ntry（循环 accountList 每条流水，每条对应一个 Ntry）
                generateNtryList(stmt, accountList);
            }

            // 输出 CAMT53 报文（可写入文件或直接传输）
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
     * 核心方法：按 accountnumber 对 JSON 流水数组分组
     * @param detailArray JSON 中的 detail 数组
     * @return 分组后的 Map（key：账号，value：该账号的所有流水）
     */
    private static Map<String, List<JSONObject>> groupByAccountNumber(JSONArray detailArray) {
        Map<String, List<JSONObject>> accountMap = new HashMap<>();
        for (int i = 0; i < detailArray.size(); i++) {
            JSONObject detail = detailArray.getJSONObject(i);

            // 获取账号（若账号为空，跳过该条流水）
            String accountNumber = detail.getStr(JSON_FIELD_ACCOUNT_NUMBER, "");
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
        Element grpHdr = parent.addElement(ELEMENT_GRP_HDR, CAMT53_NAMESPACE);

        // 报文唯一标识 MsgId
        String msgId = firstDetail.getStr(JSON_FIELD_EXT_GRP_HDR_MSG_ID, "");
        grpHdr.addElement(ELEMENT_MSG_ID, CAMT53_NAMESPACE)
                .setText(msgId);

        // 报文创建时间 CreDtTm
        String creDtTm = firstDetail.getStr(JSON_FIELD_EXT_GRP_HDR_CRE_DT_TM, "");
        grpHdr.addElement(ELEMENT_CRE_DT_TM, CAMT53_NAMESPACE).setText(creDtTm);

        // 报文接收方 MsgRcpt
        Element msgRcpt = grpHdr.addElement(ELEMENT_MSG_RCPT, CAMT53_NAMESPACE);
        msgRcpt.addElement(ELEMENT_NM, CAMT53_NAMESPACE)
                .setText(firstDetail.getStr(JSON_FIELD_EXT_GRP_HDR_MSG_RCPT_NM, ""));

        // 附加信息 AddtlInf
        grpHdr.addElement(ELEMENT_ADDTL_INF, CAMT53_NAMESPACE)
                .setText(firstDetail.getStr(JSON_FIELD_EXT_GRP_HDR_ADDTL_INF, ""));
    }

    /**
     * 生成 Stmt 固定字段（Ntry 之前的字段，取 accountList 第 0 条流水）
     * 对应 CSV 规则中 "Document/BkToCstmrStmt/Stmt/Ntry/NtryRef" 之前的所有字段
     */
    private static void generateStmtFixedFields(Element stmt, JSONObject detail) {
        // 对账单生成时间 CreDtTm（从 JSON 取 extfield_CreDtTm）
        String creDtTm = detail.getStr(JSON_FIELD_EXT_CRE_DT_TM, "");
        stmt.addElement(ELEMENT_CRE_DT_TM, CAMT53_NAMESPACE).setText(creDtTm);

        // 对账起止时间 FrToDt（从 JSON 取 tradedatetime，起止时间相同）
        String fromTradeDateTime = detail.getStr(JSON_FIELD_TRADE_DATETIME_FROM, "");
        String toTradeDateTime = detail.getStr(JSON_FIELD_TRADE_DATETIME_TO, "");
        Element frToDt = stmt.addElement(ELEMENT_FR_TO_DT, CAMT53_NAMESPACE);
        frToDt.addElement(ELEMENT_FR_DT_TM, CAMT53_NAMESPACE).setText(fromTradeDateTime);
        frToDt.addElement(ELEMENT_TO_DT_TM, CAMT53_NAMESPACE).setText(toTradeDateTime);

        // 报告来源 RptgSrc（从 JSON 取 extfield_RptgSrc_Cd）
        Element rptgSrc = stmt.addElement(ELEMENT_RPTG_SRC, CAMT53_NAMESPACE);
        rptgSrc.addElement(ELEMENT_CD, CAMT53_NAMESPACE)
                .setText(detail.getStr(JSON_FIELD_EXT_RPTG_SRC_CD, ""));

        // 账户信息 Acct（核心字段，映射账号、币种、账户名等）
        Element acct = stmt.addElement(ELEMENT_ACCT, CAMT53_NAMESPACE);

        // 账号标识 Id/Othr（从 JSON 取 accountnumber 和 extfield_Acct_Id_Othr_SchmeNm_Prtry）
        Element id = acct.addElement(ELEMENT_ID, CAMT53_NAMESPACE);
        Element othr = id.addElement(ELEMENT_OTHR, CAMT53_NAMESPACE);
        othr.addElement(ELEMENT_ID, CAMT53_NAMESPACE)
                .setText(detail.getStr(JSON_FIELD_ACCOUNT_NUMBER, ""));
        Element schmeNm = othr.addElement(ELEMENT_SCHME_NM, CAMT53_NAMESPACE);
        schmeNm.addElement(ELEMENT_PRTRY, CAMT53_NAMESPACE)
                .setText(detail.getStr(JSON_FIELD_EXT_ACCT_ID_OTHR_SCHME_NM_PRTRY, ""));

        // 账户币种 Ccy（从 JSON 取 extfield_Acct_Ccy）
        acct.addElement(ELEMENT_CCY, CAMT53_NAMESPACE)
                .setText(detail.getStr(JSON_FIELD_EXT_ACCT_CCY, ""));

        // 账户名称 Nm（从 JSON 取 accountname）
        acct.addElement(ELEMENT_NM, CAMT53_NAMESPACE)
                .setText(detail.getStr(JSON_FIELD_ACCOUNT_NAME, ""));

        // 账户所有人 Ownr/Nm（从 JSON 取 accountname，与账户名一致）
        Element ownr = acct.addElement(ELEMENT_OWNR, CAMT53_NAMESPACE);
        ownr.addElement(ELEMENT_NM, CAMT53_NAMESPACE)
                .setText(detail.getStr(JSON_FIELD_ACCOUNT_NAME, ""));

        // 开户银行信息 Acct/Svcr（映射银行名、联行号、分行信息）
        Element svcr = acct.addElement(ELEMENT_SVCR, CAMT53_NAMESPACE);

        // 银行名称 FinInstnId/Nm（从 JSON 取 extfield_bankname）
        Element finInstnId = svcr.addElement(ELEMENT_FIN_INSTN_ID, CAMT53_NAMESPACE);
        finInstnId.addElement(ELEMENT_NM, CAMT53_NAMESPACE)
                .setText(detail.getStr(JSON_FIELD_EXT_BANK_NAME, ""));

        // 银行联行号 ClrSysMmbId/MmbId（从 JSON 取 extfield_bankcode）
        Element clrSysMmbId = finInstnId.addElement(ELEMENT_CLR_SYS_MMB_ID, CAMT53_NAMESPACE);
        clrSysMmbId.addElement(ELEMENT_MMB_ID, CAMT53_NAMESPACE)
                .setText(detail.getStr(JSON_FIELD_EXT_BANK_CODE, ""));

        // 分行信息 BrnchId（从 JSON 取 extfield_lcoationkcode 和 extfield_lcoationkname）
        Element brnchId = svcr.addElement(ELEMENT_BRNCH_ID, CAMT53_NAMESPACE);
        brnchId.addElement(ELEMENT_ID, CAMT53_NAMESPACE)
                .setText(detail.getStr(JSON_FIELD_EXT_LOCATION_CODE, ""));
        brnchId.addElement(ELEMENT_NM, CAMT53_NAMESPACE)
                .setText(detail.getStr(JSON_FIELD_EXT_LOCATION_NAME, ""));

        // 余额信息 Bal（从 JSON 取余额相关字段）
        // 期初余额
        Element bal = stmt.addElement(ELEMENT_BAL, CAMT53_NAMESPACE);

        // 余额类型 Tp/CdOrPrtry/Cd（从 JSON 取 extfield_Bal_Tp_CdOrPrtry_Cd）
        Element tp = bal.addElement(ELEMENT_TP, CAMT53_NAMESPACE);
        Element cdOrPrtry = tp.addElement(ELEMENT_CD_OR_PRTRY, CAMT53_NAMESPACE);
        cdOrPrtry.addElement(ELEMENT_CD, CAMT53_NAMESPACE)
                .setText(JSON_FIELD_EXT_BAL_TP_CD_OR_PRTRY_CD_OPBD);

        // 余额金额 Amt（从 JSON 取 extfield_Bal_Amt，添加币种属性）
        Element balAmt = bal.addElement(ELEMENT_AMT, CAMT53_NAMESPACE);
        balAmt.setText(detail.getStr(JSON_FIELD_EXT_BAL_AMT_OPBD, ""));
        balAmt.addAttribute(ELEMENT_CCY, detail.getStr(JSON_FIELD_EXT_ACCT_CCY, ""));

        // 借贷标识 CdtDbtInd（从 JSON 取 extfield_Bal_CdtDbtInd）
        bal.addElement(ELEMENT_CDT_DBT_IND, CAMT53_NAMESPACE)
                .setText(detail.getStr(JSON_FIELD_EXT_BAL_CDT_DBT_IND, ""));

        // 余额日期 Dt/Dt（从 JSON 取 tradedatetime，截取日期部分）
        Element balDt = bal.addElement(ELEMENT_DT, CAMT53_NAMESPACE);
        String balDate = detail.getStr(JSON_FIELD_TRADE_DATE, "");
        balDt.addElement(ELEMENT_DT, CAMT53_NAMESPACE).setText(balDate);

        // 期末余额
        Element bal2 = stmt.addElement(ELEMENT_BAL, CAMT53_NAMESPACE);

        // 余额类型 Tp/CdOrPrtry/Cd（
        Element tp2 = bal2.addElement(ELEMENT_TP, CAMT53_NAMESPACE);
        Element cdOrPrtry2 = tp2.addElement(ELEMENT_CD_OR_PRTRY, CAMT53_NAMESPACE);
        cdOrPrtry2.addElement(ELEMENT_CD, CAMT53_NAMESPACE)
                .setText(JSON_FIELD_EXT_BAL_TP_CD_OR_PRTRY_CD_CLBD);

        // 余额金额 Amt（从 JSON 取 extfield_Bal_Amt，添加币种属性）
        Element balAmt2 = bal2.addElement(ELEMENT_AMT, CAMT53_NAMESPACE);
        balAmt2.setText(detail.getStr(JSON_FIELD_EXT_BAL_AMT_CLBD, ""));
        balAmt2.addAttribute(ELEMENT_CCY, detail.getStr(JSON_FIELD_EXT_ACCT_CCY, ""));

        // 借贷标识 CdtDbtInd（从 JSON 取 extfield_Bal_CdtDbtInd）
        bal2.addElement(ELEMENT_CDT_DBT_IND, CAMT53_NAMESPACE)
                .setText(detail.getStr(JSON_FIELD_EXT_BAL_CDT_DBT_IND, ""));

        // 余额日期 Dt/Dt（从 JSON 取 tradedatetime，截取日期部分）
        Element balDt2 = bal2.addElement(ELEMENT_DT, CAMT53_NAMESPACE);
        String balDate2 = detail.getStr(JSON_FIELD_TRADE_DATE, "");
        balDt2.addElement(ELEMENT_DT, CAMT53_NAMESPACE).setText(balDate2);
    }

    /**
     * 生成交易汇总 TxsSummry（按 CSV 规则映射 TxsSummry1~TxsSummry6）
     */
    private static void generateTransactionsSummary(Element stmt, List<JSONObject> accountList) {
        // 1. 总交易笔数
        long totalCount = accountList.size();

        // 2. 总交易金额合计（收入为正，支出为负，最终求和）
        BigDecimal totalAmount = accountList.stream()
                // todo 静态引用，下面3处
                .map(JsonToCamt53WithHutool2::getAmount) // 提取并转换金额
                .reduce(ZERO, BigDecimal::add);

        // 3. 贷方交易（收入：moneyway=2）- 笔数 + 金额
        long creditCount = accountList.stream()
                .filter(json -> MONEYWAY_INCOME.equals(json.getStr("moneyway")))
                .count();

        BigDecimal creditAmount = accountList.stream()
                .filter(json -> MONEYWAY_INCOME.equals(json.getStr("moneyway")))
                .map(JsonToCamt53WithHutool2::getAmount)
                .reduce(ZERO, BigDecimal::add);

        // 4. 借方交易（支出：moneyway=1）- 笔数 + 金额
        long debitCount = accountList.stream()
                .filter(json -> MONEYWAY_EXPENSE.equals(json.getStr("moneyway")))
                .count();

        BigDecimal debitAmount = accountList.stream()
                .filter(json -> MONEYWAY_EXPENSE.equals(json.getStr("moneyway")))
                .map(JsonToCamt53WithHutool2::getAmount)
                .reduce(ZERO, BigDecimal::add);

        Element txsSummry = stmt.addElement(ELEMENT_TXS_SUMMRY, CAMT53_NAMESPACE);

        // 总交易统计 TtlNtries（映射 TxsSummry1：单笔最大金额）
        Element ttlNtries = txsSummry.addElement(ELEMENT_TTL_NTRIES, CAMT53_NAMESPACE);

        // 总流水条数（从 JSON 取 Sum 流水条数）
        ttlNtries.addElement(ELEMENT_NB_OF_NTRIES, CAMT53_NAMESPACE)
                .setText(String.valueOf(totalCount));

        // 总流水金额（截取 TxsSummry1 中的数值，如 "单笔最大金额：52800.00" → "52800.00"）
        String allAmt = totalAmount.toString();
        ttlNtries.addElement(ELEMENT_SUM, CAMT53_NAMESPACE).setText(allAmt);

        // 净交易金额 TtlNetNtryAmt（映射 TxsSummry2：单笔最小金额）
        Element ttlNetNtryAmt = ttlNtries.addElement(ELEMENT_TTL_NET_NTRY_AMT, CAMT53_NAMESPACE);
        ttlNetNtryAmt.setText(allAmt);

        // 贷方交易汇总 TtlCdtNtries（映射 TxsSummry3：贷方笔数，TxsSummry4：借方笔数→此处按 CSV 规则映射）
        Element ttlCdtNtries = txsSummry.addElement(ELEMENT_TTL_CDT_NTRIES, CAMT53_NAMESPACE);
        String cdtNb = String.valueOf(creditCount);
        ttlCdtNtries.addElement(ELEMENT_NB_OF_NTRIES, CAMT53_NAMESPACE).setText(cdtNb);
        String cdtSum = creditAmount.toString();
        ttlCdtNtries.addElement(ELEMENT_SUM, CAMT53_NAMESPACE).setText(cdtSum);

        // 借方交易汇总 TtlDbtNtries（映射 TxsSummry5：对手方数量，TxsSummry6：平均金额→此处按 CSV 规则映射）
        Element ttlDbtNtries = txsSummry.addElement(ELEMENT_TTL_DBT_NTRIES, CAMT53_NAMESPACE);
        String dbtNb = String.valueOf(debitCount);
        ttlDbtNtries.addElement(ELEMENT_NB_OF_NTRIES, CAMT53_NAMESPACE).setText(dbtNb);
        String dbtSum = debitAmount.toString();
        ttlDbtNtries.addElement(ELEMENT_SUM, CAMT53_NAMESPACE).setText(dbtSum);
    }

    /**
     * 生成交易明细 Ntry（循环账号下所有流水，每条流水对应一个 Ntry）
     * 对应 CSV 规则中 "Document/BkToCstmrStmt/Stmt/Ntry/NtryRef" 及之后的字段
     */
    private static void generateNtryList(Element stmt, List<JSONObject> accountList) {
        for (JSONObject detail : accountList) {
            Element ntry = stmt.addElement(ELEMENT_NTRY, CAMT53_NAMESPACE);

            // 交易引用号 NtryRef（从 JSON 取 extfield_Ntry_NtryRef）
            ntry.addElement(ELEMENT_NTRY_REF, CAMT53_NAMESPACE)
                    .setText(detail.getStr(JSON_FIELD_EXT_NTRY_NTRY_REF, ""));

            // 交易金额 Amt（从 JSON 取 extfield_Ntry_Amt，添加币种属性）
            Element ntryAmt = ntry.addElement(ELEMENT_AMT, CAMT53_NAMESPACE);
            ntryAmt.setText(detail.getStr(JSON_FIELD_AMOUNT, ""));
            ntryAmt.addAttribute(ELEMENT_CCY, detail.getStr(JSON_FIELD_EXT_ACCT_CCY, ""));

            // 借贷标识 CdtDbtInd（从 JSON 取 moneyway，DBIT=支出（借方交易），CRDT=收入（贷方交易）
            String moneyway = detail.getStr(JSON_FIELD_MONEY_WAY, "");
            moneyway = MONEYWAY_INCOME.equals(moneyway) ? CRDT : DBIT;

            ntry.addElement(ELEMENT_CDT_DBT_IND, CAMT53_NAMESPACE)
                    .setText(moneyway);

            // 交易状态 Sts（从 JSON 取 extfield_Ntry_Sts）
            ntry.addElement(ELEMENT_STS, CAMT53_NAMESPACE)
                    .setText(detail.getStr(JSON_FIELD_EXT_NTRY_STS, ""));

            // 记账日期 BookgDt/Dt（从 JSON 取 tradedatetime，截取日期）
            String tradeDate = detail.getStr(JSON_FIELD_TRADE_DATE, "");
            Element bookgDt = ntry.addElement(ELEMENT_BOOKG_DT, CAMT53_NAMESPACE);
            bookgDt.addElement(ELEMENT_DT, CAMT53_NAMESPACE).setText(tradeDate);

            // 起息日期 ValDt/Dt（与记账日期一致）
            Element valDt = ntry.addElement(ELEMENT_VAL_DT, CAMT53_NAMESPACE);
            valDt.addElement(ELEMENT_DT, CAMT53_NAMESPACE).setText(tradeDate);

            // 银行交易参考号 AcctSvcrRef（从 JSON 取 extfield_Ntry_AcctSvcrRef）
            ntry.addElement(ELEMENT_ACCT_SVCR_REF, CAMT53_NAMESPACE)
                    .setText(detail.getStr(JSON_FIELD_EXT_NTRY_ACCT_SVCR_REF, ""));

            // 业务代码实现
            // 交易类型领域代码：生成 BkTxCd 节点
            Element bkTxCd = ntry.addElement(NODE_BK_TX_CD, CAMT53_NAMESPACE);

            // 领域代码 Domn
            Element domn = bkTxCd.addElement(NODE_DOMN, CAMT53_NAMESPACE);
            String domnCd = detail.getStr(CONFIG_DOMN_CD, "");
            domn.addElement(NODE_CD, CAMT53_NAMESPACE).setText(domnCd);

            // 交易家族代码
            Element fmly = domn.addElement(NODE_FMLY, CAMT53_NAMESPACE);
            String fmlyCd = detail.getStr(CONFIG_FMLY_CD, "");
            fmly.addElement(NODE_CD, CAMT53_NAMESPACE).setText(fmlyCd);

            // 交易子家族代码
            String subFmlyCd = detail.getStr(CONFIG_SUB_FMLY_CD, "");
            fmly.addElement(NODE_SUB_FMLY_CD, CAMT53_NAMESPACE).setText(subFmlyCd);

            // 交易类型自定义代码
            Element prtry = bkTxCd.addElement(NODE_PRTRY, CAMT53_NAMESPACE);
            String prtryCd = detail.getStr(CONFIG_PRTRY_CD, "");
            prtry.addElement(NODE_CD, CAMT53_NAMESPACE).setText(prtryCd);

            // 交易类型说明
            String prtryIssr = detail.getStr(CONFIG_PRTRY_ISSR, "");
            prtry.addElement(NODE_ISSR, CAMT53_NAMESPACE).setText(prtryIssr);

            // 交易详情 NtryDtls/TxDtls（映射参考号、交易金额明细）
            Element ntryDtls = ntry.addElement(ELEMENT_NTRY_DTLS, CAMT53_NAMESPACE);
            Element txDtls = ntryDtls.addElement(ELEMENT_TX_DTLS, CAMT53_NAMESPACE);

            // 参考信息 Refs（从 JSON 取两个参考号字段）
            Element refs = txDtls.addElement(ELEMENT_REFS, CAMT53_NAMESPACE);
            refs.addElement(ELEMENT_ACCT_SVCR_REF, CAMT53_NAMESPACE)
                    .setText(detail.getStr(JSON_FIELD_EXT_NTRY_TX_DTLS_REFS_ACCT_SVCR_REF, ""));
            refs.addElement(ELEMENT_END_TO_END_ID, CAMT53_NAMESPACE)
                    .setText(detail.getStr(JSON_FIELD_EXT_NTRY_TX_DTLS_REFS_END_TO_END_ID, ""));

            // 交易金额明细 AmtDtls/TxAmt/Amt（从 JSON 取 amount）
            Element amtDtls = txDtls.addElement(ELEMENT_AMT_DTLS, CAMT53_NAMESPACE);
            Element txAmt = amtDtls.addElement(ELEMENT_TX_AMT, CAMT53_NAMESPACE);
            Element txAmtVal = txAmt.addElement(ELEMENT_AMT, CAMT53_NAMESPACE);
            txAmtVal.setText(detail.getStr(JSON_FIELD_AMOUNT, ""));
            txAmtVal.addAttribute(ELEMENT_CCY, detail.getStr(JSON_FIELD_EXT_ACCT_CCY, ""));

            // 交易附言 AddtlNtryInf（从 JSON 取 purpose）
            ntry.addElement(ELEMENT_ADDTL_NTRY_INF, CAMT53_NAMESPACE)
                    .setText(detail.getStr(JSON_FIELD_PURPOSE, ""));
        }
    }

    /**
     * 提取 JSONObject 中的 amount 字段，转换为 BigDecimal（处理空值/字符串类型）
     * @param json 单条交易流水
     * @return 金额（空值/异常时返回 0）
     */
    private static BigDecimal getAmount(JSONObject json) {
        try {
            // 处理 amount 为字符串或数字类型的情况
            Object amountObj = json.get("amount");
            if (amountObj == null) {
                return BigDecimal.ZERO;
            }
            // 若字段是字符串，直接转 BigDecimal；数字类型自动兼容
            return new BigDecimal(amountObj.toString());
        } catch (Exception e) {
            // 日志记录异常（可选，便于排查非法数据）
            System.err.println("解析金额失败，流水数据：" + json + "，异常信息：" + e.getMessage());
            return BigDecimal.ZERO;
        }
    }
}