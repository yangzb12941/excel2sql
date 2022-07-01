package com.tools.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSONObject;
import com.tools.excelCell.ExcelCell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 考试试题导入工具基类
 * @param <T>
 */
public abstract class ExcelImportTools<T extends ExcelCell> extends AnalysisEventListener<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelImportTools.class);
    private static final int BATCH_COUNT = 300;
    List<T> list = new ArrayList<T>(BATCH_COUNT);

    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        LOGGER.info("解析到一条数据:{}", JSONObject.toJSONString(t));
        list.add(t);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            doSomething();
            // 存储完成清理 list
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        doSomething();
    }

    //子类自定义实现读取excel之后进行的操作
    public abstract void doSomething();
}
