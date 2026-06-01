package com.lx.test.MT940;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * MT940账户对账单报文生成器
 * 遵循渣打银行MT940-UTF8规范，生成包含2个账号、各2条明细的报文
 */
public class MyMT940 {
    private static final Random random = new Random();
    private static final SimpleDateFormat dateFormatYYMMDD = new SimpleDateFormat("yyMMdd");
    private static final SimpleDateFormat dateFormatMMDD = new SimpleDateFormat("MMdd");
    private static final SimpleDateFormat timeFormatHHMM = new SimpleDateFormat("HHmm");

    // 固定值常量
    private static final String BLOCK_1_ID = "{1:";
    private static final String BLOCK_2_ID = "{2:";
    private static final String BLOCK_3_ID = "{3:";
    private static final String BLOCK_4_ID = "{4:";
    private static final String BLOCK_5_ID = "{5:";
    private static final String APPLICATION_IDENTIFIER = "F";
    private static final String SERVICE_IDENTIFIER = "01";
    private static final String INPUT_OUTPUT_IDENTIFIER = "O";
    private static final String MESSAGE_TYPE = "940";
    private static final String MESSAGE_PRIORITY = "N";
    private static final String BANKING_PRIORITY = "0000";
    private static final String ENTRY_METHOD = "N";
    private static final String CHK_TRAILER = "CHECKSUM DISABLED";
    private static final String MAC_TRAILER = "MACCING DISABLED";

    // 示例账号数据
    private static final String[] ACCOUNT_NUMBERS = {
            "3510021-01-4",  // 账号1
            "3510021-01-7"   // 账号2
    };
    private static final String[] CURRENCIES = {"CNY", "USD"};  // 对应账号的货币
    private static final String SWIFT_ADDRESS = "TISGDE5WATKE";  // 示例SWIFT地址

    /**
     * 生成完整的MT940报文
     * @param jsonArray
     */
    public String generateMT940Message(JSONArray jsonArray) {
        Date currentDate = new Date();

        // 生成各块内容
        String block1 = generateBasicHeaderBlock();
        String block2 = generateApplicationHeaderBlock(currentDate);
        String block3 = generateUserHeaderBlock();
        String block4 = generateTextBlock(currentDate);
        String block5 = generateTrailerBlock();

        // 组合完整报文
        return block1 + block2 + block3 + block4 + block5;
    }

    /**
     * 生成基本头块 (Block 1)
     */
    private String generateBasicHeaderBlock() {
        StringBuilder sb = new StringBuilder(BLOCK_1_ID);
        // 应用标识符 + 服务标识符 + LT地址（补充至12位）+
        sb.append(APPLICATION_IDENTIFIER)
                .append(SERVICE_IDENTIFIER)
                .append(SWIFT_ADDRESS)
                .append(generateSessionNumber()) // 会话号
                .append(generateSequenceNumber()); // 序列号
        sb.append("}");
        return sb.toString();
    }

    /**
     * 生成应用头块 (Block 2)
     */
    private String generateApplicationHeaderBlock(Date currentDate) {
        StringBuilder sb = new StringBuilder(BLOCK_2_ID);
        sb.append(INPUT_OUTPUT_IDENTIFIER) // 输入/输出标识符
                .append(MESSAGE_TYPE) // 消息类型
                .append(timeFormatHHMM.format(currentDate)) // 输入时间
                .append(generateMessageInputReference(currentDate)) // 消息输入参考
                .append(dateFormatYYMMDD.format(currentDate)) // 输出日期
                .append(timeFormatHHMM.format(currentDate)) // 输出时间
                .append(MESSAGE_PRIORITY); // 消息优先级
        sb.append("}");
        return sb.toString();
    }

    /**
     * 生成用户头块 (Block 3)
     */
    private String generateUserHeaderBlock() {
        StringBuilder sb = new StringBuilder(BLOCK_3_ID);
        sb.append("{113:").append(BANKING_PRIORITY).append("}") // 银行优先级
                .append("{108:").append(generateMessageUserReference()).append("}"); // 消息用户参考
        sb.append("}");
        return sb.toString();
    }

