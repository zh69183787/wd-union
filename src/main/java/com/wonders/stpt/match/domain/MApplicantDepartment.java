package com.wonders.stpt.match.domain;

import java.util.Date;

/**
 * MApplicantDepartment entity. @author MyEclipse Persistence Tools参赛单位表
 */

public class MApplicantDepartment extends BaseDomain implements java.io.Serializable {

	// Fields
	/**
	 * 主键
	 */
	private String applicantDepartmentId;
	/**
	 * 单位主键
	 */
	private String departmentId;
	/**
	 * 单位名称
	 */
	private String departmentName;
	/**
	 * 奖项主键
	 */
	private String prizeId;

	// Constructors

	/** default constructor */
	public MApplicantDepartment() {
	}

	/** minimal constructor */
	public MApplicantDepartment(String applicantDepartmentId) {
		this.applicantDepartmentId = applicantDepartmentId;
	}

	/** full constructor */
	public MApplicantDepartment(String applicantDepartmentId,
			String departmentId, String departmentName, String prizeId,
			String creator, Date createTime, String updater, Date updateTime,
			String removed) {
		this.applicantDepartmentId = applicantDepartmentId;
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.prizeId = prizeId;
	}

	// Property accessors

	public String getApplicantDepartmentId() {
		return this.applicantDepartmentId;
	}

	public void setApplicantDepartmentId(String applicantDepartmentId) {
		this.applicantDepartmentId = applicantDepartmentId;
	}

	public String getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getPrizeId() {
		return this.prizeId;
	}

	public void setPrizeId(String prizeId) {
		this.prizeId = prizeId;
	}

}