package com.tools.excelCell;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 条款-责任-费用类型匹配 insurance_clauses
 */
@Data
public class InsuranceClausesCell extends ExcelCell{
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
     * 二级责任代码
     */
    @ExcelProperty(index = 3)
    private String twoLevelClause;

    /**
     * 责任名称
     */
    @ExcelProperty(index = 4)
    private String authorityName;

    /**
     * 可赔付项目及费用类型
     */
    @ExcelProperty(index = 5)
    private String feeTypeCd;

    /**
     * 可赔付项目及费用名称
     */
    @ExcelProperty(index = 6)
    private String feeTypeName;

    /**
     * 赔付条件
     */
    @ExcelProperty(index = 7)
    private String compensationConditions;
}
