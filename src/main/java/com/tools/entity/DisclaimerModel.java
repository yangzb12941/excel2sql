package com.tools.entity;

import lombok.Data;

@Data
public class DisclaimerModel extends SqlModelGeneralFieldEntity{
    /**
     * 条款代码
     */
    private String clauseCode;

    /**
     * 条款名称
     */
    private String clauseName;

    /**
     * 免责类型
     */
    private String disclaimerType;

    /**
     * 免责事项
     */
    private String disclaimerDescription;

    /**
     * 免责标签
     */
    private String disclaimerLabel;
}
