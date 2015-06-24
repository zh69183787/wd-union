package com.wonders.stpt.match.dao;

import java.util.List;

import com.wonders.stpt.utils.paginator.mybatis.domain.PageBounds;
import com.wonders.stpt.utils.paginator.mybatis.domain.PageList;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2014/7/8.
 */
public interface GenericDAO<T> {
	/**
	 * 新增实体
	 * @param entity
	 * @return
	 * @throws Exception
	 */
    public int insert(T entity) throws Exception;
    /**
     * 更新实体
     * @param entity
     * @return
     * @throws Exception
     */
    public int update(@Param("entity")T entity,@Param("isDynamic")boolean isDynamic)throws Exception;
    /**
     * 根据实体主键删除
     * @param entityId
     * @return
     * @throws Exception
     */
    public int delete(List<String> entityId) throws Exception;
    /**
     * 查询所有未删除的实体
     * @return
     * @throws Exception
     */
    public PageList<T> select(T entity,PageBounds pageBounds)throws Exception;
}
