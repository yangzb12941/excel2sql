package com.tools.excelCell;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 护理依赖程度赔偿比例
 */
@Data
public class NursingDependenceCell  extends ExcelCell{
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
     * 完全护理依赖
     */
    @ExcelProperty(index = 2)
    private Double absoluteDependence;

    /**
     * 大部分护理依赖
     */
    @ExcelProperty(index = 3)
    private Double mostCareDependency;

    /**
     * 部分护理依赖
     */
    @ExcelProperty(index = 4)
    private Double partialNursingDependence;

    /**
     * 依据文件名称
     */
    @ExcelProperty(index = 5)
    private String bySource;
}
