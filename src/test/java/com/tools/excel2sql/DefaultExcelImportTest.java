package com.tools.excel2sql;

import com.alibaba.excel.EasyExcel;
import com.tools.entity.InsuranceLabelLibraryModel;
import com.tools.excelCell.InsuranceLabelLibraryCell;
import com.tools.sqlWriter.SqlWriter2Txt;
import com.tools.utils.DefaultExcelImporTools;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.File;

@SpringBootTest
public class DefaultExcelImportTest {
    @Test
    public void test() {
        String sqlTemplate = "INSERT INTO insurance_label_library (insurance_liability_risk_type, insurance_liability_label, trigger_condition, risk_content)";
        File file = new File("E:\\中华财险工作文件夹\\人伤平台\\1011意键险人伤平台\\新建 XLSX 工作表.xlsx");
        EasyExcel.read(file, InsuranceLabelLibraryCell.class,
                new DefaultExcelImporTools(SqlWriter2Txt.getInstance(),sqlTemplate, InsuranceLabelLibraryModel.class))
                .sheet(0).headRowNumber(5).doRead();
    }
}
