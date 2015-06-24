package com.wonders.stpt.match.domain;

import java.io.Serializable;

/**
 * Created by Administrator on 2014/8/19.
 */
public class MatchTheme extends BaseDomain implements Serializable{

    private String matchThemeId;
    private Integer year;
    private String themeName;
    
    public MatchTheme() {
		super();
		
	}

	public String getMatchThemeId() {
        return matchThemeId;
    }

    public void setMatchThemeId(String matchThemeId) {
        this.matchThemeId = matchThemeId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }
}
