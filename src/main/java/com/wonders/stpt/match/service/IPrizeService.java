package com.wonders.stpt.match.service;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.match.domain.MApplicantDepartment;
import com.wonders.stpt.match.domain.MPrize;
import com.wonders.stpt.match.domain.PrizeRule;
import com.wonders.stpt.utils.paginator.mybatis.domain.PageList;

/**
 * 奖项
 * @author shanweifeng
 *
 */
public interface IPrizeService {
	/**
	 * 分页查找
	 * @param prize
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	PageList<MPrize> getPrizes(MPrize prize,Integer pageIndex,Integer pageSize)throws Exception;
	/**
	 * 根据条件查找（不分页）
	 * @param prize
	 * @return
	 * @throws Exception
	 */
	PageList<MPrize> getPrizes(MPrize prize)throws Exception;
	/**
	 * 根据主键查找
	 * @param prizeId
	 * @return
	 * @throws Exception
	 */
	MPrize getPrize(String prizeId)throws Exception;
	/**
	 * 批量删除
	 * @param prizeIds
	 * @return
	 * @throws Exception
	 */
	int delete(List<String> prizeIds)throws Exception;
	/**
	 * 根据主键删除
	 * @param prizeId
	 * @return
	 * @throws Exception
	 */
	int delete(String prizeId)throws Exception;
	/**
	 * 保存
	 * @param prize
	 * @throws Exception
	 */
	void save(MPrize prize,String[] excludes)throws Exception;
	/**
	 * 修改
	 * @param prize
	 * @param isDynamic
	 * @throws Exception
	 */
	void update(MPrize prize,boolean isDynamic)throws Exception;

}
