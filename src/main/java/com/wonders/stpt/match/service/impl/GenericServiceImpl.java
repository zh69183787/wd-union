package com.wonders.stpt.match.service.impl;

import com.wonders.stpt.match.dao.GenericDAO;
import com.wonders.stpt.match.service.IGenericService;
import com.wonders.stpt.utils.paginator.mybatis.domain.PageBounds;
import com.wonders.stpt.utils.paginator.mybatis.domain.PageList;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2014/9/11.
 */
@Service
public class GenericServiceImpl<T> implements IGenericService<T> {

    private GenericDAO genericDao;

    @Override
    public PageList<T> getDomains(T domain, Integer pageIndex, Integer pageSize) throws Exception {
        if (pageIndex == null || pageSize == null) {
            return genericDao.select(domain, new PageBounds());
        } else
            return genericDao.select(domain, new PageBounds(pageIndex, pageSize));
    }

    @Override
    public PageList<T> getDomains(T domain) throws Exception {
        return getDomains(domain,null,null);
    }

    @Override
    public T getDomain(String domainId) throws Exception {
        return null;
    }

    @Override
    public int delete(List<String> domainIds) throws Exception {
        return 0;
    }

    @Override
    public int delete(String domainId) throws Exception {
        return 0;
    }

    @Override
    public void save(T domain, String[] excludes) throws Exception {

    }

    @Override
    public void update(T domain, boolean isDynamic) throws Exception {

    }

    public void setGenericDao(GenericDAO genericDao) {
        this.genericDao = genericDao;
    }
}
