package com.wonders.stpt.match.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2014/8/22.
 */
public class Match extends BaseDomain implements Serializable {

    public static final String NEW_STATUS = "0"; // 新建
    public static final String WAIT_STATUS = "1"; // 待审核
    public static final String SUCCESS_STATUS = "2"; // 审核通过
    public static final String FAIL_STATUS = "3"; // 审核未通过
    public static final String OVER_STATUS = "4"; // 结束

    public static final String MATCH_TYPE_PERSONAL = "1";
    public static final String MATCH_TYPE_TEAM = "2";
    public static final String MATCH_TYPE_PROJECT = "3";
    public static final String MATCH_TYPE_PROJECT_RESULT = "4";

    private String matchId;
    private String matchName;
    private String matchType;
    private Date beginDate;
    private Date endDate;
    private String departmentId;
    private String department;
    private String operatorId;
    private String operator;
    private String status;
    private String checkResult;
    private String checkId;
    private String checker;
    private String matchThemeId;

    private List<Attachment> attachments;
    private MatchTheme matchTheme;


    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }

    public String getCheckId() {
        return checkId;
    }

    public void setCheckId(String checkId) {
        this.checkId = checkId;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    public MatchTheme getMatchTheme() {
        return matchTheme;
    }

    public void setMatchTheme(MatchTheme matchTheme) {
        this.matchTheme = matchTheme;
    }

    public String getMatchThemeId() {
        return matchThemeId;
    }

    public void setMatchThemeId(String matchThemeId) {
        this.matchThemeId = matchThemeId;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }
}
