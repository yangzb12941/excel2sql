package com.tools.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.tools.entity.MatchMilepostModel;
import com.tools.entity.MatchPlanBusModel;
import com.tools.excelCell.TrackingPolicyExcelCell;
import com.tools.sqlWriter.SqlWriter2Txt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 跟踪策略 规则excel文件导入
 */
public class TrackingPolicyExcelImportTools extends ExcelImportTools<TrackingPolicyExcelCell>{
    private static final Logger LOGGER = LoggerFactory.getLogger(TrackingPolicyExcelImportTools.class);
    private static final Date curDate = new Date();
    //插入数据脚本，写入文件
    private SqlWriter2Txt sqlWriter2Txt;

    private final String sqlTemplate_1 = "insert into match_plan_bus_model (uuid,injury_condition,basic_diagnosis,basic_diagnosis_code,diagnosis_plan,treatment_plan,all_track_times,have_cruise_track,is_default_bus," +
            "creator,modifier,tenant_code,gmt_create,gmt_modified,is_valid)";
    private final String sqlTemplate_2 = "insert into match_milepost_model (uuid,match_plan_bus_model_code,track_order,track_cycle,collection_items,is_cruise_track," +
            "creator,modifier,tenant_code,gmt_create,gmt_modified,is_valid)";

    public TrackingPolicyExcelImportTools(SqlWriter2Txt sqlWriter2Txt) {
        this.sqlWriter2Txt = sqlWriter2Txt;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data
     *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(TrackingPolicyExcelCell data, AnalysisContext context) {
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
        List<TrackingPolicyExcelCell> excelCellList = excelDataToDaoModel();
        HashMap<MatchPlanBusModel, List<MatchMilepostModel>> matchPlanBusModelEntityListHashMap = creatMatchPlanBusModelEntity(excelCellList);
        List<MatchPlanBusModel> matchPlanBusModelList = new ArrayList<>(matchPlanBusModelEntityListHashMap.size());
        for (Map.Entry<MatchPlanBusModel, List<MatchMilepostModel>> entity : matchPlanBusModelEntityListHashMap.entrySet()) {
            sqlWriter2Txt.writer2Text(entity.getValue(),sqlTemplate_2);
            matchPlanBusModelList.add(entity.getKey());
        }
        sqlWriter2Txt.writer2Text(matchPlanBusModelList,sqlTemplate_1);
    }

    private List<TrackingPolicyExcelCell> excelDataToDaoModel(){
        List<TrackingPolicyExcelCell> excelCellList = new ArrayList<TrackingPolicyExcelCell>(super.list.size());
        super.list.stream().forEach((e)->{
            TrackingPolicyExcelCell excelCell = new TrackingPolicyExcelCell();
            excelCell.setBasicDiagnosis(e.getBasicDiagnosis());
            excelCell.setBasicDiagnosisCode(e.getBasicDiagnosisCode());
            excelCell.setInjuryDescription(e.getInjuryDescription());
            excelCell.setDiagnosisPlan(e.getDiagnosisPlan());
            excelCell.setIsDisability(e.getIsDisability());
            excelCell.setFirstTracking(e.getFirstTracking());
            excelCell.setFirstDeliverables(e.getFirstDeliverables());
            excelCell.setSecondTracking(e.getSecondTracking());
            excelCell.setSecondDeliverables(e.getSecondDeliverables());
            excelCell.setThirdTracking(e.getThirdTracking());
            excelCell.setThirdDeliverables(e.getThirdDeliverables());
            excelCell.setFourthTracking(e.getFourthTracking());
            excelCell.setFourthDeliverables(e.getFourthDeliverables());
            excelCell.setFirstTracking(e.getFirstTracking());
            excelCell.setFifthDeliverables(e.getFifthDeliverables());
            excelCell.setSixthTracking(e.getSixthTracking());
            excelCell.setSixthDeliverables(e.getSixthDeliverables());
            excelCell.setCruiseTracking(e.getCruiseTracking());
            excelCell.setCruiseDeliverables(e.getCruiseDeliverables());
            excelCellList.add(excelCell);
        });
        return excelCellList;
    }


    private HashMap<MatchPlanBusModel,List<MatchMilepostModel>> creatMatchPlanBusModelEntity(List<TrackingPolicyExcelCell> excelCellList){
        HashMap<MatchPlanBusModel,List<MatchMilepostModel>> mpbModels = new HashMap<MatchPlanBusModel,List<MatchMilepostModel>>(excelCellList.size());
        for (TrackingPolicyExcelCell cell:excelCellList) {
            List<MatchMilepostModel> mmmEntitys= new ArrayList<MatchMilepostModel>(8);
            MatchPlanBusModel mpbModel = new MatchPlanBusModel();
            mpbModel.setUuid(UUID.randomUUID().toString().replace("-",""));
            //是否有巡航跟踪
            mpbModel.setHaveCruiseTrack(0);
            //没有基础诊断，解析是否是如下跟踪策略：
            // 轻伤-未就诊、死亡-未就诊、死亡-门诊、死亡-住院、通用跟踪策略-门诊、通用跟踪策略-住院
            if(Objects.isNull(cell.getBasicDiagnosisCode())){
                String[] split = cell.getBasicDiagnosis().split("-");
                //轻伤、死亡、通用跟踪策略 当作基础诊断
                mpbModel.setInjuryCondition(split[0]);
                //未就诊、门诊、住院、当作基础诊断
                mpbModel.setDiagnosisPlan(split[1]);
                if(split[0].equals("通用跟踪策略")){
                    //是否通用跟踪策略
                    mpbModel.setIsDefaultBus(1);
                }else{
                    //是否通用跟踪策略
                    mpbModel.setIsDefaultBus(0);
                }
                //基础诊断
                mpbModel.setBasicDiagnosis(null);
                //基础诊断编码
                mpbModel.setBasicDiagnosisCode(null);
                //治疗方案:手术治疗、保守治疗
                mpbModel.setTreatmentPlan(cell.getDiagnosisPlan());
            }else{
                //基础诊断
                mpbModel.setBasicDiagnosis(cell.getBasicDiagnosis());
                //基础诊断编码
                mpbModel.setBasicDiagnosisCode(cell.getBasicDiagnosisCode());
                //伤情
                mpbModel.setInjuryCondition(cell.getInjuryDescription());
                //诊疗方式
                mpbModel.setDiagnosisPlan(null);
                //治疗方案
                mpbModel.setTreatmentPlan(cell.getDiagnosisPlan());
                //是否通用跟踪策略
                mpbModel.setIsDefaultBus(0);
            }
            if(Objects.nonNull(cell.getFirstTracking())){
                MatchMilepostModel mmmEntity = new MatchMilepostModel();
                mmmEntity.setUuid(UUID.randomUUID().toString().replace("-",""));
                mmmEntity.setMatchPlanBusModelCode(mpbModel.getUuid());
                mmmEntity.setTrackOrder(1);
                mmmEntity.setTrackCycle(cell.getFirstTracking());
                mmmEntity.setCollectionItems(cell.getFirstDeliverables());
                mmmEntity.setIsCruiseTrack(0);

                mmmEntity.setIsValid(1);
                mmmEntity.setGmtCreate(curDate);
                mmmEntity.setGmtModified(curDate);
                mmmEntity.setCreator("system");
                mmmEntity.setModifier("system");
                mmmEntity.setTenantCode("injury-vehicle-track");
                mmmEntitys.add(mmmEntity);
            }

            if(Objects.nonNull(cell.getSecondTracking())){
                MatchMilepostModel mmmEntity = new MatchMilepostModel();
                mmmEntity.setUuid(UUID.randomUUID().toString().replace("-",""));
                mmmEntity.setMatchPlanBusModelCode(mpbModel.getUuid());
                mmmEntity.setTrackOrder(2);
                mmmEntity.setTrackCycle(cell.getSecondTracking());
                mmmEntity.setCollectionItems(cell.getSecondDeliverables());
                mmmEntity.setIsCruiseTrack(0);

                mmmEntity.setIsValid(1);
                mmmEntity.setGmtCreate(curDate);
                mmmEntity.setGmtModified(curDate);
                mmmEntity.setCreator("system");
                mmmEntity.setModifier("system");
                mmmEntity.setTenantCode("injury-vehicle-track");
                mmmEntitys.add(mmmEntity);
            }

            if(Objects.nonNull(cell.getThirdTracking())){
                MatchMilepostModel mmmEntity = new MatchMilepostModel();
                mmmEntity.setUuid(UUID.randomUUID().toString().replace("-",""));
                mmmEntity.setMatchPlanBusModelCode(mpbModel.getUuid());
                mmmEntity.setTrackOrder(3);
                mmmEntity.setTrackCycle(cell.getThirdTracking());
                mmmEntity.setCollectionItems(cell.getThirdDeliverables());
                mmmEntity.setIsCruiseTrack(0);

                mmmEntity.setIsValid(1);
                mmmEntity.setGmtCreate(curDate);
                mmmEntity.setGmtModified(curDate);
                mmmEntity.setCreator("system");
                mmmEntity.setModifier("system");
                mmmEntity.setTenantCode("injury-vehicle-track");
                mmmEntitys.add(mmmEntity);
            }

            if(Objects.nonNull(cell.getFourthTracking())){
                MatchMilepostModel mmmEntity = new MatchMilepostModel();
                mmmEntity.setUuid(UUID.randomUUID().toString().replace("-",""));
                mmmEntity.setMatchPlanBusModelCode(mpbModel.getUuid());
                mmmEntity.setTrackOrder(4);
                mmmEntity.setTrackCycle(cell.getFourthTracking());
                mmmEntity.setCollectionItems(cell.getFourthDeliverables());
                mmmEntity.setIsCruiseTrack(0);

                mmmEntity.setIsValid(1);
                mmmEntity.setGmtCreate(curDate);
                mmmEntity.setGmtModified(curDate);
                mmmEntity.setCreator("system");
                mmmEntity.setModifier("system");
                mmmEntity.setTenantCode("injury-vehicle-track");
                mmmEntitys.add(mmmEntity);
            }

            if(Objects.nonNull(cell.getFifthTracking())){
                MatchMilepostModel mmmEntity = new MatchMilepostModel();
                mmmEntity.setUuid(UUID.randomUUID().toString().replace("-",""));
                mmmEntity.setMatchPlanBusModelCode(mpbModel.getUuid());
                mmmEntity.setTrackOrder(5);
                mmmEntity.setTrackCycle(cell.getFifthTracking());
                mmmEntity.setCollectionItems(cell.getFifthDeliverables());
                mmmEntity.setIsCruiseTrack(0);

                mmmEntity.setIsValid(1);
                mmmEntity.setGmtCreate(curDate);
                mmmEntity.setGmtModified(curDate);
                mmmEntity.setCreator("system");
                mmmEntity.setModifier("system");
                mmmEntity.setTenantCode("injury-vehicle-track");
                mmmEntitys.add(mmmEntity);
            }

            if(Objects.nonNull(cell.getSixthTracking())){
                MatchMilepostModel mmmEntity = new MatchMilepostModel();
                mmmEntity.setUuid(UUID.randomUUID().toString().replace("-",""));
                mmmEntity.setMatchPlanBusModelCode(mpbModel.getUuid());
                mmmEntity.setTrackOrder(6);
                mmmEntity.setTrackCycle(cell.getSixthTracking());
                mmmEntity.setCollectionItems(cell.getSixthDeliverables());
                mmmEntity.setIsCruiseTrack(0);

                mmmEntity.setIsValid(1);
                mmmEntity.setGmtCreate(curDate);
                mmmEntity.setGmtModified(curDate);
                mmmEntity.setCreator("system");
                mmmEntity.setModifier("system");
                mmmEntity.setTenantCode("injury-vehicle-track");
                mmmEntitys.add(mmmEntity);
            }

            if(Objects.nonNull(cell.getCruiseTracking())){
                MatchMilepostModel mmmEntity = new MatchMilepostModel();
                mmmEntity.setUuid(UUID.randomUUID().toString().replace("-",""));
                mmmEntity.setMatchPlanBusModelCode(mpbModel.getUuid());
                mmmEntity.setTrackOrder(7);
                mmmEntity.setTrackCycle(cell.getCruiseTracking());
                mmmEntity.setCollectionItems(cell.getCruiseDeliverables());
                mmmEntity.setIsCruiseTrack(1);

                mmmEntity.setIsValid(1);
                mmmEntity.setGmtCreate(curDate);
                mmmEntity.setGmtModified(curDate);
                mmmEntity.setCreator("system");
                mmmEntity.setModifier("system");
                mmmEntity.setTenantCode("injury-vehicle-track");
                mmmEntitys.add(mmmEntity);
                mpbModel.setHaveCruiseTrack(1);
            }
            mpbModel.setIsValid(1);
            mpbModel.setGmtCreate(curDate);
            mpbModel.setGmtModified(curDate);
            mpbModel.setCreator("system");
            mpbModel.setModifier("system");
            mpbModel.setTenantCode("injury-vehicle-track");
            //跟踪次数
            mpbModel.setAllTrackTimes(mmmEntitys.size());
            mpbModels.put(mpbModel,mmmEntitys);
        }
        return mpbModels;
    }
}
