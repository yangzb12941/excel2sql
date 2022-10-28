package com.tools.excel2sql;

import com.alibaba.excel.EasyExcel;
import com.tools.entity.*;
import com.tools.excelCell.*;
import com.tools.sqlWriter.SqlWriter2Txt;
import com.tools.utils.DefaultExcelImporTools;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
public class DefaultExcelImportTest {
    @Test
    public void insurance_label_library() {
        String sqlTemplate = "INSERT INTO insurance_label_library (insurance_liability_risk_type, insurance_liability_label, trigger_condition, risk_content)";
        File file = new File("E:\\中华财险工作文件夹\\人伤平台\\1011意键险人伤平台\\新建 XLSX 工作表.xlsx");
        EasyExcel.read(file, InsuranceLabelLibraryCell.class,
                new DefaultExcelImporTools(SqlWriter2Txt.getInstance(),sqlTemplate, InsuranceLabelLibraryModel.class))
                .sheet(0).headRowNumber(5).doRead();
    }

    @Test
    public void insurance_clauses() {
        String sqlTemplate = "\n" +
                "INSERT INTO insurance_clauses (clause_code, clause_name, one_level_clause, two_level_clause, authority_name, fee_type_cd, fee_type_name, compensation_conditions, pay_formula_conditions, paya_formula, adjustment_formula_conditions, adjustment_formula)";
        File file = new File("E:\\中华财险工作文件夹\\人伤平台\\1011意键险人伤平台\\数据库脚本\\insurance_clauses.xlsx");
        EasyExcel.read(file, InsuranceClausesCell.class,
                new DefaultExcelImporTools(SqlWriter2Txt.getInstance(),sqlTemplate, InsuranceClausesModel.class))
                .sheet(0).headRowNumber(1).doRead();
    }

    @Test
    public void insurance_liability() {
        String sqlTemplate = "INSERT INTO insurance_liability (clause_code, clause_name, insurance_liability, insurance_liability_label)";
        File file = new File("E:\\中华财险工作文件夹\\人伤平台\\1011意键险人伤平台\\数据库脚本\\insurance_liability.xlsx");
        EasyExcel.read(file, InsuranceLiabilityCell.class,
                new DefaultExcelImporTools(SqlWriter2Txt.getInstance(),sqlTemplate, InsuranceLiabilityModel.class))
                .sheet(0).headRowNumber(1).doRead();
    }

    @Test
    public void insurance_basics() {
        String sqlTemplate = "INSERT INTO insurance_basics (clause_code, clause_name, one_level_clause, authority_name, url)";
        File file = new File("E:\\中华财险工作文件夹\\人伤平台\\1011意键险人伤平台\\数据库脚本\\insurance_basics.xlsx");
        EasyExcel.read(file, InsuranceBasicsCell.class,
                new DefaultExcelImporTools(SqlWriter2Txt.getInstance(),sqlTemplate, InsuranceBasicsModel.class))
                .sheet(0).headRowNumber(1).doRead();
    }

    @Test
    public void disclaimer() {
        String sqlTemplate = "INSERT INTO disclaimer (clause_code, clause_name, disclaimer_type, disclaimer_description, disclaimer_label)";
        File file = new File("E:\\中华财险工作文件夹\\人伤平台\\1011意键险人伤平台\\数据库脚本\\disclaimer.xlsx");
        EasyExcel.read(file, DisclaimerCell.class,
                new DefaultExcelImporTools(SqlWriter2Txt.getInstance(),sqlTemplate, DisclaimerModel.class))
                .sheet(0).headRowNumber(1).doRead();
    }
}
