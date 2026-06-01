package test;

import cn.hutool.core.util.IdUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import org.junit.Test;

import java.util.List;

public class FileUtil {
    public static void main(String[] args) {
        ExcelReader excelreader = ExcelUtil.getReader("C:\\Users\\浙江保融\\Desktop\\接口指令.xlsx");

        List<List<Object>> rows = excelreader.read(1);

//        System.out.println("-- delete TSYS_SUBTRANS_FEATURE_PACK");
//        for (List<Object> row : rows) {
//            final String s = row.get(12).toString();
//            if (!"10001.0".equals(s)){
//                continue;
//            }
//            StringBuilder ss = new StringBuilder("delete from TSYS_SUBTRANS_FEATURE_PACK where (TRANS_CODE = 'ms_interfacePermission' and SUB_TRANS_CODE = '");
//            String col1 = row.get(2).toString();
//            ss.append(col1).append("')").append(";");
//            System.out.println(ss);
//        }
//
//        System.out.println("-- delete TSYS_SUBTRANS_MARK");
//        for (List<Object> row : rows) {
//            final String s = row.get(12).toString();
//            if (!"10001.0".equals(s)){
//                continue;
//            }
//            StringBuilder ss = new StringBuilder("delete from TSYS_SUBTRANS_MARK where (trans_code = 'ms_interfacePermission' and sub_trans_code = '");
//            String col1 = row.get(2).toString();
//            ss.append(col1).append("')").append(";");
//            System.out.println(ss);
//        }
//
//        System.out.println("-- delete TSYS_SUBTRANS");
//        for (List<Object> row : rows) {
//            final String s = row.get(12).toString();
//            if (!"10001.0".equals(s)){
//                continue;
//            }
//            StringBuilder ss = new StringBuilder("delete from TSYS_SUBTRANS where (trans_code = 'ms_interfacePermission' and sub_trans_code = '");
//            String col1 = row.get(2).toString();
//            ss.append(col1).append("')").append(";");
//            System.out.println(ss);
//        }
//
//        System.out.println("-- insert TSYS_SUBTRANS");
//        for (List<Object> row : rows) {
//            final String s = row.get(12).toString();
//            if (!"10001.0".equals(s)){
//                continue;
//            }
//            StringBuilder ss = new StringBuilder("insert into TSYS_SUBTRANS (trans_code, sub_trans_code, sub_trans_name, rel_serv, rel_url, ctrl_flag, login_flag, ext_field_1, ext_field_2, ext_field_3, IS_STANDARD, remark) values ('ms_interfacePermission', '");
//            String col1 = row.get(2).toString();
//            String col2 = row.get(3).toString();
//            ss.append(col1).append("', '").append(col2).append("', '").append(col1).append("', null, null, '1', null, null, null, 0, null)").append(";");
//            System.out.println(ss);
//        }

        System.out.println("-- insert TSYS_SUBTRANS_MARK");
        for (List<Object> row : rows) {
//            final String s1 = row.get(12).toString();
//            if (!"10001.0".equals(s1)){
//                continue;
//            }
            String s = row.get(11).toString();
            String s2 = "";
            if (!"default".equals(s)) {
                s = "'" + s + "'";
                s2 = "P";
            } else {
                s = "null";
                s2 = "S";
            }
            StringBuilder ss = new StringBuilder("insert into TSYS_SUBTRANS_MARK (trans_code, sub_trans_code, product_model, design_model, product_category, customer_code, industry_code, import_version, remark, description, CREATEDBY, CREATEDON, LASTMODIFIEDBY, LASTMODIFIEDON, ROWVERSION, SYS_CODE) values ('ms_interfacePermission', '");
            String col1 = row.get(1).toString();
            ss.append(col1).append("', 'ATS3.0', '3.0-NATIVE', '").append(s2).append("', null, null, 'V3.2.202601.0', null, null, 'SupAdmin', TO_DATE('2026-01-09 14:25:11', 'YYYY-MM-DD HH24:MI:SS'), 'SupAdmin', TO_DATE('2026-01-09 14:25:11', 'YYYY-MM-DD HH24:MI:SS'), 1, ").append(s).append(");");
            System.out.println(ss);
        }

//        System.out.println("-- insert TSYS_SUBTRANS_FEATURE_PACK");
//        for (List<Object> row : rows) {
//            final String s = row.get(12).toString();
//            if (!"10001.0".equals(s)){
//                continue;
//            }
//            StringBuilder ss = new StringBuilder("insert into TSYS_SUBTRANS_FEATURE_PACK (TRANS_CODE, SUB_TRANS_CODE, CATEGORIZEID, REMARK, CREATEDON, CREATEDBY, LASTMODIFIEDON, LASTMODIFIEDBY, ROWVERSION) values ('ms_interfacePermission', '");
//            String col1 = row.get(2).toString();
//            ss.append(col1).append("', 'bsp', null, TO_DATE('2026-01-09 14:25:11', 'YYYY-MM-DD HH24:MI:SS'), 'SysAdmin', TO_DATE('2026-01-09 14:25:11', 'YYYY-MM-DD HH24:MI:SS'), 'SysAdmin', 1)").append(";");
//            System.out.println(ss);
//        }
    }


