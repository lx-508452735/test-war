package com.lx.test;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

import java.util.List;

public class FileUtil {
    public static void main(String[] args) {
        ExcelReader excelreader = ExcelUtil.getReader("C:\\Users\\浙江保融\\Desktop\\区域.xlsx");

        List<List<Object>> rows= excelreader.read(1);
        for (List<Object> row:rows){
//            StringBuilder ss= new StringBuilder("INSERT INTO TSYS_USER (USER_ID,USER_NAME,USER_PWD,ORG_ID,USER_TYPE,USER_STATUS,LOCK_STATUS,CREATE_DATE,TENANTID) VALUES (");
            StringBuilder ss= new StringBuilder("UPDATE T_SY_AREAS SET ENNAME = ");
            String col1 = row.get(0).toString();
//            ss.append("'").append(col1).append("',");
//            String col2 = row.get(1).toString();
//            ss.append("'").append(col2).append("',");
            if (row.size()<3 || row.get(2).toString().length()==1){
                continue;
            }
            String col3 = row.get(2).toString();
            ss.append("'").append(col3).append("' WHERE CODE = '").append(col1).append("';");

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
}
