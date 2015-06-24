package com.wonders.stpt.match.controller;

import com.wonders.stpt.match.domain.PrizeRule;
import com.wonders.stpt.match.service.IPrizeRuleService;
import com.wonders.stpt.utils.paginator.mybatis.domain.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;

/**
 * Created by Administrator on 2014/9/11.
 */
@Controller
@RequestMapping("/rule")
public class PrizeRuleController extends BaseController {

    @Autowired
    private IPrizeRuleService prizeRuleService;

    @RequestMapping(method= RequestMethod.GET,value="rules")
    public String rules(PrizeRule rule,@RequestParam(required = false, defaultValue = "1") int pageIndex,
                         @RequestParam(required = false, defaultValue = "15") int pageSize,Model model)throws Exception{

        PageList<PrizeRule> prizeRules=prizeRuleService.getPrizeRules(rule, pageIndex, pageSize);
        model.addAttribute("rule", rule);
        addPageAttr(model,prizeRules);
        return "rule/rules";
    }

    /**
     * 根据主键查找
     * @param ruleId
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(method= RequestMethod.GET,value="{ruleId}")
    public String rule(@PathVariable String ruleId,Model model)throws Exception{
        model.addAttribute("rule", prizeRuleService.getPrizeRule(ruleId));
        return "rule/rule";
    }

    /**
     * 跳转到新增页面
     * @param rule
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(method=RequestMethod.GET)
    public String form(PrizeRule rule,Model model)throws Exception{

        model.addAttribute("rule",rule);
        return "rule/rule";
    }
    /**
     * 添加
     * @param rule
     * @param model
     * @param redirectAttributes
     * @return
     * @throws Exception
     */
    @RequestMapping(method=RequestMethod.POST)
    public String save(PrizeRule rule,Model model,RedirectAttributes redirectAttributes)throws Exception{
        prizeRuleService.save(rule, null);
        redirectAttributes.addFlashAttribute("success",getMessage("save.success"));
        return "rule/rule";
    }
    /**
     * 更新
     * @param ruleId
     * @param rule
     * @param model
     * @param redirectAttributes
     * @return
     * @throws Exception
     */
    @RequestMapping(method=RequestMethod.PUT,value="{ruleId}")
    public String update(@PathVariable String ruleId,PrizeRule rule,Model model,RedirectAttributes redirectAttributes)throws Exception{
        prizeRuleService.save(rule, new String[]{"creator","createTime"});
        redirectAttributes.addFlashAttribute("success",getMessage("save.success"));
        return "redirect:rules";
    }
    /**
     * 删除(含批量删除)
     * @param ruleId
     * @param model
     * @param redirectAttributes
     * @return
     * @throws Exception
     */
    @RequestMapping(method=RequestMethod.DELETE,value="{ruleId}")
    public String delete(@PathVariable String ruleId,Model model,RedirectAttributes redirectAttributes)throws Exception{
        prizeRuleService.delete(Arrays.asList(ruleId.split(",")));
        redirectAttributes.addFlashAttribute("success",getMessage("delete.success"));
        return "redirect:rules";
    }
}
