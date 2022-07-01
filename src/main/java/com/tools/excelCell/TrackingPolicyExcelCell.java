package com.tools.excelCell;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 跟踪策略
 */
@Data
public class TrackingPolicyExcelCell extends ExcelCell{
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
     * 伤情描述
     */
    @ExcelProperty(index = 2)
    private String injuryDescription;

    /**
     * 治疗方案
     */
    @ExcelProperty(index = 3)
    private String diagnosisPlan;

    /**
     * 是否涉残
     */
    @ExcelProperty(index = 4)
    private String isDisability;

    /**
     * 首次跟踪时间
     */
    @ExcelProperty(index = 5)
    private Integer firstTracking;

    /**
     * 交付物
     */
    @ExcelProperty(index = 6)
    private String firstDeliverables;

    /**
     * 第二次跟踪时间
     */
    @ExcelProperty(index = 7)
    private Integer secondTracking;

    /**
     * 交付物
     */
    @ExcelProperty(index = 8)
    private String secondDeliverables;

    /**
     * 第三次跟踪时间
     */
    @ExcelProperty(index = 9)
    private Integer thirdTracking;

    /**
     * 交付物
     */
    @ExcelProperty(index = 10)
    private String thirdDeliverables;

    /**
     * 第四次跟踪时间
     */
    @ExcelProperty(index = 11)
    private Integer fourthTracking;

    /**
     * 交付物
     */
    @ExcelProperty(index = 12)
    private String fourthDeliverables;

    /**
     * 第五次跟踪时间
     */
    @ExcelProperty(index = 13)
    private Integer fifthTracking;

    /**
     * 交付物
     */
    @ExcelProperty(index = 14)
    private String fifthDeliverables;

    /**
     * 第六次跟踪时间
     */
    @ExcelProperty(index = 15)
    private Integer sixthTracking;

    /**
     * 交付物
     */
    @ExcelProperty(index = 16)
    private String sixthDeliverables;

    /**
     * 巡航跟踪周期
     */
    @ExcelProperty(index = 17)
    private Integer cruiseTracking;

    /**
     * 交付物
     */
    @ExcelProperty(index = 18)
    private String cruiseDeliverables;
}