    /**
     * 生成文本块 (Block 4)，包含2个账号、各2条明细
     */
    private String generateTextBlock(Date currentDate) {
        StringBuilder sb = new StringBuilder(BLOCK_4_ID);
        sb.append("\n"); // CRLF

        // 遍历两个账号生成对账单
        for (int accountIndex = 0; accountIndex < ACCOUNT_NUMBERS.length; accountIndex++) {
            String accountNumber = ACCOUNT_NUMBERS[accountIndex];
            String currency = CURRENCIES[accountIndex];

            // 交易参考号
            sb.append(":20:").append(generateTransactionRefNo(accountIndex)).append("\n");

            // 账户标识
            sb.append(":25:").append(SWIFT_ADDRESS).append("/").append(accountNumber).append("\n");

            // 对账单/页码（每日递增，这里固定为00001/00001）
            sb.append(":28C:00001/00001").append("\n");

            // 期初余额
            sb.append(generateOpeningBalance(currentDate, currency)).append("\n");

            // 生成2条交易明细
            for (int detailIndex = 0; detailIndex < 2; detailIndex++) {
                sb.append(generateStatementLine(currentDate, accountIndex, detailIndex)).append("\n");
            }

            // :86:
            sb.append(addAccountOwnerInfo("PAYMENT DETAILS", "SALARY & RENT", "123",
                    "张三-1234567890", "VA-202310", "111222333444"));
            // 期末余额
            sb.append(generateClosingBalance(currentDate, currency)).append("\n-\n");

        }

        sb.append("-}"); // 文本块结束标记
        return sb.toString();
    }

    /**
     * 添加账户所有者信息（:86:）
     */
    public String addAccountOwnerInfo(String field1, String field2,
                                    String field3, String field4, String field5, String field6) {
        StringBuilder sb = new StringBuilder(":86:");
        sb.append(field1).append("\n")
                .append(field2).append("\n")
                .append(field3).append("\n")
                .append(field4).append("\n")
                .append(field5).append("\n")
                .append(field6).append("\n");

        return sb.toString();
    }

    /**
     * 生成 Trailer 块 (Block 5)
     */
    private String generateTrailerBlock() {
        StringBuilder sb = new StringBuilder(BLOCK_5_ID);
        sb.append("{CHK:").append(CHK_TRAILER).append("}")
                .append("{MAC:").append(MAC_TRAILER).append("}");
        sb.append("}");
        return sb.toString();
    }

    /**
     * 生成带账号标识的交易参考号
     */
    private String generateTransactionRefNo(int accountIndex) {
        return "TRX" + accountIndex + UUID.randomUUID().toString().replace("-", "").substring(0, 10);
    }

    /**
     * 生成消息输入参考
     */
    private String generateMessageInputReference(Date date) {
        StringBuilder sb = new StringBuilder();
        sb.append(dateFormatYYMMDD.format(date)) // 输入日期
                .append(SWIFT_ADDRESS) // 发送方SWIFT地址
                .append(generateSessionNumber()) // 会话号
                .append(generateSequenceNumber()); // 序列号
        // 确保长度为28字符
        while (sb.length() < 28) {
            sb.append("0");
        }
        return sb.substring(0, 28);
    }

    /**
     * 生成消息用户参考 (:108:)
     */
    private String generateMessageUserReference() {
        // 16位用户参考号随机生成
        return String.format("%016d", System.currentTimeMillis() % 10000000000000000L);
    }

    /**
     * 生成会话号
     */
    private String generateSessionNumber() {
        return String.format("%04d", random.nextInt(9999));
    }

    /**
     * 生成序列号
     */
    private String generateSequenceNumber() {
        return String.format("%06d", random.nextInt(999999));
    }

    /**
     * 生成期初余额 (:60F:)
     */
    private String generateOpeningBalance(Date date, String currency) {
        StringBuilder sb = new StringBuilder(":60F:");
        sb.append(generateDebitCreditMark()) // 借贷标记
                .append(dateFormatYYMMDD.format(date)) // 余额日期
                .append(currency) // 货币
                .append(generateAmount(10000, 1000000, 2)); // 金额 (10000到1000000之间的随机数，2位小数)
        return sb.toString();
    }

    /**
     * 生成交易明细行 (:61:)
     */
    private String generateStatementLine(Date baseDate, int accountIndex, int detailIndex) {
        StringBuilder sb = new StringBuilder(":61:");

        // 计算交易日期（基础日期减去detailIndex+1天）
        Date valueDate = new Date(baseDate.getTime() - (detailIndex + 1) * 24 * 60 * 60 * 1000);

        sb.append(dateFormatYYMMDD.format(valueDate)) // 价值日期
                .append(dateFormatMMDD.format(valueDate)) // 录入日期
                .append(generateDebitCreditMark()) // 借贷标记
                .append("N") // 资金代码 (未使用，固定为N)
                .append(generateAmount(100, 50000, 2)) // 交易金额
                .append(ENTRY_METHOD) // 录入方法
                .append(generateTransactionType()) // 交易类型
                .append(generateAccountOwnerReference()) // 账户所有者参考
                .append("//") // 分隔符
                .append(generateInstitutionReference(accountIndex, detailIndex)) // 机构参考
                .append(generateSupplementaryDetails()); // 补充细节

        return sb.toString();
    }

