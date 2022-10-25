package com.tools.sqlWriter;

import com.tools.FiledIndex.FieldMapping;
import com.tools.entity.SqlModelGeneralFieldEntity;
import com.tools.utils.TrackingPolicyExcelImportTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SqlWriter2Txt {
    private static final Logger LOGGER = LoggerFactory.getLogger(SqlWriter2Txt.class);
    private volatile static SqlWriter2Txt sqlWriter2Txt = null;

    private SqlWriter2Txt() {}

    public static SqlWriter2Txt getInstance(){
        if(Objects.isNull(sqlWriter2Txt)){
            synchronized (SqlWriter2Txt.class){
                if(Objects.nonNull(sqlWriter2Txt)){
                    return sqlWriter2Txt;
                }else{
                    sqlWriter2Txt = new SqlWriter2Txt();
                    return sqlWriter2Txt;
                }
            }
        }else{
            return sqlWriter2Txt;
        }
    }

    /**
     * 把结果集写入sql文件
     * @param data 结果集
     * @param sqlTemplate sql 模板
     * @param filedMap 字段映射，如果data 字段与模板字段不匹配，那么通过filedMap指定映射
     *                 key:sql字段；value:data字段
     * @throws IOException
     */
    public void writer2Text(List<? extends SqlModelGeneralFieldEntity> data, String sqlTemplate, Map<String,String> filedMap){
        String tableName = tableName(sqlTemplate);
        List<String> sqlStatements = new ArrayList<>(data.size());
        for (SqlModelGeneralFieldEntity item : data) {
            String sqlStatement = analysisFiled(item, sqlTemplate,filedMap);
            sqlStatements.add(sqlStatement);
        }
        try{
            dumpToFile(tableName,sqlStatements);
        }catch (Exception e) {
            LOGGER.error("writer2Text error{}",e);
        }
    }

    /**
     * 把结果集写入sql文件
     * @param data 结果集
     * @param sqlTemplate sql 模板
     * @throws IOException
     */
    public void writer2Text(List<? extends SqlModelGeneralFieldEntity> data, String sqlTemplate) {
        writer2Text(data,sqlTemplate,null);
    }

    /**
     * 拼接insert sql 语句
     * @param item
     * @param sqlTemplate
     * @return
     */
    private String analysisFiled(SqlModelGeneralFieldEntity item,String sqlTemplate,Map<String,String> filedMap){
        List<FieldMapping> fieldMappings = null;
        if(Objects.nonNull(filedMap)){
            fieldMappings = sqlField(item, sqlTemplate,filedMap);
        }else{
            fieldMappings = sqlField(item, sqlTemplate);
        }
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append(sqlTemplate);
        sqlStatement.append("values");
        sqlStatement.append("(");
        for (int i = 0; i < fieldMappings.size(); i++) {
            sqlStatement.append("'"+fieldMappings.get(i).getValue()+"'");
            sqlStatement.append(",");
        }
        //去掉最后一个,号
        String substring = sqlStatement.substring(0, sqlStatement.length() - 1);
        substring = substring+");"+System.lineSeparator();
        return substring;
    }

    private List<FieldMapping> sqlField(SqlModelGeneralFieldEntity item,String sqlTemplate){
        //通过反射获取实体类字段
        Class<? extends SqlModelGeneralFieldEntity> subClass = item.getClass();
        Class<?> superClass = subClass.getSuperclass();
        Method[] subMethods = subClass.getDeclaredMethods();
        Method[] supMethods = superClass.getDeclaredMethods();
        Method[] unionMethods = new Method[subMethods.length+supMethods.length];
        System.arraycopy(subMethods, 0, unionMethods, 0,subMethods.length);
        System.arraycopy(supMethods, 0, unionMethods, subMethods.length,supMethods.length);
        String[] tableFields = tableFields(sqlTemplate);
        List<FieldMapping> fieldMappings = new ArrayList<>(tableFields.length);
        try{
            for (int i = 0; i < tableFields.length; i++) {
                String camelCase = toCamelCase(tableFields[i]);
                for (Method objectMethod : unionMethods ) {
                    String methodName = objectMethod.getName();
                    if(methodName.startsWith("get")){
                        if (methodName.toLowerCase().equals("get"+camelCase.toLowerCase())) {
                            FieldMapping fieldMapping = new FieldMapping();
                            fieldMapping.setSqlField(tableFields[i]);
                            fieldMapping.setDataField(camelCase);
                            fieldMapping.setValue(Objects.nonNull(objectMethod.invoke(item))?objectMethod.invoke(item).toString():null);
                            fieldMappings.add(fieldMapping);
                            break;
                        }
                    }
                }
            }
        }catch (IllegalAccessException | InvocationTargetException e) {
            LOGGER.error("{}",e);
        }
        return fieldMappings;
    }

    /**
     * 把对象对应字段值，映射到sql模板中。
     * @param item
     * @param sqlTemplate sql 模板
     * @param filedMap 字段映射，如果data 字段与模板字段不匹配，那么通过filedMap指定映射
     *                 key:sql字段；value:data字段
     * @return
     */
    private List<FieldMapping> sqlField(SqlModelGeneralFieldEntity item,String sqlTemplate,Map<String,String> filedMap){
        //通过反射获取实体类字段
        Class<? extends SqlModelGeneralFieldEntity> subClass = item.getClass();
        Class<?> superClass = subClass.getSuperclass();
        Method[] subMethods = subClass.getDeclaredMethods();
        Method[] supMethods = superClass.getDeclaredMethods();
        Method[] unionMethods = new Method[subMethods.length+supMethods.length];
        System.arraycopy(subMethods, 0, unionMethods, 0,subMethods.length);
        System.arraycopy(supMethods, 0, unionMethods, subMethods.length,supMethods.length);
        String[] tableFields = tableFields(sqlTemplate);
        List<FieldMapping> fieldMappings = new ArrayList<>(tableFields.length);
        try{
            for (int i = 0; i < tableFields.length; i++) {
                String camelCase = "";
                if(filedMap.containsKey(tableFields[i])){
                    camelCase = filedMap.get(tableFields[i]);
                }else{
                    camelCase = toCamelCase(tableFields[i]);
                }
                for (Method objectMethod : unionMethods ) {
                    if (objectMethod.getName().toLowerCase().equals("get"+camelCase.toLowerCase())) {
                        FieldMapping fieldMapping = new FieldMapping();
                        fieldMapping.setSqlField(tableFields[i]);
                        fieldMapping.setDataField(camelCase);
                        fieldMapping.setValue(objectMethod.invoke(item).toString());
                        fieldMappings.add(fieldMapping);
                        continue;
                    }
                }
            }
        }catch (IllegalAccessException | InvocationTargetException e) {
            LOGGER.error("{}",e);
        }
        return fieldMappings;
    }

    /**
     * 解析sql模板中的表字段
     * @param sqlTemplate
     * @return
     */
    private String[] tableFields(String sqlTemplate){
        int left = sqlTemplate.indexOf("(");
        int right = sqlTemplate.indexOf(")");
        String substring = sqlTemplate.substring(left+1, right);
        String[] split = substring.split(",");
        for (int i = 0; i < split.length; i++) {
            split[i] = split[i].trim();
        }
        return split;
    }

    /**
     * 把字段转化为驼峰命名
     * @param filed
     * @return
     */
    private String toCamelCase(String filed){
        StringBuilder camelCase = new StringBuilder();
        boolean isSeparator = false;
        for (char c : filed.toCharArray()) {
            if(isSeparator){
                c = (char)(c-32);
                camelCase.append(c);
                isSeparator = false;
                continue;
            }
            if(c == '_'){
                isSeparator = true;
                continue;
            }
            camelCase.append(c);
        }
        return camelCase.toString();
    }

    private String tableName(String sqlTemplate){
        int indexOf = sqlTemplate.indexOf("(");
        String tmpString = sqlTemplate.substring(0,indexOf).trim();
        int lastIndexOf = tmpString.lastIndexOf(" ");
        String tableName = tmpString.substring(lastIndexOf, tmpString.length()).trim();
        return tableName;
    }

    private void dumpToFile(String fileName,List<String> dumpContent) throws IOException {
        BufferedWriter out = null;
        try {
            File directory =new File("D:\\基础数据脚本");
               //如果文件夹不存在则创建
            if(!directory.exists()  && !directory.isDirectory()){
                directory.mkdir();
            }

            File file = new File("D:\\基础数据脚本\\"+fileName+".sql");
            if(!file.exists()){
                file.createNewFile();
            }
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true)));
            for (String data:dumpContent) {
                out.write(data);
            }
        }catch (IOException e) {
            LOGGER.error("dumpToFile error{}",e);
        }finally {
            if(Objects.nonNull(out)){
                out.flush();
                out.close();
            }
        }
    }
}
