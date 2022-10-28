package com.tools.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.tools.entity.SqlModelGeneralFieldEntity;
import com.tools.excelCell.ExcelCell;
import com.tools.sqlWriter.SqlWriter2Txt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DefaultExcelImporTools extends ExcelImportTools<ExcelCell>{
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultExcelImporTools.class);
    private static final Date curDate = new Date();

    //插入数据脚本，写入文件
    private SqlWriter2Txt sqlWriter2Txt;

    private final String sqlTemplate;

    private final Class zlass;

    public DefaultExcelImporTools(SqlWriter2Txt sqlWriter2Txt,String sqlTemplate,Class zlass) {
        this.sqlWriter2Txt = sqlWriter2Txt;
        this.sqlTemplate = sqlTemplate;
        this.zlass = zlass;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data
     *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(ExcelCell data, AnalysisContext context) {
        super.invoke(data,context);
    }
    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        super.doAfterAllAnalysed(context);
    }

    @Override
    public void doSomething() {
        List<SqlModelGeneralFieldEntity> excelCellList = excelDataToDaoModel();
        sqlWriter2Txt.writer2Text(excelCellList,this.sqlTemplate);
    }

    private List<SqlModelGeneralFieldEntity> excelDataToDaoModel(){
        List<SqlModelGeneralFieldEntity> excelCellList = new ArrayList<SqlModelGeneralFieldEntity>(super.list.size());
        super.list.stream().forEach((e)->{
            //1、通过反射获取 e 属性并且获取其值
            Method[] declaredMethods = e.getClass().getDeclaredMethods();
            Object valueFromCell = getValueFromCell(declaredMethods, e);
            excelCellList.add((SqlModelGeneralFieldEntity)valueFromCell);
        });
        return excelCellList;
    }

    /**
     * 根据反射 解析出读取的数据并塞入指定的对象。
     * @param declaredMethods
     * @param o
     */
    private Object getValueFromCell(Method[] declaredMethods,Object o){
        Object target = null;
        try{
            target = this.zlass.newInstance();
            for (Method method : declaredMethods) {
                String methodName = method.getName();
                if(methodName.startsWith("get")){
                    String invoke = (String)method.invoke(o);
                    String targetMethodName = methodName.substring(3,methodName.length());
                    final Method targetMethod = BeanUtils.findMethod(this.zlass, "set" + targetMethodName, String.class);
                    targetMethod.invoke(target, invoke);
                }
            }
        }catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
            LOGGER.error("{}",e);
        }
        return target;
    }
}
