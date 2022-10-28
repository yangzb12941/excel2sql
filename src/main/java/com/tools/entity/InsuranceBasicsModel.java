package com.tools.entity;

import lombok.Data;

@Data
public class InsuranceBasicsModel extends SqlModelGeneralFieldEntity{
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
     * 条款代码
     */
    private String authorityName;

    /**
     * 条款文件oss文件存储url
     */
    private String url;
}