    /**
     * 生成期末余额 (:62F:)
     */
    private String generateClosingBalance(Date date, String currency) {
        StringBuilder sb = new StringBuilder(":62F:");
        sb.append(generateDebitCreditMark()) // 借贷标记
                .append(dateFormatYYMMDD.format(date)) // 余额日期
                .append(currency) // 货币
                .append(generateAmount(15000, 1500000, 2)); // 金额 (15000到1500000之间的随机数，2位小数)
        return sb.toString();
    }

    /**
     * 生成借贷标记 (C或D)
     */
    private String generateDebitCreditMark() {
        return random.nextBoolean() ? "C" : "D";
    }

    /**
     * 生成金额，格式为带逗号分隔符的数字
     *
     * @param min           最小值
     * @param max           最大值
     * @param decimalPlaces 小数位数
     */
    private String generateAmount(int min, int max, int decimalPlaces) {
        double amount = min + (random.nextDouble() * (max - min));
        // 格式化为带指定小数位的字符串，使用逗号作为小数点
        String format = "%,." + decimalPlaces + "f";
        return String.format(format, amount).replace(".", ",");
    }

    /**
     * 生成交易类型（699-借记，399-贷记）
     */
    private String generateTransactionType() {
        return random.nextBoolean() ? "699" : "399";
    }

    /**
     * 生成账户所有者参考
     */
    private String generateAccountOwnerReference() {
        return random.nextBoolean() ? "NONREF" : generateRandomString(16);
    }

    /**
     * 生成机构参考
     */
    private String generateInstitutionReference(int accountIndex, int detailIndex) {
        if (random.nextBoolean()) {
            return "CHK" + accountIndex + detailIndex + String.format("%06d", random.nextInt(999999));
        } else {
            return "REF" + accountIndex + detailIndex + generateRandomString(8);
        }
    }

    /**
     * 生成补充细节
     */
    private String generateSupplementaryDetails() {
        return "DETAIL-" + generateRandomString(20);
    }

