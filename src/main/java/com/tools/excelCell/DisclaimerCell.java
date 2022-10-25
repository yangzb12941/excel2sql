package com.tools.excelCell;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 保险责任 insurance_liability
 */
@Data
public class DisclaimerCell extends ExcelCell{
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
     * 免责类型
     */
    @ExcelProperty(index = 2)
    private String disclaimerType;

    /**
     * 免责事项
     */
    @ExcelProperty(index = 3)
    private String disclaimerDescription;

    /**
     * 免责标签
     */
    @ExcelProperty(index = 4)
    private String disclaimerLabel;
}
