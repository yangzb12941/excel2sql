package com.tools.excelCell;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 保险责任标签库 insurance_label_library
 */
@Data
public class InsuranceLabelLibraryCell extends ExcelCell{
    /**
     * 保险责任风险类别
     */
    @ExcelProperty(index = 0)
    private String insuranceLiabilityRiskType;

    /**
     * 保险责任标签
     */
    @ExcelProperty(index = 1)
    private String insuranceLiabilityLabel;

    /**
     * 触发条件
     */
    @ExcelProperty(index = 2)
    private String triggerCondition;

    /**
     * 风险提示内容
     */
    @ExcelProperty(index = 3)
    private String riskContent;
}