    //插入资金类别
    @Test
    public void test38() {
        ExcelReader excelreader = ExcelUtil.getReader("C:\\Users\\浙江保融\\Desktop\\资金类别导入数据.xlsx");

        List<List<Object>> rows = excelreader.read(1);
        for (List<Object> row : rows) {
            StringBuilder ss= new StringBuilder("INSERT INTO T_BD_CATEGORIES " +
                    "(URID, CODE, NAME, CLASSIFICATION, ISACTIVE, CREATEDBY, CREATEDON, LASTMODIFIEDBY, LASTMODIFIEDON," +
                    " ROWVERSION, DESCRIPTION, ISSYSTEMINIT, TENANTID, PARENTID, EXTENDCATEGORY, SORTNO, CASHFLOWITEMID, " +
                    "ISFUNDAVAILABLE,CLAIMELEMENT) VALUES (");
            String uuid = IdUtil.simpleUUID();
            ss.append("'").append(uuid).append("',");

            String col1 = row.get(0).toString();
            ss.append("'").append(col1).append("',");
            String col2 = row.get(1).toString();
            ss.append("'").append(col2).append("',");
            ss.append("'").append(1).append("',");
            ss.append("'").append(1).append("',");
            ss.append("'").append("server").append("',");
            ss.append("SYSDATE,");
            ss.append("'").append("server").append("',");
            ss.append("SYSDATE,");
            ss.append("'").append("1").append("',");
            ss.append("null,");
            ss.append("'").append("0").append("',");
            ss.append("'").append("10001").append("',");
            ss.append("null,");
            ss.append("null,");
            ss.append("null,");
            ss.append("null,");
            ss.append("'").append("1").append("',");
            ss.append("null);");



//            String col3 = row.get(2).toString();
//            ss.append("'").append(col3).append("',");
//            String col4 = row.get(3).toString();
//            ss.append("'").append(col4).append("',");
//            String col5 = row.get(4).toString();
//            ss.append("'").append(col5).append("',");
//            String col6 = row.get(5).toString();
//            ss.append("'").append(col6).append("',");
//            String col7 = row.get(6).toString();
//            ss.append("'").append(col7).append("',");
//            String col8 = row.get(7).toString();
//            ss.append("'").append(col8).append("',");
//            ss.append("'10001');");
            System.out.println(ss);
        }
    }

    //插入识别标记
    @Test
    public void test95() {
        ExcelReader excelreader = ExcelUtil.getReader("C:\\Users\\浙江保融\\Desktop\\识别标记导入数据.xlsx");

        List<List<Object>> rows = excelreader.read(1);
        for (List<Object> row : rows) {
            StringBuilder ss= new StringBuilder("INSERT INTO T_BA_RECOGMARKS " +
                    "(URID, CODE, NAME, ISSYSTEMINIT, ISACTIVE, CREATEDON, CREATEDBY, LASTMODIFIEDON, LASTMODIFIEDBY, " +
                    "TENANTID, ROWVERSION, MEMO) VALUES (");
            String uuid = IdUtil.simpleUUID();
            ss.append("'").append(uuid).append("',");

            String col1 = row.get(0).toString();
            ss.append("'").append(col1).append("',");
            String col2 = row.get(1).toString();
            ss.append("'").append(col2).append("',");
            ss.append("'").append("1").append("',");
            ss.append("'").append("1").append("',");
            ss.append("SYSDATE,");
            ss.append("'").append("server").append("',");
            ss.append("SYSDATE,");
            ss.append("'").append("server").append("',");
            ss.append("'").append("10001").append("',");
            ss.append("'").append("1").append("',");
            ss.append("null);");

            StringBuilder ss2= new StringBuilder("INSERT INTO T_BA_DISTRMARKPATH " +
                    "(URID, MARKID, PATHID, CREATEDON, CREATEDBY, LASTMODIFIEDON, LASTMODIFIEDBY, " +
                    "TENANTID, ROWVERSION) VALUES (");
            String uuid2 = IdUtil.simpleUUID();
            ss2.append("'").append(uuid2).append("',");
            ss2.append("'").append(uuid).append("',");
            ss2.append("'").append("c516314c3c2541bc98960509867aab6f").append("',");
            ss2.append("SYSDATE,");
            ss2.append("'").append("server").append("',");
            ss2.append("SYSDATE,");
            ss2.append("'").append("server").append("',");
            ss2.append("'").append("10001").append("',");
            ss2.append("'").append("1").append("');");
//            String col3 = row.get(2).toString();
//            ss.append("'").append(col3).append("',");
//            String col4 = row.get(3).toString();
//            ss.append("'").append(col4).append("',");
//            String col5 = row.get(4).toString();
//            ss.append("'").append(col5).append("',");
//            String col6 = row.get(5).toString();
//            ss.append("'").append(col6).append("',");
//            String col7 = row.get(6).toString();
//            ss.append("'").append(col7).append("',");
//            String col8 = row.get(7).toString();
//            ss.append("'").append(col8).append("',");
//            ss.append("'10001');");
            System.out.println(ss+"\n"+ss2);
        }
    }

