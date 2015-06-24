package com.wonders.stpt.match.domain;

import java.util.Date;

/**
 * MProjectAchievement entity. @author MyEclipse Persistence Tools项目成功类申报资料表
 */

public class MProjectAchievement extends BaseDomain implements java.io.Serializable {

	// Fields
	/**
	 * 主键
	 */
	private String projectAchievementId;
	/**
	 * 参赛模块
	 */
	private String module;
	/**
	 * 项目名称
	 */
	private String prjectName;
	/**
	 * 申报集体名称
	 */
	private String department;
	/**
	 * 责任人
	 */
	private String responsibilePerson;
	/**
	 * 联系电话
	 */
	private String telephone;
	/**
	 * 项目简介
	 */
	private String introduct;
	/**
	 * 单位主键
	 */
	private String departmentId;

    private String prizeId;

    private MPrize prize;


	public String getProjectAchievementId() {
		return this.projectAchievementId;
	}

	public void setProjectAchievementId(String projectAchievementId) {
		this.projectAchievementId = projectAchievementId;
	}

	public String getModule() {
		return this.module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getPrjectName() {
		return this.prjectName;
	}

	public void setPrjectName(String prjectName) {
		this.prjectName = prjectName;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getResponsibilePerson() {
		return this.responsibilePerson;
	}

	public void setResponsibilePerson(String responsibilePerson) {
		this.responsibilePerson = responsibilePerson;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getIntroduct() {
		return this.introduct;
	}

	public void setIntroduct(String introduct) {
		this.introduct = introduct;
	}

	public String getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

    public String getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(String prizeId) {
        this.prizeId = prizeId;
    }

    public MPrize getPrize() {
        return prize;
    }

    public void setPrize(MPrize prize) {
        this.prize = prize;
    }
}