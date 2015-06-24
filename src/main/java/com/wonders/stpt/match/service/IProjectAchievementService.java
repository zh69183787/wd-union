package com.wonders.stpt.match.service;

import java.util.List;


import com.wonders.stpt.match.domain.MProjectAchievement;
import com.wonders.stpt.utils.paginator.mybatis.domain.PageList;

/**
 * 项目成功类申报资料
 * @author shanweifeng
 *
 */
public interface IProjectAchievementService {
	/**
	 * 分页查询
	 * @param projectPrize
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	PageList<MProjectAchievement> getProjectAchievements(MProjectAchievement projectAchievement,Integer pageIndex,Integer pageSize)throws Exception;
	/**
	 * 条件查询(不分页)
	 * @param projectPrize
	 * @return
	 * @throws Exception
	 */
	PageList<MProjectAchievement> getProjectAchievements(MProjectAchievement projectAchievement)throws Exception;
	/**
	 * 根据主键查询
	 * @param projectPrizeId
	 * @return
	 * @throws Exception
	 */
	MProjectAchievement getProjectAchievement(String projectAchievementId)throws Exception;
	/**
	 * 批量删除
	 * @param projectPrizeIds
	 * @return
	 * @throws Exception
	 */
	int delete(List<String> projectAchievementIds)throws Exception;
	/**
	 * 根据主键删除
	 * @param projectPrizeId
	 * @return
	 * @throws Exception
	 */
	int delete(String projectAchievementId)throws Exception;
	/**
	 * 保存
	 * @param projectPrize
	 * @throws Exception
	 */
	void save(MProjectAchievement projectAchievement)throws Exception;
	/**
	 * 更新
	 * @param projectPrize
	 * @param excludes
	 * @param isDynamic(是否动态更新)
	 * @throws Exception
	 */
	void update(MProjectAchievement projectAchievement,String[] excludes,boolean isDynamic)throws Exception;
}