    //插入交易类型
    @Test
    public void test152() {
        ExcelReader excelreader = ExcelUtil.getReader("C:\\Users\\浙江保融\\Desktop\\交易类型导入数据.xlsx");

        List<List<Object>> rows = excelreader.read(1);
        for (List<Object> row : rows) {
            StringBuilder ss= new StringBuilder("INSERT INTO T_SE_PAYTYPES " +
                    "(URID, CODE, NAME, MONEYWAY, PAYTYPECATEGORY,SETTLEMENTMODERANGE,DEFAULTSETTLEMENTMODEID," +
                    "BUDGETITEMREQUIREDTYPE,OPPOBJECTTYPE," +
                    " CREATEDBY, LASTMODIFIEDBY) VALUES (");
            String uuid = IdUtil.simpleUUID();
            ss.append("'").append(uuid).append("',");

            String col1 = row.get(0).toString();
            ss.append("'").append(col1).append("',");
            String col2 = row.get(1).toString();
            ss.append("'").append(col2).append("',");
            ss.append("'").append("2").append("',");
            ss.append("'").append("0").append("',");
            ss.append("'").append(",602,203,").append("',");
            ss.append("'").append("602").append("',");
            ss.append("'").append("0").append("',");
            ss.append("'").append("2").append("',");
            ss.append("'").append("server").append("',");
            ss.append("'").append("server").append("');");
            System.out.println(ss);
        }
    }

    //插入角色权限
    @Test
    public void test181(){
        ExcelReader excelreader = ExcelUtil.getReader("C:\\Users\\浙江保融\\Desktop\\2.xlsx");

        List<List<Object>> rows = excelreader.read(1);
        for (List<Object> row : rows) {
            StringBuilder ss= new StringBuilder("INSERT INTO tsys_role_right (TRANS_CODE, SUB_TRANS_CODE, ROLE_CODE, CREATE_BY, CREATE_DATE, BEGIN_DATE, END_DATE, RIGHT_FLAG, RIGHT_ENABLE,TENANTID)\n" +
                    "VALUES (");
            ss.append("'ms_loanlendsregister', 'UC_FINANCING_FL_LOANLENDS_ADDAUTH',");

            String col1 = row.get(2).toString();
            ss.append("'").append(col1).append("',");

            ss.append(" 'AuthAdmin', 0, 0, 0, '1', null,10001);");
            System.out.println(ss);
        }
    }
    //插入角色权限
    @Test
    public void test182(){
        ExcelReader excelreader = ExcelUtil.getReader("C:\\Users\\浙江保融\\Desktop\\1.xlsx");

        List<List<Object>> rows = excelreader.read(1);
        for (List<Object> row : rows) {
            StringBuilder ss= new StringBuilder("INSERT INTO tsys_role_right (TRANS_CODE, SUB_TRANS_CODE, ROLE_CODE, CREATE_BY, CREATE_DATE, BEGIN_DATE, END_DATE, RIGHT_FLAG, RIGHT_ENABLE,TENANTID)\n" +
                    "VALUES (");
            ss.append("'ms_loanlends', 'UC_FINANCING_IF_LOANSLENDS_ADDAUTH',");

            String col1 = row.get(2).toString();
            ss.append("'").append(col1).append("',");

            ss.append(" 'AuthAdmin', 0, 0, 0, '1', null,10001);");
            System.out.println(ss);
        }
    }
}
