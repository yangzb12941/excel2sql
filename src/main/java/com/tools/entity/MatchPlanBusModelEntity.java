package com.tools.entity;

import lombok.Data;

@Data
public class MatchPlanBusModelEntity extends SqlModelGeneralFieldEntity{

    /**
     * 伤情
     */
    private String injuryCondition;

    /**
     * 基础诊断
     */
    private String basicDiagnosis;

    /**
     * 基础诊断编码
     */
    private String basicDiagnosisCode;

    /**
     * 诊疗方式:未就诊、门诊、住院
     */
    private String diagnosisPlan;

    /**
     * 治疗方案:手术治疗、保守治疗
     */
    private String treatmentPlan;

    /**
     * 是否通用跟踪策略
     */
    private Integer isDefaultBus;

    /**
     * 跟踪次数
     */
    private Integer allTrackTimes;

    /**
     * 是否有巡航跟踪
     */
    private Integer haveCruiseTrack;
}
