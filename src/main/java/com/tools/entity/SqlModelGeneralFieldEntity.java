package com.tools.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SqlModelGeneralFieldEntity {
    /**
     * 业务主键
     */
    private String uuid;

    /**
     * 1.有效;0.无效
     */
    private Integer isValid;

    /**
     * 数据创建时间
     */
    private Date gmtCreate;

    /**
     * 数据修改时间
     */
    private Date gmtModified;

    /**
     * 数据新建人
     */
    private String creator;

    /**
     * 数据修改人
     */
    private String modifier;

    /**
     * 数据租户
     */
    private String tenantCode;
}
