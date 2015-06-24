package com.wonders.stpt.match.domain;

import java.util.Date;
import java.util.List;

/**
 * MPrize entity. @author MyEclipse Persistence Tools奖项
 */

public class MPrize extends BaseDomain implements java.io.Serializable {

    public static final String PRIZE_TYPE_PERSON = "1";

    public static final String PRIZE_TYPE_TEAM = "2";

    public static final String PRIZE_TYPE_PROJECT = "3";

    public static final String PRIZE_TYPE_PROJECT_ACHIEVEMENT = "4";


    // Fields
    /**
     * 主键
     */
    private String prizeId;
    /**
     * 类型
     */
    private String prizeType;
    private String prizeSubType;
    /**
     * 名称
     */
    private String prizeName;
    /**
     * 数量
     */
    private Short quantity;
    /**
     * 奖金
     */
    private Double bonus;
    /**
     * 竞赛主键
     */
    private String matchId;
    private String personRange;

    private List<MApplicantDepartment> applicationDepartmentList;
    // Constructors

    /**
     * default constructor
     */
    public MPrize() {
    }

    /**
     * minimal constructor
     */
    public MPrize(String prizeId) {
        this.prizeId = prizeId;
    }

    /**
     * full constructor
     */
    public MPrize(String prizeId, String prizeType, String prizeName,
                  Short quantity, Double bonus, String matchId, String creator,
                  Date createTime, String updater, Date updateTime, String removed) {
        this.prizeId = prizeId;
        this.prizeType = prizeType;
        this.prizeName = prizeName;
        this.quantity = quantity;
        this.bonus = bonus;
        this.matchId = matchId;
    }

    // Property accessors

    public String getPrizeId() {
        return this.prizeId;
    }

    public void setPrizeId(String prizeId) {
        this.prizeId = prizeId;
    }

    public String getPrizeType() {
        return this.prizeType;
    }

    public void setPrizeType(String prizeType) {
        this.prizeType = prizeType;
    }

    public String getPrizeName() {
        return this.prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public List<MApplicantDepartment> getApplicationDepartmentList() {
        return applicationDepartmentList;
    }

    public void setApplicationDepartmentList(
            List<MApplicantDepartment> applicationDepartmentList) {
        this.applicationDepartmentList = applicationDepartmentList;
    }

    public Short getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Short quantity) {
        this.quantity = quantity;
    }

    public Double getBonus() {
        return this.bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    public String getMatchId() {
        return this.matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getPrizeSubType() {
        return prizeSubType;
    }

    public void setPrizeSubType(String prizeSubType) {
        this.prizeSubType = prizeSubType;
    }

    public String getPersonRange() {
        return personRange;
    }

    public void setPersonRange(String personRange) {
        this.personRange = personRange;
    }
}