package com.tools.entity;

import lombok.Data;

@Data
public class InsuranceClausesModel extends SqlModelGeneralFieldEntity{
    /**
     * 条款代码
     */
    private String clauseCode;

    /**
     * 条款名称
     */
    private String clauseName;

    /**
     * 一级责任代码
     */
    private String oneLevelClause;

    /**
     * 二级责任代码
     */
    private String twoLevelClause;

    /**
     * 责任名称
     */
    private String authorityName;

    /**
     * 可赔付项目及费用类型
     */
    private String feeTypeCd;

    /**
     * 可赔付项目及费用名称
     */
    private String feeTypeName;

    /**
     * 赔付条件
     */
    private String compensationConditions;

    /**
     * 赔付条件
     */
    private String payFormulaConditions;

    /**
     * 应付损失公式条件
     */
    private String payaFormula;

    /**
     * 理算公式条件
     */
    private String adjustmentFormulaConditions;

    /**
     * 理算公式
     */
    private String adjustmentFormula;
}
