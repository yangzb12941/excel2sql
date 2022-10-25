package com.tools.entity;

import lombok.Data;

@Data
public class InsuranceLabelLibraryModel extends SqlModelGeneralFieldEntity{

    /**
     * 保险责任风险类别
     */
    private String insuranceLiabilityRiskType;

    /**
     * 保险责任标签
     */
    private String insuranceLiabilityLabel;

    /**
     * 触发条件
     */
    private String triggerCondition;

    /**
     * 风险提示内容
     */
    private String riskContent;
}
