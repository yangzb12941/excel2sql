package com.tools.excelCell;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 保险责任 insurance_liability
 */
@Data
public class InsuranceLiabilityCell extends ExcelCell{
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
     * 保险责任标
     */
    @ExcelProperty(index = 2)
    private String insuranceLiability;

    /**
     * 保险责任标签
     */
    @ExcelProperty(index = 3)
    private String insuranceLiabilityLabel;
}
