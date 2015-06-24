package com.wonders.stpt.match.service;

import java.util.List;

import com.wonders.stpt.match.domain.MatchTheme;
import com.wonders.stpt.utils.paginator.mybatis.domain.PageList;

/**
 * Created by Administrator on 2014/8/19.
 */
public interface IMatchThemeService {
	/**
	 * 分页查询数据
	 * @param theme
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
    PageList<MatchTheme> getThemes(MatchTheme theme,Integer pageIndex,Integer pageSize)throws Exception;
    /**
     * 保存数据
     * @param matchTheme（实体对象）
     * @param excludes（保存时忽略的匹配属性）
     * @throws Exception
     */
    void save(MatchTheme matchTheme,String[] excludes)throws Exception;
    /**
     * 根据主键查询记录
     * @param matchThemeId
     * @return
     * @throws Exception
     */
    MatchTheme getMatchTheme(String matchThemeId)throws Exception;
    /**
     * 批量删除(逻辑删除)
     * @param matchThemeIds
     * @return
     * @throws Exception
     */
    int deletes(List<String> matchThemeIds)throws Exception;
    /**
     * 根据主键删除
     * @param matchThemeId
     * @return
     * @throws Exception
     */
    int deletes(String matchThemeId)throws Exception;
}
