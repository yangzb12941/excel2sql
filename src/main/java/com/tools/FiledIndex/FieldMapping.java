package com.tools.FiledIndex;

import lombok.Data;

@Data
public class FieldMapping {
    //对象字段名
    private String dataField;
    //sql模板字段名
    private String sqlField;
    //匹配字段值
    private String value;
}
