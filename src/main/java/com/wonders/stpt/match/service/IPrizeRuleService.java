package com.wonders.stpt.match.service;

import com.wonders.stpt.match.domain.MPrize;
import com.wonders.stpt.match.domain.PrizeRule;
import com.wonders.stpt.utils.paginator.mybatis.domain.PageList;

import java.util.List;

/**
 * Created by Administrator on 2014/9/11.
 */
public interface IPrizeRuleService {
    PageList<PrizeRule> getPrizeRules(PrizeRule rule,Integer pageIndex,Integer pageSize) throws Exception;

    PageList<PrizeRule> getPrizeRules(PrizeRule rule) throws Exception;

    PrizeRule getPrizeRule(String ruleId) throws Exception;

    int delete(List<String> ruleIds) throws Exception;

    int delete(String ruleId) throws Exception;

    void save(PrizeRule rule,String[] excludes) throws Exception;

    void update(PrizeRule rule ,boolean isDynamic)throws Exception;

    void validRule(MPrize prize) throws Exception;

}
