package com.tools.excelCell;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 全国各地人伤损害赔偿标准
 */
@Data
public class InjuryCompensationStandardCell extends ExcelCell{
    /**
     * 分公司
     */
    @ExcelProperty(index = 0)
    private String branchOffice;

    /**
     * 中支
     */
    @ExcelProperty(index = 1)
    private String middleBranch;

    /**
     * 在岗职工平均工资(年)
     */
    @ExcelProperty(index = 2)
    private Double averageWage;

    /**
     * 城镇居民人均可支配收入（年）
     */
    @ExcelProperty(index = 3)
    private Double perCapitaUrban;

    /**
     * 农村居民人均纯收入（年）
     */
    @ExcelProperty(index = 4)
    private Double perCapitaRural;

    /**
     * 城镇居民人均消费性支出（年）
     */
    @ExcelProperty(index = 5)
    private Double perConsumptionUrban;

    /**
     * 农村居民人均年生活消费支出（年）
     */
    @ExcelProperty(index = 6)
    private Double perConsumptionRural;

    /**
     * 丧葬费
     */
    @ExcelProperty(index = 7)
    private Double funeralExpenses;

    /**
     * 全体居民人均可支配收入（年）
     */
    @ExcelProperty(index = 8)
    private Double perCapitaIncome;

    /**
     * 全体居民人均消费性支出（年）
     */
    @ExcelProperty(index = 9)
    private Double perCapitaConsumption;

    /**
     * 伙食补助费（元/天）
     */
    @ExcelProperty(index = 10)
    private Double foodAllowance;

    /**
     * 营养费（元/天）
     */
    @ExcelProperty(index = 11)
    private Double nutritionFee;

    /**
     * 亲属住宿费（元/天）
     */
    @ExcelProperty(index = 12)
    private Double relativesAccommodationFee;

    /**
     * 误工费（元/天）
     */
    @ExcelProperty(index = 13)
    private Double delayFee;

    /**
     * 院内护理费（元/天）
     */
    @ExcelProperty(index = 14)
    private Double hospitalNursingFee;

    /**
     * 院外护理费（元/天）
     */
    @ExcelProperty(index = 15)
    private Double outOfHospitalNursingFee;

    /**
     * 交通费（元/天）
     */
    @ExcelProperty(index = 16)
    private Double transportationFee;

    /**
     * 精神抚慰金（级）
     */
    @ExcelProperty(index = 17)
    private Double spiritualSolace;

    /**
     * 医疗费（非医保扣减比例%）
     */
    @ExcelProperty(index = 18)
    private Double deductionNonMedical;
}
