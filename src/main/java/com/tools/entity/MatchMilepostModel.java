package com.tools.entity;

import lombok.Data;

@Data
public class MatchMilepostModel extends SqlModelGeneralFieldEntity{

    /**
     * 跟踪计划总线模板uuid
     */
    private String matchPlanBusModelCode;

    /**
     * 跟踪阶段
     */
    private Integer trackOrder;

    /**
     * 跟踪周期
     */
    private Integer trackCycle;

    /**
     * 应交付物
     */
    private String collectionItems;

    /**
     * 是否巡航跟踪 1.是;0.否
     */
    private Integer isCruiseTrack;
}
