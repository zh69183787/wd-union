package com.wonders.stpt.match.service;

import com.wonders.stpt.utils.paginator.mybatis.domain.PageList;

import java.util.List;

/**
 * Created by Administrator on 2014/9/11.
 */
public interface IGenericService<T> {
    PageList<T> getDomains(T domain,Integer pageIndex,Integer pageSize) throws Exception;

    PageList<T> getDomains(T domain) throws Exception;

    T getDomain(String domainId) throws Exception;

    int delete(List<String> domainIds) throws Exception;

    int delete(String domainId) throws Exception;

    void save(T domain,String[] excludes) throws Exception;

    void update(T domain ,boolean isDynamic)throws Exception;
}
