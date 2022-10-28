package com.tools.entity;

import lombok.Data;

@Data
public class InsuranceLiabilityModel extends SqlModelGeneralFieldEntity{
    /**
     * 条款代码
     */
    private String clauseCode;

    /**
     * 条款名称
     */
    private String clauseName;

    /**
     * 保险责任标
     */
    private String insuranceLiability;

    /**
     * 保险责任标签
     */
    private String insuranceLiabilityLabel;
}
