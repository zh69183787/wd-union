package com.wonders.stpt.match.service;

import java.util.List;

import com.wonders.stpt.match.domain.MPersonalPrize;
import com.wonders.stpt.utils.paginator.mybatis.domain.PageList;
/**
 * 服务层接口
 * @author shanweifeng
 *
 */
public interface IMPersonalPrizeService {
	/**
	 * 分页显示查询结果
	 * @param personalPrize
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	PageList<MPersonalPrize> getMPersonalPrizes(MPersonalPrize personalPrize,Integer pageIndex,Integer pageSize) throws Exception;
	/**
	 * 根据条件查询（不分页）
	 * @param personalPrize
	 * @return
	 * @throws Exception
	 */
    PageList<MPersonalPrize> getMPersonalPrizes(MPersonalPrize personalPrize) throws Exception;
    /**
     * 根据主键查询记录
     * @param personalPrizeId
     * @return
     * @throws Exception
     */
    MPersonalPrize getMPersonalPrize(String personalPrizeId) throws Exception;
    /**
     * 批量删除记录
     * @param personalPrizeIds
     * @return
     * @throws Exception
     */
    int delete(List<String> personalPrizeIds) throws Exception;
    /**
     * 根据主键删除记录
     * @param personalPrizeId
     * @return
     * @throws Exception
     */
    int delete(String personalPrizeId) throws Exception;
    /**
     * 新增记录
     * @param personalPrize
     * @param excludes
     * @throws Exception
     */
    void save(MPersonalPrize personalPrize) throws Exception;
    /**
     * 更新记录
     * @param personalPrize
     * @param isDynamic
     * @throws Exception
     */
    void update(MPersonalPrize personalPrize,String[] excludes,boolean isDynamic) throws Exception;
}
