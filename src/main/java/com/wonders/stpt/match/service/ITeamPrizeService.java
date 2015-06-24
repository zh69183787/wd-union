package com.wonders.stpt.match.service;

import java.util.List;

import com.wonders.stpt.match.domain.MTeamPrize;
import com.wonders.stpt.utils.paginator.mybatis.domain.PageList;

/**
 * 集体奖项申报资料接口
 * @author shanweifeng
 *
 */
public interface ITeamPrizeService {
	/**
	 * 分页查询
	 * @param mTeamPrize
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	PageList<MTeamPrize> getMTeamPrizes(MTeamPrize mTeamPrize,Integer pageIndex,Integer pageSize)throws Exception;
	/**
	 * 条件查询（不分页）
	 * @param mTeamPrize
	 * @return
	 * @throws Exception
	 */
	PageList<MTeamPrize> getMTeamPrizes(MTeamPrize mTeamPrize)throws Exception;
	/**
	 * 根据主键查询
	 * @param mTeamPrize
	 * @return
	 * @throws Exception
	 */
	MTeamPrize getMTeamPrize(String mTeamPrizeId)throws Exception;
	/**
	 * 批量删除
	 * @param mTeamPrizeIds
	 * @return
	 * @throws Exception
	 */
	int delete(List<String> mTeamPrizeIds)throws Exception;
	/**
	 * 根据主键删除
	 * @param mTeamPrizeId
	 * @return
	 * @throws Exception
	 */
	int delete(String mTeamPrizeId)throws Exception;
	/**
	 * 保存记录
	 * @param mTeamPrize
	 * @throws Exception
	 */
	void save(MTeamPrize mTeamPrize)throws Exception;
	/**
	 * 更新记录
	 * @param mTeamPrize
	 * @param excludes
	 * @param isDynamic（是否动态更新）
	 * @throws Exception
	 */
	void update(MTeamPrize mTeamPrize,String[] excludes,boolean isDynamic)throws Exception;
}