    /**
     * 生成指定长度的随机字符串
     */
    private String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    /**
     * 字符串填充
     */
    private String padToLength(int originalLength, int padLength, char padChar) {
        if (padLength <= 0) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < padLength; i++) {
            sb.append(padChar);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s="{\"mbs\":{\"pub\":{\"outsystemid\":\"3d99c0aa5e7d4eff82dc4c3add3d48ac\",\"srcbatchno\":\"0000000580911\",\"timezone\":\"GMT+8\",\"transcode\":\"BAPB01\",\"transdatetime\":\"2024-06-24 23:11:00\",\"version\":\"b.xlsx\"},\"req\":{\"list\":{\"detail\":[{\"lastmodifiedon\":\"2024-06-24 23:05:33\",\"oppositebank\":\"中国银行深圳市分行交易银行部\",\"claimstate\":\"1\",\"purpose\":\"贴现实付金额\",\"curcode\":\"CNY\",\"lastmodifiedby\":\"admin\",\"source\":\"6\",\"urid\":\"b9ec519c876f46ab90240229c1918cbd\",\"currentbalance\":73011443.49,\"accountid\":\"6f9431081e694b26a069d4558b9152ab\",\"accountname\":\"深圳市创鑫激光股份有限公司\",\"billtype\":\"电子商业汇票\",\"createdby\":\"admin\",\"receiptcode\":\"186489014789\",\"accountbanklocations\":\"中国银行股份有限公司深圳高新区支行\",\"oppositeaccountname\":\"中国银行深圳市分行交易银行部\",\"bankserialnumber\":\"186489014789\",\"draftlist\":[],\"moneyway\":\"2\",\"amount\":606990.89,\"accountnumber\":\"741962140413\",\"comments\":\"000000000001,000060950000//贴现实付金额\",\"tradedatetime\":\"2024-06-24 19:22:04\",\"bankbusref\":\"186489014789\",\"orgcode\":\"101\",\"createdon\":\"2024-06-24 23:05:33\",\"transseq\":\"6f9431081e694b26a069d4558b9152ab@2024-06-24@2024-06-24 19:22:04@606,990.89@73,011,443.49@2@1\",\"tradedate\":\"2024-06-24\",\"srcserialno\":\"b9ec519c876f46ab90240229c1918cbd\",\"billcode\":\"530845802802120240416000863674\",\"valuedate\":\"2024-06-24\",\"isreconciliation\":\"0\",\"matchtranstate\":\"1\",\"oppositeaccountnumber\":\"178397121001\",\"rowversion\":1},{\"lastmodifiedon\":\"2024-06-24 23:05:33\",\"oppositebank\":\"中国银行深圳市分行交易银行部\",\"claimstate\":\"1\",\"purpose\":\"贴现实付金额\",\"curcode\":\"CNY222\",\"lastmodifiedby\":\"admin\",\"source\":\"6\",\"urid\":\"b9ec519c876f46ab90240229c1918cbd\",\"currentbalance\":73011443.49,\"accountid\":\"6f9431081e694b26a069d4558b9152ab\",\"accountname\":\"深圳市创鑫激光股份有限公司\",\"billtype\":\"电子商业汇票\",\"createdby\":\"admin\",\"receiptcode\":\"186489014789\",\"accountbanklocations\":\"中国银行股份有限公司深圳高新区支行\",\"oppositeaccountname\":\"中国银行深圳市分行交易银行部\",\"bankserialnumber\":\"186489014789\",\"draftlist\":[],\"moneyway\":\"2\",\"amount\":606990.89,\"accountnumber\":\"741962140413\",\"comments\":\"000000000001,000060950000//贴现实付金额\",\"tradedatetime\":\"2024-06-24 19:22:04\",\"bankbusref\":\"186489014789\",\"orgcode\":\"101\",\"createdon\":\"2024-06-24 23:05:33\",\"transseq\":\"6f9431081e694b26a069d4558b9152ab@2024-06-24@2024-06-24 19:22:04@606,990.89@73,011,443.49@2@1\",\"tradedate\":\"2024-06-24\",\"srcserialno\":\"b9ec519c876f46ab90240229c1918cbd\",\"billcode\":\"530845802802120240416000863674\",\"valuedate\":\"2024-06-24\",\"isreconciliation\":\"0\",\"matchtranstate\":\"1\",\"oppositeaccountnumber\":\"178397121001\",\"rowversion\":1},{\"lastmodifiedon\":\"2024-06-24 23:05:33\",\"oppositebank\":\"中国银行深圳市分行交易银行部\",\"claimstate\":\"1\",\"purpose\":\"贴现实付金额\",\"curcode\":\"CNY333\",\"lastmodifiedby\":\"admin\",\"source\":\"6\",\"urid\":\"b9ec519c876f46ab90240229c1918cbd\",\"currentbalance\":73011443.49,\"accountid\":\"6f9431081e694b26a069d4558b9152ab\",\"accountname\":\"深圳市创鑫激光股份有限公司\",\"billtype\":\"电子商业汇票\",\"createdby\":\"admin\",\"receiptcode\":\"186489014789\",\"accountbanklocations\":\"中国银行股份有限公司深圳高新区支行\",\"oppositeaccountname\":\"中国银行深圳市分行交易银行部\",\"bankserialnumber\":\"186489014789\",\"draftlist\":[],\"moneyway\":\"2\",\"amount\":606990.89,\"accountnumber\":\"741962140413\",\"comments\":\"000000000001,000060950000//贴现实付金额\",\"tradedatetime\":\"2024-06-24 19:22:04\",\"bankbusref\":\"186489014789\",\"orgcode\":\"101\",\"createdon\":\"2024-06-24 23:05:33\",\"transseq\":\"6f9431081e694b26a069d4558b9152ab@2024-06-24@2024-06-24 19:22:04@606,990.89@73,011,443.49@2@1\",\"tradedate\":\"2024-06-24\",\"srcserialno\":\"b9ec519c876f46ab90240229c1918cbd\",\"billcode\":\"530845802802120240416000863674\",\"valuedate\":\"2024-06-24\",\"isreconciliation\":\"0\",\"matchtranstate\":\"1\",\"oppositeaccountnumber\":\"178397121001\",\"rowversion\":1}]}}}}\n";
        JSONObject jsonObject = JSONUtil.parseObj(s);
        JSONArray jsonArray = jsonObject.getByPath("mbs.req.list.detail", JSONArray.class);
        MyMT940 generator = new MyMT940();
        // todo 下周需要将数组对象转化成报文，从块1开始
        String mt940Message = generator.generateMT940Message(jsonArray);
        System.out.println("生成的MT940报文：");
        System.out.println(mt940Message);
    }

}