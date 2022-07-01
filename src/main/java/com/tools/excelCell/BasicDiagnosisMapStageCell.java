package com.tools.excelCell;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 基础诊断与损伤部位与三期天数映射关系
 */
@Data
public class BasicDiagnosisMapStageCell extends ExcelCell{
    /**
     * 基础诊断
     */
    @ExcelProperty(index = 0)
    private String basicDiagnosis;

    /**
     * 基础诊断编码
     */
    @ExcelProperty(index = 1)
    private String basicDiagnosisCode;

    /**
     * 损伤部位名称
     */
    @ExcelProperty(index = 2)
    private String damagedPart;

    /**
     * 损伤部位编码
     */
    @ExcelProperty(index = 3)
    private String damagedPartCode;

    /**
     * 误工期下限值
     */
    @ExcelProperty(index = 4)
    private Integer delayedWorkUpper;

    /**
     * 误工期上限值
     */
    @ExcelProperty(index = 5)
    private Integer delayedWorkLower;

    /**
     * 误工期推荐值
     */
    @ExcelProperty(index = 6)
    private Integer delayedWorkOptimal;

    /**
     * 护理期下限值
     */
    @ExcelProperty(index = 7)
    private Integer nursingWorkUpper;

    /**
     * 护理期上限值
     */
    @ExcelProperty(index = 8)
    private Integer nursingWorkLower;

    /**
     * 护理期推荐值
     */
    @ExcelProperty(index = 9)
    private Integer nursingWorkOptimal;

    /**
     * 营养期下限值
     */
    @ExcelProperty(index = 10)
    private Integer vegetativeWorkUpper;

    /**
     * 营养期上限值
     */
    @ExcelProperty(index = 11)
    private Integer vegetativeWorkLower;

    /**
     * 营养期推荐值
     */
    @ExcelProperty(index = 12)
    private Integer vegetativeWorkOptimal;
}
