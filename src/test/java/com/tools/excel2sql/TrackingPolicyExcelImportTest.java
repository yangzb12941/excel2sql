package com.tools.excel2sql;

import com.alibaba.excel.EasyExcel;
import com.tools.excelCell.TrackingPolicyExcelCell;
import com.tools.sqlWriter.SqlWriter2Txt;
import com.tools.utils.TrackingPolicyExcelImportTools;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
public class TrackingPolicyExcelImportTest {
    @Test
    public void test() {
        File file = new File("E:\\中华财险工作文件夹\\人伤平台\\830车险人伤平台\\基础数据\\人伤跟踪处理策略梳理2022-6-14交付（待定）.xlsx");
        EasyExcel.read(file, TrackingPolicyExcelCell.class,
                new TrackingPolicyExcelImportTools(SqlWriter2Txt.getInstance()))
                .sheet("跟踪策略").headRowNumber(2).doRead();
    }
}
