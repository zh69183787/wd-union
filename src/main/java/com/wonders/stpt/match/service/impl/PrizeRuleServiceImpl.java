package com.wonders.stpt.match.service.impl;

import com.wonders.stpt.match.dao.PrizeRuleDao;
import com.wonders.stpt.match.domain.MPrize;
import com.wonders.stpt.match.domain.PrizeRule;
import com.wonders.stpt.match.service.IPrizeRuleService;
import com.wonders.stpt.utils.paginator.mybatis.domain.PageBounds;
import com.wonders.stpt.utils.paginator.mybatis.domain.PageList;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2014/9/11.
 */
@Service
public class PrizeRuleServiceImpl implements IPrizeRuleService {

    @Autowired
    private PrizeRuleDao prizeRuleDao;

    @Override
    public PageList<PrizeRule> getPrizeRules(PrizeRule rule, Integer pageIndex, Integer pageSize) throws Exception {
        if (pageIndex == null || pageSize == null) {
            return prizeRuleDao.select(rule, new PageBounds());
        } else
            return prizeRuleDao.select(rule, new PageBounds(pageIndex, pageSize));
    }

    @Override
    public PageList<PrizeRule> getPrizeRules(PrizeRule rule) throws Exception {
        return  getPrizeRules( rule,null,null);
    }

    @Override
    public PrizeRule getPrizeRule(String ruleId) throws Exception {
        PrizeRule prizeRule=new PrizeRule();
        prizeRule.setPrizeRuleId(ruleId);
        PageList<PrizeRule> prizeRules=getPrizeRules(prizeRule,null,null);
        if(prizeRules!=null && prizeRules.size()==1){
            return prizeRules.get(0);
        }
        return null;
    }

    @Override
    public int delete(List<String> ruleIds) throws Exception {
        int i=0;
        for(String ruleId:ruleIds ){
            PrizeRule prizeRule=new PrizeRule();
            prizeRule.setRemoved(PrizeRule.DELETE);
            prizeRule.setPrizeRuleId(ruleId);
            prizeRuleDao.update(prizeRule, true);//动态删除
            i++;
        }
        return i;
    }

    @Override
    public int delete(String ruleId) throws Exception {
        ArrayList<String> ruleIds=new ArrayList<String>();
        ruleIds.add(ruleId);
        return delete(ruleIds);
    }

    @Override
    public void save(PrizeRule rule, String[] excludes) throws Exception {
        if(StringUtils.isNotBlank(rule.getPrizeRuleId())){//更新
            PrizeRule pPrizeRule=getPrizeRule(rule.getPrizeRuleId());
            BeanUtils.copyProperties(rule, pPrizeRule, excludes);
            prizeRuleDao.update(pPrizeRule, false);//false为非动态更新
        }else{//添加
            prizeRuleDao.insert(rule);
        }
    }

    @Override
    public void update(PrizeRule rule, boolean isDynamic) throws Exception {
        prizeRuleDao.update(rule, isDynamic);
    }

    public void validRule(MPrize prize) throws Exception{
        PrizeRule example = new PrizeRule();
        BeanUtils.copyProperties(prize,example);
        List<PrizeRule> rules =  getPrizeRules(example);
        if(rules.size() > 1){
            throw new Exception("存在多条奖金设置规则,无法识别!");
        } else if(rules.size() < 1){
            throw new Exception("没有找到合适的奖金设置规则!");
        }else{
            PrizeRule rule = rules.get(0);
            if(prize.getBonus() > rule.getMaxBonus() || prize.getBonus() < rule.getMinBonus()){
                throw new Exception("奖金额度必须设置在"+rule.getMinBonus()+"至"+rule.getMaxBonus()+",请重新设置!");
            }
        }
    }

}
