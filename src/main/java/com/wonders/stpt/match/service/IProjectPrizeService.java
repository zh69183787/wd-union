package com.wonders.stpt.match.service;

import java.util.List;

import com.wonders.stpt.match.domain.MProjectPrize;
import com.wonders.stpt.utils.paginator.mybatis.domain.PageList;

/**
 * 项目类申报资料
 * @author shanweifeng
 *
 */
public interface IProjectPrizeService {
	/**
	 * 分页查询
	 * @param projectPrize
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	PageList<MProjectPrize> getProjectPrizes(MProjectPrize projectPrize,Integer pageIndex,Integer pageSize)throws Exception;
	/**
	 * 条件查询(不分页)
	 * @param projectPrize
	 * @return
	 * @throws Exception
	 */
	PageList<MProjectPrize> getProjectPrizes(MProjectPrize projectPrize)throws Exception;
	/**
	 * 根据主键查询
	 * @param projectPrizeId
	 * @return
	 * @throws Exception
	 */
	MProjectPrize getProjectPrize(String projectPrizeId)throws Exception;
	/**
	 * 批量删除
	 * @param projectPrizeIds
	 * @return
	 * @throws Exception
	 */
	int delete(List<String> projectPrizeIds)throws Exception;
	/**
	 * 根据主键删除
	 * @param projectPrizeId
	 * @return
	 * @throws Exception
	 */
	int delete(String projectPrizeId)throws Exception;
	/**
	 * 保存
	 * @param projectPrize
	 * @throws Exception
	 */
	void save(MProjectPrize projectPrize)throws Exception;
	/**
	 * 更新
	 * @param projectPrize
	 * @param excludes
	 * @param isDynamic(是否动态更新)
	 * @throws Exception
	 */
	void update(MProjectPrize projectPrize,String[] excludes,boolean isDynamic)throws Exception;
}
