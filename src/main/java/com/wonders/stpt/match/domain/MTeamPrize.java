package com.wonders.stpt.match.domain;

import java.util.Date;

/**
 * MTeamPrize entity. @author MyEclipse Persistence Tools
 */

public class MTeamPrize extends BaseDomain implements java.io.Serializable {

	// Fields
	/**
	 * 主键
	 */
	private String teamPrizeId;
	/**
	 * 集体名称
	 */
	private String name;
	/**
	 * 人数
	 */
	private Integer persons;
	/**
	 * 责任人
	 */
	private String responsibilePerson;
	/**
	 * 联系电话
	 */
	private String telephone;
	/**
	 * 简要事迹
	 */
	private String introduct;
	
	/**
	 * 部门主键
	 */
	private String departmentId;

    private String prizeId;

    private MPrize prize;


	public String getTeamPrizeId() {
		return this.teamPrizeId;
	}

	public void setTeamPrizeId(String teamPrizeId) {
		this.teamPrizeId = teamPrizeId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPersons() {
		return this.persons;
	}

	public void setPersons(Integer persons) {
		this.persons = persons;
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
		return departmentId;
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