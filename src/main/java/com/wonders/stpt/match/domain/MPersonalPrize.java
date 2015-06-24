package com.wonders.stpt.match.domain;

import java.util.Date;

/**
 * MPersonalPrize entity. @author MyEclipse Persistence Tools
 */

public class MPersonalPrize extends BaseDomain implements java.io.Serializable {

	// Fields
	/**
	 * 主键
	 */
	private String personalPrizeId;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 性别
	 */
	private String gender;
	/**
	 * 生日
	 */
	private Date brithday;
	/**
	 * 政治面貌
	 */
	private String political;
	/**
	 * 职位
	 */
	private String position;
	/**
	 * 工作单位
	 */
	private String department;
	/**
	 * 荣誉
	 */
	private String prizeInfo;
	/**
	 * 主要事迹
	 */
	private String introduct;
	
	/**
	 * 单位主键
	 */
	private String departmentId;

    private String prizeId;

    private MPrize prize;


	public String getPersonalPrizeId() {
		return this.personalPrizeId;
	}

	public void setPersonalPrizeId(String personalPrizeId) {
		this.personalPrizeId = personalPrizeId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBrithday() {
		return this.brithday;
	}

	public void setBrithday(Date brithday) {
		this.brithday = brithday;
	}

	public String getPolitical() {
		return this.political;
	}

	public void setPolitical(String political) {
		this.political = political;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPrizeInfo() {
		return this.prizeInfo;
	}

	public void setPrizeInfo(String prizeInfo) {
		this.prizeInfo = prizeInfo;
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