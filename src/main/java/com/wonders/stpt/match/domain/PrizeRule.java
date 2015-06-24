package com.wonders.stpt.match.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2014/9/10.
 */
public class PrizeRule extends BaseDomain implements Serializable {

    private String prizeRuleId;
    private String prizeType;
    private String prizeSubType;
    private String personRange;
    private Double maxBonus;
    private Double minBonus;

    public String getPrizeRuleId() {
        return prizeRuleId;
    }

    public void setPrizeRuleId(String prizeRuleId) {
        this.prizeRuleId = prizeRuleId;
    }

    public String getPrizeType() {
        return prizeType;
    }

    public void setPrizeType(String prizeType) {
        this.prizeType = prizeType;
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

    public Double getMaxBonus() {
        return maxBonus;
    }

    public void setMaxBonus(Double maxBonus) {
        this.maxBonus = maxBonus;
    }

    public Double getMinBonus() {
        return minBonus;
    }

    public void setMinBonus(Double minBonus) {
        this.minBonus = minBonus;
    }
}
