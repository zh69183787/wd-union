package com.wonders.stpt.match.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2014/8/19.
 */
public class BaseDomain implements Serializable {

    public static final String NORMAL="0";
    public static final String DELETE="1";

    private Date createTime;
    private Date updateTime;
    private String creator;
    private String updater;
    private String removed;

    public BaseDomain(){
        removed = NORMAL;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemoved() {
        return removed;
    }

    public void setRemoved(String removed) {
        this.removed = removed;
    }
}
