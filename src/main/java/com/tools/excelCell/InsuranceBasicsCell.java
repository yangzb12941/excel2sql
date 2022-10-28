package com.tools.excelCell;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class InsuranceBasicsCell extends ExcelCell{
    /**
     * 条款代码
     */
    @ExcelProperty(index = 0)
    private String clauseCode;

    /**
     * 条款名称
     */
    @ExcelProperty(index = 1)
    private String clauseName;

    /**
     * 一级责任代码
     */
    @ExcelProperty(index = 2)
    private String oneLevelClause;

    /**
     * 条款代码
     */
    @ExcelProperty(index = 3)
    private String authorityName;

    /**
     * 条款文件oss文件存储url
     */
    @ExcelProperty(index = 4)
    private String url;
}
