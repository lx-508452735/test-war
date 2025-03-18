package com.lx.test;

import cn.hutool.core.util.IdUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

public class FileUtil {
    public static void main(String[] args) {
        ExcelReader excelreader = ExcelUtil.getReader("C:\\Users\\浙江保融\\Desktop\\China coding.xlsx");

        List<List<Object>> rows= excelreader.read(1);
        for (List<Object> row:rows){
//INSERT INTO T_EI_DATAMAPDETAILS
// (URID, DATAMAPID, SOURCEVALUE, TARGETVALUE, CREATEDBY, CREATEDON, LASTMODIFIEDBY, LASTMODIFIEDON, DESCRIPTION, ROWVERSION,TENANTID)
//SELECT '8dsacxzcadcdsacxzsc89ca2926fe450','851f7956sadcdsacxzc89ca2926fe450','T_BA_ELERECEIPTS','BANKACCOUNT@@service/eleReceipts/updateExportState','SysAdmin',to_date('2024-08-08','yyyy-MM-dd'),'SysAdmin',to_date('2024-08-08','yyyy-MM-dd'),'',1,10001
//FROM DUAL WHERE NOT EXISTS(SELECT * FROM T_EI_DATAMAPDETAILS T WHERE T.URID = '8dsacxzcadcdsacxzsc89ca2926fe450');
            StringBuilder ss= new StringBuilder("INSERT INTO T_EI_DATAMAPDETAILS (URID, DATAMAPID, SOURCEVALUE, TARGETVALUE, CREATEDBY, CREATEDON, LASTMODIFIEDBY, LASTMODIFIEDON, DESCRIPTION, ROWVERSION,TENANTID) VALUES (");
            ss.append("'").append(IdUtil.simpleUUID()).append("',");
            ss.append("'").append("ITALYACCOUNT").append("',");
//            StringBuilder ss= new StringBuilder("UPDATE T_SY_AREAS SET ENNAME = ");
            String col2 = row.get(2).toString().replaceAll(" +","");
            String col0 = row.get(0).toString().replaceAll(" +","");
            ss.append("'").append(col2).append("',");
            ss.append("'").append(col0).append("',");
            ss.append("'SysAdmin',to_date('2024-08-08','yyyy-MM-dd'),'SysAdmin',to_date('2024-08-08','yyyy-MM-dd'),'',1,10001);");
//            String col2 = row.get(1).toString();
//            ss.append("'").append(col2).append("',");
//            if (row.size()<3 || row.get(2).toString().length()==1){
//                continue;
//            }
//            String col3 = row.get(2).toString();
//            ss.append("'").append(col3).append("' WHERE CODE = '").append(col1).append("';");

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
